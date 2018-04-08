package com.yfax.task.htt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttInviteUserHisDao;
import com.yfax.task.htt.vo.HttInviteUserHisVo;

/**
 * 邀请徒弟明细记录
 * @author Minbo
 */
@Service
public class HttInviteUserHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(HttInviteUserHisService.class);
	
	@Autowired
	private HttInviteUserHisDao dao;
	
	public List<HttInviteUserHisVo> getCalGoldOfInviteUser(String masterPhoneNum) {
		return this.dao.selectCalGoldOfInviteUser(masterPhoneNum);
	}
	
	/**
	 * 根据徒弟手机，找到师傅信息
	 * @param phoneNum
	 * @return
	 */
	public HttInviteUserHisVo selectInviteUserByStudentPhoneNum(String phoneNum) {
		return this.dao.selectInviteUserByStudentPhoneNum(phoneNum);
	}
	
	/**
	 * 看是否已经有师徒绑定关系了
	 * @param map
	 * @return
	 */
	public HttInviteUserHisVo selectInviteUserByMasAndStuPhoneNum(Map<String, String> map) {
		return this.dao.selectInviteUserByMasAndStuPhoneNum(map);
	}
	
	public boolean addHttInviteUserHis(HttInviteUserHisVo inviteUserHisVo) {
		try {
			return this.dao.insertInviteUserHis(inviteUserHisVo);
		} catch (Exception e) {
			logger.error("记录邀请徒弟明细记录异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public boolean modifyHttInviteUserHis(HttInviteUserHisVo inviteUserHisVo) {
		try {
			return this.dao.updateInviteUserHis(inviteUserHisVo);
		} catch (Exception e) {
			logger.error("更新邀请徒弟明细记录异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * 记录徒弟各金币贡献
	 * @param masterPhoneNum
	 * @param studentPhoneNum
	 * @param gold
	 */
	public void processStudengGold(String masterPhoneNum, String studentPhoneNum, long gold) {
		Map<String, String> mapParams = new HashMap<>();
		mapParams.put("masterPhoneNum", masterPhoneNum);
		mapParams.put("studentPhoneNum", studentPhoneNum);
		HttInviteUserHisVo inviteUserHisVo = this.selectInviteUserByMasAndStuPhoneNum(mapParams);
		if(inviteUserHisVo != null) {
			long sumOfInviteGold = inviteUserHisVo.getStudentsGold() + gold;
			inviteUserHisVo.setStudentsGold(sumOfInviteGold);
			boolean flagOfInvite = this.modifyHttInviteUserHis(inviteUserHisVo);
			if(!flagOfInvite) {
				logger.info("masterPhoneNum" + masterPhoneNum +  ", studentPhoneNum=" + studentPhoneNum 
						+ ", 处理失败。");
			}
		}
	}
}