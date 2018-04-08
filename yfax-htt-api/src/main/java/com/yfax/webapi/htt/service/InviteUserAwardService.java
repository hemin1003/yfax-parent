package com.yfax.webapi.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.utils.DateUtil;
import com.yfax.utils.JsonResult;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.InviteUserAwardDao;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.InviteUserAwardVo;

/**
 * 邀请徒弟奖励明细
 * @author Minbo
 */
@Service
public class InviteUserAwardService {

	protected static Logger logger = LoggerFactory.getLogger(InviteUserAwardService.class);

	@Autowired
	private InviteUserAwardDao dao;
	@Autowired
	private AwardHisService awardHisService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private UserTaskService userTaskService;

	public boolean addInviteUserAward(InviteUserAwardVo inviteUserAwardVo) {
		try {
			return this.dao.insertInviteUserAward(inviteUserAwardVo);
		} catch (Exception e) {
			logger.error("记录邀请徒弟奖励明细异常：" + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 处理师徒邀请贡献奖励
	 */
	@Transactional
	public boolean processInviteUserAward(String studentPhoneNum) {
		//TODO 加个标志位，处理首次邀请奖励
		try {
			InviteUserAwardVo inviteUserAwardVo = new InviteUserAwardVo();
			inviteUserAwardVo.setStudentPhoneNum(studentPhoneNum);
			List<InviteUserAwardVo> list = this.dao.selectInviteUserAward(inviteUserAwardVo);
			for (int i = 0; i < list.size(); i++) {
				inviteUserAwardVo = list.get(i);
				
				// 1. 完成首次当天奖励
				if (inviteUserAwardVo.getTime() == 0 && inviteUserAwardVo.getIsAward() == 0) {
					AppUserVo appUserVoOfMaster = this.appUserService.selectLoginByPhoneNum(inviteUserAwardVo.getMasterPhoneNum());
					if(appUserVoOfMaster == null) {
						logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。师傅的masterPhoneNum=" 
								+ inviteUserAwardVo.getMasterPhoneNum() + "账户不存在，跳过处理");
						return false;
					}
					if(appUserVoOfMaster.getBlackList() == 1) {
						logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。师傅的masterPhoneNum=" 
								+ appUserVoOfMaster.getPhoneNum() + "，黑名单用户，跳过处理");
						return false;
					}
					String cTime = DateUtil.getCurrentLongDateTime();
					logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。进入处理首次当天奖励，师傅的phoneNum="
							+ inviteUserAwardVo.getMasterPhoneNum() + ", 徒弟的phoneNum="
							+ inviteUserAwardVo.getStudentPhoneNum());
					inviteUserAwardVo.setIsAward(1);
					inviteUserAwardVo.setUpdateDate(cTime);
					boolean flag = this.dao.updateInviteUserAward(inviteUserAwardVo);
					logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。更新[首次当天奖励]记录标识结果：flag=" + flag);

					if (flag) {
						// 2. 更新徒弟每日分步标识，每日只能下发一次奖励
						AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(studentPhoneNum);
						appUserVo.setIsInviteStep(1); // 当日分步奖励
						appUserVo.setUpdateDate(cTime);
						flag = this.appUserService.modifyUser(appUserVo);
						logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。更新[徒弟每日分步标识]结果：flag=" + flag);

						if (flag) {

							// 2. 更新师傅的徒弟数
							logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。准备更新师傅的徒弟数，师傅的phoneNum=" + inviteUserAwardVo.getMasterPhoneNum()
									+ ", 原徒弟数students=" + appUserVoOfMaster.getStudents());
							appUserVoOfMaster.setStudents(appUserVoOfMaster.getStudents() + 1); // 徒弟数+1
							appUserVoOfMaster.setUpdateDate(cTime);
							flag = this.appUserService.modifyUser(appUserVoOfMaster);
							logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。更新[师傅的徒弟数]结果：flag=" + flag + "，师傅最新徒弟数students="
									+ appUserVoOfMaster.getStudents());

							if (flag) {
								// 3. 下发记录奖励数据
								logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。下发记录奖励数据，inviteUserAwardVo=" + inviteUserAwardVo.toString());
								int gold = Integer.valueOf(
										inviteUserAwardVo.getGold().substring(1, inviteUserAwardVo.getGold().length()));
								JsonResult jResult = this.awardHisService.addAwardHis(
										inviteUserAwardVo.getMasterPhoneNum(), gold, GlobalUtils.AWARD_TYPE_INVITE,
										null, null, null, null, null, null, null, null,
										inviteUserAwardVo.getStudentPhoneNum(), null, null);
								logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。记录奖励结果：jResult=" + jResult.toJsonString());
							}
							
							// 如果是首次，则完成任务：师傅首次成功收徒
							if (flag && appUserVoOfMaster.getStudents() == 1) {
								flag = this.userTaskService.finishFirstStuTask(inviteUserAwardVo.getMasterPhoneNum());
								logger.info("phoneNum=" + inviteUserAwardVo.getStudentPhoneNum() + "。更新[师傅的任务奖励-首次成功收徒]结果：flag=" + flag);
							}
						}
					}
					break;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("处理师徒邀请贡献奖励异常：" + e.getMessage(), e);
			return false;
		}
	}
}