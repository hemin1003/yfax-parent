package com.yfax.webapi.htt.service;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.utils.JsonResult;
import com.yfax.utils.ResultCode;
import com.yfax.utils.StrUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.AppUserDao;
import com.yfax.webapi.htt.dao.AwardHisDao;
import com.yfax.webapi.htt.dao.InviteUserAwardDao;
import com.yfax.webapi.htt.vo.AppConfigVo;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.AwardCheckinHisVo;
import com.yfax.webapi.htt.vo.AwardHisVo;
import com.yfax.webapi.htt.vo.AwardStudentHisVo;
import com.yfax.webapi.htt.vo.InviteUserAwardVo;
import com.yfax.webapi.htt.vo.InviteUserHisVo;
import com.yfax.webapi.htt.vo.ReadHisVo;
import com.yfax.webapi.htt.vo.UserTaskVo;
import com.yfax.webapi.htt.vo.VideoHisVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 记录用户金币奖励记录
 * @author Minbo
 */
@Service
public class AwardHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(AwardHisService.class);
	
	@Autowired
	private AwardHisDao awardHisDao;
	@Autowired
	private AppUserDao appUserDao;
	@Autowired
	private InviteUserHisService inviteUserHisService;
	@Autowired
	private ReadHisService readHisService;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private InviteUserAwardDao inviteUserAwardDao;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private AwardCheckinHisService awardCheckinHisService;
	@Autowired
	private AwardStudentHisService awardStudentHisService;
	@Autowired
	private VideoHisService videoHisService;
	
	/**
	 * @param phoneNum
	 * @param gold
	 * @param awardType
	 * @param firstRead
	 * @param firstShare
	 * @param firstInvite
	 * @param readHisId
	 * @param dailyCheckIn
	 * @param continueCheckIn
	 * @param dailyReadAward
	 * @param dailyVideoAward
	 * @param studentPhoneNum
	 * @return
	 */
	public JsonResult addAwardHis(String phoneNum, int gold, Integer awardType, 
			Integer firstRead, Integer firstShare, Integer firstInvite, String readHisId, 
			Integer dailyCheckIn, Integer continueCheckIn, Integer dailyReadAward, Integer dailyVideoAward, 
			String studentPhoneNum, String taskId, String taskTitle) {
		try {
			//1. 记录奖励明细
			String ids = UUID.getUUID();
			AwardHisVo awardHisVo = new AwardHisVo();
			awardHisVo.setId(ids);
			awardHisVo.setPhoneNum(phoneNum);
			awardHisVo.setAwardType(awardType);
			awardHisVo.setAwardName(GlobalUtils.getAwardTypeName(awardType));
			awardHisVo.setGold("+" + String.valueOf(gold));
			String cTime = DateUtil.getCurrentLongDateTime();
			awardHisVo.setCreateDate(cTime);
			awardHisVo.setUpdateDate(cTime);
			awardHisVo.setStudentPhoneNum(studentPhoneNum);
			//任务奖励
			if(awardType == GlobalUtils.AWARD_TYPE_TASK || awardType == GlobalUtils.AWARD_TYPE_GAME) {
				awardHisVo.setTaskId(taskId);
				awardHisVo.setTaskTitle(taskTitle);
			}
			//2. 更新用户金币余额
			AppUserVo appUserVo = this.appUserDao.selectByPhoneNum(phoneNum);
			appUserVo.setTabArticle(null);
			appUserVo.setTabVideo(null);
			long old = Long.valueOf(appUserVo.getGold());
			long sum = old + gold;
			appUserVo.setGold(String.valueOf(sum));
			long oldTotal = appUserVo.getTotalGold();
			long sumTotal = oldTotal + gold;
			appUserVo.setTotalGold(sumTotal);
			//分步邀请奖励
			if(awardType == GlobalUtils.AWARD_TYPE_INVITE && 
					!StrUtil.null2Str(studentPhoneNum).equals("")) {
				long oldStudent = appUserVo.getStudentsGold();
				long sumStudent = oldStudent + gold;
				appUserVo.setStudentsGold(sumStudent);
			}
			appUserVo.setUpdateDate(cTime);
			appUserVo.setFirstRead(firstRead);
			appUserVo.setFirstShare(firstShare);
			appUserVo.setFirstInvite(firstInvite);
			//今日签到标识
			appUserVo.setDailyCheckIn(dailyCheckIn);	
			//连续签到标识
			appUserVo.setContinueCheckIn(continueCheckIn);
			//每日累积阅读时长奖励标识
			appUserVo.setDailyReadAward(dailyReadAward);
			//每日累积视频时长奖励标识
			appUserVo.setDailyVideoAward(dailyVideoAward);
			boolean flag1 = this.appUserDao.updateUser(appUserVo);
			if(!flag1) {
				logger.error("phoneNum=" + phoneNum + "，用户金币奖励记录更新失败flag1=" + flag1, 
						new RuntimeException("用户金币更新失败（并发请求）"));
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			appUserVo = this.appUserDao.selectByPhoneNum(phoneNum);
			logger.info(GlobalUtils.getAwardTypeName(awardType) + ": 手机号码phoneNum=" + phoneNum + ", 原金币余额gold=" + old + ", 奖励金币gold=" + gold 
				+ ", 更新金币总余额sum=" + sum + ", 奖励类型awardType=" + awardType);
			boolean flag =  false;
			
			
			//分步邀请奖励
			if(awardType == GlobalUtils.AWARD_TYPE_INVITE && 
					!StrUtil.null2Str(studentPhoneNum).equals("")) {
				AwardStudentHisVo awardStudentHisVo = new AwardStudentHisVo();
				awardStudentHisVo.setId(UUID.getUUID());
				awardStudentHisVo.setPhoneNum(phoneNum);
				awardStudentHisVo.setAwardType(awardType);
				awardStudentHisVo.setAwardName(GlobalUtils.getAwardTypeName(awardType));
				awardStudentHisVo.setGold("+" + String.valueOf(gold));
				awardStudentHisVo.setCreateDate(cTime);
				awardStudentHisVo.setUpdateDate(cTime);
				awardStudentHisVo.setStudentPhoneNum(studentPhoneNum);
				flag = this.awardStudentHisService.addAwardStudentHis(awardStudentHisVo);
				logger.info("phoneNum=" + phoneNum + "，分步邀请奖励结果flag=" + flag);
			
			//6. 签到奖励
			}else if(awardType == GlobalUtils.AWARD_TYPE_DAYLY) {
				AwardCheckinHisVo awardCheckinHisVo = new AwardCheckinHisVo();
				awardCheckinHisVo.setId(ids);
				awardCheckinHisVo.setPhoneNum(phoneNum);
				awardCheckinHisVo.setAwardType(awardType);
				awardCheckinHisVo.setAwardName(GlobalUtils.getAwardTypeName(awardType));
				awardCheckinHisVo.setGold("+" + String.valueOf(gold));
				awardCheckinHisVo.setCreateDate(cTime);
				awardCheckinHisVo.setUpdateDate(cTime);
				flag = this.awardCheckinHisService.addAwardCheckinHis(awardCheckinHisVo);
				logger.info("phoneNum=" + phoneNum + "，签到奖励结果flag=" + flag);
				
			}else {
				flag = this.awardHisDao.insertAwardHis(awardHisVo);
			}
			
			logger.info("奖励记录插入flag=" + flag + ", 更新用户信息flag1=" + flag1);
			if(flag && flag1) {
				//分步邀请奖励
				if(awardType == GlobalUtils.AWARD_TYPE_INVITE && 
						!StrUtil.null2Str(studentPhoneNum).equals("")) {
					//记录徒弟贡献明细
					this.inviteUserHisService.processStudengGold(phoneNum, studentPhoneNum, gold);
				}
				//3. 阅读奖励
				if(awardType == GlobalUtils.AWARD_TYPE_READ) {
					try {
						//看是否需要给师傅奖励
						this.awardMasterHis(phoneNum, gold, awardType, cTime, awardHisVo, appUserVo);
					} catch (Exception e) {
						logger.error("看是否需要给师傅奖励异常：" + e.getMessage(), 
								new RuntimeException("phoneNum=" + phoneNum));
					}
					try {
						//阅读任务状态更新
						this.processUserReadTask(appUserVo);
					} catch (Exception e) {
						logger.error("阅读任务状态更新异常：" + e.getMessage(), 
								new RuntimeException("phoneNum=" + phoneNum));
					}
					try {
						//记录文章已获取金币标识
						ReadHisVo readHisVo = this.readHisService.selectReadHisById(readHisId);
						if(readHisVo == null) {
							readHisVo = new ReadHisVo();
							readHisVo.setId(readHisId);
						}
						readHisVo.setIsAward(1);		//已奖励标识
						readHisVo.setAwardCount(readHisVo.getAwardCount() + 1);	//奖励次数加1
						readHisVo.setUpdateDate(cTime);
						boolean flag2 =  this.readHisService.modifyReadHis(readHisVo);
						if (!flag2) {
							logger.error("阅读文章奖励标识更新失败，readHisId=" + readHisId);
						}
						
					} catch (Exception e) {
						logger.error("阅读文章历史奖励标识，readHisId=" + readHisId + "，异常：" + e.getMessage(), e);
					}
				}
				//4. 视频奖励
				if(awardType == GlobalUtils.AWARD_TYPE_VIDEO) {
					//视频奖励任务状态更新
					this.processUserVideoTask(appUserVo);
					
					try {
						//记录视频已获取金币标识
						//共用了readHisId字段
						VideoHisVo videoHisVo = this.videoHisService.selectVideoHisById(readHisId);
						if(videoHisVo == null) {
							videoHisVo = new VideoHisVo();
							videoHisVo.setId(readHisId);
						}
						videoHisVo.setIsAward(1);		//已奖励标识
						videoHisVo.setAwardCount(videoHisVo.getAwardCount() + 1);	//奖励次数加1
						videoHisVo.setUpdateDate(cTime);
						boolean flag2 =  this.videoHisService.modifyVideoHis(videoHisVo);
						if (!flag2) {
							logger.error("视频奖励标识更新失败，videoHisId=" + readHisId);
						}
					} catch (Exception e) {
						logger.error("视频已获取金币标识，readHisId=" + readHisId + "，异常：" + e.getMessage(), e);
					}
				}
				Map<String, Object> map = new HashMap<>();
				map.put("gold", gold);
				map.put("awardType", awardType);
				return new JsonResult(ResultCode.SUCCESS, map);
			}else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
		} catch (Exception e) {
			logger.error("记录用户金币奖励记录异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.EXCEPTION);
		}
	}
	
	/**
	 * 个人阅读任务状态更新
	 * @param appUserVo
	 */
	public void processUserReadTask(AppUserVo appUserVo) {
		//阅读资讯3分钟
		if(appUserVo.getIsFirstRead() == 0) {
			Map<String, Object> params = new HashMap<>();
			params.put("phoneNum", appUserVo.getPhoneNum());
			params.put("awardType", GlobalUtils.AWARD_TYPE_READ);
			//任务列表记录
			UserTaskVo userTaskVo = new UserTaskVo();
			userTaskVo.setPhoneNum(appUserVo.getPhoneNum());
			userTaskVo.setTaskId("n001");	//新手任务
			userTaskVo = this.userTaskService.selectUserTask(userTaskVo);
			if(userTaskVo != null) {
				int count = userTaskVo.getTaskTime() / 30;
				if(count > 0 ) {
					Long userTaskCount = this.awardHisDao.selectCountByNewUserTask(params);
					if(userTaskVo != null && userTaskCount>=count
							&& userTaskVo.getStatus() == 0) {
						//更新个人信息标识
						appUserVo.setIsFirstRead(1);
						appUserVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
						boolean flag = this.appUserDao.updateUser(appUserVo);
						if (flag) {
							//更新任务状态信息
							userTaskVo.setStatus(1);		//可领取
							userTaskVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
							this.userTaskService.modifyUserTask(userTaskVo);
						}
					}
				}
			}
		}
		
		//阅读资讯5分钟
		if(appUserVo.getIsDailyRead() == 0) {
			//任务列表记录
			Map<String, Object> params = new HashMap<>();
			params.put("phoneNum", appUserVo.getPhoneNum());
			params.put("awardType", GlobalUtils.AWARD_TYPE_READ);
			params.put("currentTime", DateUtil.getCurrentDate());
			//任务列表记录
			UserTaskVo userTaskVo = new UserTaskVo();
			userTaskVo.setPhoneNum(appUserVo.getPhoneNum());
			userTaskVo.setTaskId("d001");	//日常任务
			userTaskVo = this.userTaskService.selectUserTask(userTaskVo);
			if(userTaskVo != null) {
				int count = userTaskVo.getTaskTime() / 30;
				if(count > 0 ) {
					Long userTaskCount = this.selectCountByDailyTask(params);
					if(userTaskVo != null && userTaskCount>=count 
							&& userTaskVo.getStatus() == 0) {
						
						//更新个人信息标识
						appUserVo.setIsDailyRead(1);
						appUserVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
						boolean flag = this.appUserDao.updateUser(appUserVo);
						if (flag) {
							//更新任务状态信息
							userTaskVo.setStatus(1);		//可领取
							userTaskVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
							this.userTaskService.modifyUserTask(userTaskVo);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 个人阅读任务状态更新
	 * @param appUserVo
	 */
	public void processUserVideoTask(AppUserVo appUserVo) {
		//观看视频5分钟
		if(appUserVo.getIsDailyVideo() == 0) {
			//任务列表记录
			Map<String, Object> params = new HashMap<>();
			params.put("phoneNum", appUserVo.getPhoneNum());
			params.put("awardType", GlobalUtils.AWARD_TYPE_VIDEO);
			params.put("currentTime", DateUtil.getCurrentDate());
			//任务列表记录
			UserTaskVo userTaskVo = new UserTaskVo();
			userTaskVo.setPhoneNum(appUserVo.getPhoneNum());
			userTaskVo.setTaskId("d004");	//日常任务
			userTaskVo = this.userTaskService.selectUserTask(userTaskVo);
			if(userTaskVo != null) {
				int count = userTaskVo.getTaskTime() / 30;
				if(count > 0 ) {
					Long userTaskCount = this.selectCountByDailyTask(params);
					if(userTaskVo != null && userTaskCount>=count
							&& userTaskVo.getStatus() == 0) {
						
						//更新个人信息标识
						appUserVo.setIsDailyVideo(1);
						appUserVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
						boolean flag = this.appUserDao.updateUser(appUserVo);
						if (flag) {
							//更新任务状态信息
							userTaskVo.setStatus(1);		//可领取
							userTaskVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
							this.userTaskService.modifyUserTask(userTaskVo);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 随机阅读奖励，给师傅奖励
	 * @throws Exception
	 */
	public void awardMasterHis(String phoneNum, int gold, int awardType, String cTime, 
			AwardHisVo awardHisVo, AppUserVo stuAppUserVo) throws Exception {
		logger.info("看是否需要给师傅奖励.");
		//获得师傅手机号码信息
		InviteUserHisVo inviteUserHisVo = this.inviteUserHisService.selectInviteUserByStudentPhoneNum(phoneNum);
		if(inviteUserHisVo != null) {
			//取师徒贡献奖励配置
			String condition = "";
			String extraAward = "";
			AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
			
			JSONObject jsonObject = JSONObject.fromObject(appConfigVo.getParams().toString());
			Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
			for (Entry<String, Object> entry : mapJson.entrySet()) {
				if(entry.getKey().toString().equals("inviteConfig")) {
					JSONArray jsonArray = JSONArray.fromObject(entry.getValue().toString());
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						String goldConfig = StrUtil.null2Str(jsonObj.get("goldConfig"));
						JSONArray goldConfigJsonArray = JSONArray.fromObject(goldConfig);
						for (int j = 0; j < goldConfigJsonArray.size(); j++) {
							JSONObject goldConfigJsonObj = goldConfigJsonArray.getJSONObject(j);
							condition = StrUtil.null2Str(goldConfigJsonObj.get("condition"));
							extraAward = StrUtil.null2Str(goldConfigJsonObj.get("extraAward"));
						}
					}
					break;
				}
			}
			
			if(condition.equals("") || extraAward.equals("")) {
				logger.error("appConfig的params参数，邀请师徒奖励配置异常，请检查", new RuntimeException("condition=" + condition 
						+ ", extraAward=" + extraAward));
			}else {
				Map<String, Object> map = new HashMap<>();
				map.put("phoneNum", phoneNum);
				map.put("awardType", awardType);
				
				//收益总金额
				long awardCount = this.awardHisDao.selectUserAwardTotal(map);
				logger.info("阅读金币awardCount=" + awardCount + ", condition="+ condition);
				if(awardCount < Integer.valueOf(condition)) {
					logger.info("phoneNum=" + phoneNum + ", 阅读金币还未达到贡献师傅金币励条件，跳过处理。");
				}else{
					//5. 记录师傅的徒弟贡献奖励明细
					AwardStudentHisVo awardStudentHisVo = new AwardStudentHisVo();
					awardStudentHisVo.setId(UUID.getUUID());
					awardStudentHisVo.setPhoneNum(inviteUserHisVo.getMasterPhoneNum());
					awardStudentHisVo.setAwardType(GlobalUtils.AWARD_TYPE_STUDENT);
					awardStudentHisVo.setAwardName(GlobalUtils.getAwardTypeName(GlobalUtils.AWARD_TYPE_STUDENT));
					awardStudentHisVo.setGold(extraAward);
					awardStudentHisVo.setCreateDate(cTime);
					awardStudentHisVo.setUpdateDate(cTime);
					awardStudentHisVo.setStudentPhoneNum(inviteUserHisVo.getStudentPhoneNum());
					//6. 更新师傅的金币余额
					AppUserVo appUserVo2 = this.appUserDao.selectByPhoneNum(inviteUserHisVo.getMasterPhoneNum());
					appUserVo2.setTabArticle(null);
					appUserVo2.setTabVideo(null);
					if(appUserVo2 != null) {
						//如果为黑名单用户，直接返回已获取奖励，不继续逻辑处理
						if(appUserVo2.getBlackList() == 1) {
							logger.warn("师傅的手机号码phoneNum=" + appUserVo2.getPhoneNum() + "，黑名单用户，跳过处理");
							
						}else {
							int extra = Integer.valueOf(extraAward.substring(1, extraAward.length()));
							//金币余额
							int old2 = Integer.valueOf(appUserVo2.getGold());
							int sum2 = old2 + extra;
							appUserVo2.setGold(String.valueOf(sum2));
							//总金币
							long oldTotal = appUserVo2.getTotalGold();
							long sumTotal = oldTotal + extra;
							appUserVo2.setTotalGold(sumTotal);
							//徒弟贡献
							long oldStudent = appUserVo2.getStudentsGold();
							long sumStudent = oldStudent + extra;
							appUserVo2.setStudentsGold(sumStudent);
							appUserVo2.setUpdateDate(cTime);
							//记录徒弟贡献明细
							this.inviteUserHisService.processStudengGold(appUserVo2.getPhoneNum(), 
									stuAppUserVo.getPhoneNum(), extra);
							logger.info("师傅的手机号码phoneNum=" + inviteUserHisVo.getMasterPhoneNum() + ", 原金币余额gold=" + old2 
									+ ", 贡献金币=" + extra + ", 更新金币后总余额sum=" + sum2 
									+ ", 奖励类型awardType=" + GlobalUtils.AWARD_TYPE_STUDENT);
							boolean flag2 =  this.awardStudentHisService.addAwardStudentHis(awardStudentHisVo);
							boolean flag3 = this.appUserDao.updateUser(appUserVo2);
							//更新奖励明细记录isCal标识
							boolean flag4= this.awardHisDao.updateUserAwardTotalFlag(awardHisVo);
							logger.info("贡献给师傅阅读金币更新结果，贡献师傅阅读奖励新增flag2=" + flag2 + ", 更新师傅金币信息flag3=" + flag3 
									+ ", 更新奖励明细记录isCal标识flag4=" + flag4);
							
							if(stuAppUserVo.getIsMasterFinished() == 1) {
								logger.info("stuAppUserVo.getIsMasterFinished()=" + stuAppUserVo.getIsMasterFinished() 
									+ ", 徒弟贡献的分步奖励全部完成标识已达到，跳过处理。");
							}
							if(stuAppUserVo.getIsInviteStep() == 1) {
								logger.info("stuAppUserVo.getIsInviteStep()=" + stuAppUserVo.getIsInviteStep() 
									+ ", 徒弟今日分步奖励已下发给师傅了，跳过处理。");
							}
							logger.info("徒弟phoneNum=" + phoneNum + "，徒弟分步奖励全部完成标识IsMasterFinished()=" + stuAppUserVo.getIsMasterFinished() 
							+ ", 徒弟今日分步奖励IsInviteStep()=" + stuAppUserVo.getIsInviteStep());
							//分步奖励当日标识
							if(stuAppUserVo.getIsInviteStep() == 0 && stuAppUserVo.getIsMasterFinished() ==0 
									&& flag2 && flag3 && flag4) {
								InviteUserAwardVo inviteUserAwardVo = new InviteUserAwardVo();
								inviteUserAwardVo.setStudentPhoneNum(phoneNum);
								List<InviteUserAwardVo> list = this.inviteUserAwardDao.selectInviteUserAward(inviteUserAwardVo);
								for (int i = 0; i < list.size(); i++) {
									inviteUserAwardVo = list.get(i);
									//取第一条
									if(inviteUserAwardVo.getIsAward() == 0) {
										//徒弟信息设置
										stuAppUserVo.setIsInviteStep(1);	//分步当日奖励标识
										if(i == list.size() - 1) {
											stuAppUserVo.setIsMasterFinished(1);	//师傅分步奖励全部完成标识
										}
										boolean flag6 = this.appUserDao.updateUser(stuAppUserVo);
										if(!flag6) {
											logger.error("phoneNum=" + phoneNum + "，用户更新失败，不能发放分步当日奖励。", 
													new RuntimeException("分步当日奖励标识更新异常"));
											break;
										}
										
										//确认记录没有重复生成
										Map<String, Object> params = new HashMap<>();
										params.put("studentPhoneNum", stuAppUserVo.getPhoneNum());
										params.put("updateDate", DateUtil.getCurrentDate());
										InviteUserAwardVo tempObj = this.inviteUserAwardDao.selectIsExistInviteUserAward(params);
										if(tempObj != null) {
											logger.error("phoneNum=" + phoneNum + "，当日分布邀请奖励已发放，不能重复发放，跳过处理。", 
													new RuntimeException("分布邀请奖励不能重复发放"));
											break;
										}
										
										//1. 记录师傅的徒弟邀请奖励明细
										gold = Integer.valueOf(inviteUserAwardVo.getGold().substring(1, inviteUserAwardVo.getGold().length()));
										AwardStudentHisVo awardStudentHisVo2 = new AwardStudentHisVo();
										awardStudentHisVo2.setId(UUID.getUUID());
										awardStudentHisVo2.setPhoneNum(inviteUserHisVo.getMasterPhoneNum());
										awardStudentHisVo2.setAwardType(GlobalUtils.AWARD_TYPE_INVITE);
										awardStudentHisVo2.setAwardName(GlobalUtils.getAwardTypeName(GlobalUtils.AWARD_TYPE_INVITE));
										awardStudentHisVo2.setGold(inviteUserAwardVo.getGold());
										cTime = DateUtil.getCurrentLongDateTime();
										awardStudentHisVo2.setCreateDate(cTime);
										awardStudentHisVo2.setUpdateDate(cTime);
										awardStudentHisVo2.setStudentPhoneNum(inviteUserHisVo.getStudentPhoneNum());
										boolean flag =  this.awardStudentHisService.addAwardStudentHis(awardStudentHisVo2);
										
										if(flag) {
											//2. 更新师傅用户金币余额
											//金币余额
											appUserVo2 = this.appUserDao.selectLoginByPhoneNum(appUserVo2.getPhoneNum());
											int old3 = Integer.valueOf(appUserVo2.getGold());
											int sum = Integer.valueOf(appUserVo2.getGold()) + gold;
											appUserVo2.setGold(String.valueOf(sum));
											//总金币
											long oldTotal2 = appUserVo2.getTotalGold();
											long sumTotal2 = oldTotal2 + gold;
											appUserVo2.setTotalGold(sumTotal2);
											//徒弟贡献
											long oldStudent2 = appUserVo2.getStudentsGold();
											long sumStudent2 = oldStudent2 + gold;
											appUserVo2.setStudentsGold(sumStudent2);
											appUserVo2.setUpdateDate(cTime);
											//记录徒弟贡献明细
											this.inviteUserHisService.processStudengGold(inviteUserHisVo.getMasterPhoneNum(), 
													inviteUserHisVo.getStudentPhoneNum(), gold);
											//如果是首次当天奖励（托底程序判断）
											if (inviteUserAwardVo.getTime() == 0 && inviteUserAwardVo.getIsAward() == 0) {
												logger.info("phoneNum=" + inviteUserHisVo.getStudentPhoneNum() + "。准备更新师傅的徒弟数，师傅的phoneNum=" + inviteUserHisVo.getMasterPhoneNum()
													+ ", 原徒弟数students=" + appUserVo2.getStudents());
												appUserVo2.setStudents(appUserVo2.getStudents() + 1); // 徒弟数+1
												logger.info("phoneNum=" + inviteUserHisVo.getStudentPhoneNum() + "。更新[师傅的徒弟数]结果：flag=" + flag + "，师傅最新徒弟数students="
														+ appUserVo2.getStudents());
												// 如果是第一个徒弟，则完成任务：师傅首次成功收徒
												if (appUserVo2.getStudents() == 1) {
													flag = this.userTaskService.finishFirstStuTask(inviteUserHisVo.getMasterPhoneNum());
													logger.info("phoneNum=" + inviteUserHisVo.getStudentPhoneNum() + "。更新[师傅的任务奖励-首次成功收徒]结果：flag=" + flag);
												}
											}
											//更新师傅信息
											boolean flag1 = this.appUserDao.updateUser(appUserVo2);
											
											//更新奖励标识位
											inviteUserAwardVo.setIsAward(1);
											inviteUserAwardVo.setUpdateDate(cTime);
											boolean flag5 = this.inviteUserAwardDao.updateInviteUserAward(inviteUserAwardVo);
											
											logger.info("师傅手机号码phoneNum=" + appUserVo2.getPhoneNum()+ ", 原金币余额gold=" + old3+ ", 师徒分步奖励金币gold=" + gold 
													+ ", 更新金币总余额sum=" + sum + ", 奖励类型awardType=" + GlobalUtils.AWARD_TYPE_INVITE);
											logger.info("更新徒弟分步奖励记录标识（time=" + inviteUserAwardVo.getTime() + ", desc=" + inviteUserAwardVo.getDescStr() + "）flag5=" + flag5 
													+ "，师傅的奖励记录插入flag=" + flag + ", 更新师傅用户信息flag1=" + flag1 + "，更新徒弟用户信息flag6=" + flag6);
										}
										break;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public List<AwardHisVo> selectAwardHisByPhoneNum(String phoneNum) {
		List<AwardHisVo> listOfAwardHis = this.awardHisDao.selectAwardHisByPhoneNum(phoneNum);
		List<AwardCheckinHisVo> listOfCheckinAwardHis = this.awardCheckinHisService.selectAllAwardCheckinHisList(phoneNum);
		for (AwardCheckinHisVo awardCheckinHisVo : listOfCheckinAwardHis) {
			AwardHisVo awardHisVo  = new AwardHisVo();
			awardHisVo.setId(awardCheckinHisVo.getId());
			awardHisVo.setPhoneNum(awardCheckinHisVo.getPhoneNum());
			awardHisVo.setAwardType(awardCheckinHisVo.getAwardType());
			awardHisVo.setAwardName(awardCheckinHisVo.getAwardName());
			awardHisVo.setGold(awardCheckinHisVo.getGold());
			awardHisVo.setCreateDate(awardCheckinHisVo.getCreateDate());
			awardHisVo.setUpdateDate(awardCheckinHisVo.getUpdateDate());
			awardHisVo.setStudentPhoneNum(awardCheckinHisVo.getStudentPhoneNum());
			listOfAwardHis.add(awardHisVo);
		}
		List<AwardStudentHisVo> listOfStudentAwardHis = this.awardStudentHisService.getAwardStudentHisList(phoneNum);
		for (AwardStudentHisVo awardStudentHisVo : listOfStudentAwardHis) {
			AwardHisVo awardHisVo  = new AwardHisVo();
			awardHisVo.setId(awardStudentHisVo.getId());
			awardHisVo.setPhoneNum(awardStudentHisVo.getPhoneNum());
			awardHisVo.setAwardType(awardStudentHisVo.getAwardType());
			awardHisVo.setAwardName(awardStudentHisVo.getAwardName());
			awardHisVo.setGold(awardStudentHisVo.getGold());
			awardHisVo.setCreateDate(awardStudentHisVo.getCreateDate());
			awardHisVo.setUpdateDate(awardStudentHisVo.getUpdateDate());
			awardHisVo.setStudentPhoneNum(awardStudentHisVo.getStudentPhoneNum());
			listOfAwardHis.add(awardHisVo);
		}
		listOfAwardHis.sort(new Comparator<AwardHisVo>() {

			@Override
			public int compare(AwardHisVo o1, AwardHisVo o2) {
				Date startTime = DateUtil.parseLongDateTime(o1.getCreateDate());
				Date endTime = DateUtil.parseLongDateTime(o2.getCreateDate());
				int flag = 1;
				if(startTime.after(endTime)) {
					flag = -1;
				}
				return flag;
			}
			
		});
		return listOfAwardHis;
	}
	
	public AwardHisVo selectAwardHisIsCheckIn(Map<String, Object> map) {
		return this.awardHisDao.selectAwardHisIsCheckIn(map);
	}
	
	public Long selectUserTotalOfGold(Map<String, Object> map) {
		return this.awardHisDao.selectUserTotalOfGold(map);
	}
	
//	public Long getContinueCheckInAward(Map<String, Object> map) {
//		return this.awardHisDao.selectContinueCheckInAward(map);
//	}
//	
//	public Long selectUserAwardCount(Map<String, Object> map) {
//		return this.awardHisDao.selectUserAwardCount(map);
//	}
//	
//	public List<AwardHisVo> selectAwardHisCheckList(String phoneNum) {
//		return this.awardHisDao.selectAwardHisCheckList(phoneNum);
//	}
	
//	/**
//	 * 根据奖励记录获取七日签到数据
//	 * @param phoneNum
//	 * @return
//	 */
//	public Map<Object, Object> queryContinueCheckList(String phoneNum) {
//		//取七日金币配置数据
//		AppConfigVo appConfigVo = this.appConfigDao.selectAppConfig();
//		String params = appConfigVo.getParams().toString();
//		
//		Map<Object, Object> mapDay = new HashMap<>();
//		Map<Object, Object> checkInInfoMap = new HashMap<>();
//		JSONObject jsonObject = JSONObject.fromObject(params);
//		Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
//		for (Entry<String, Object> entry : mapJson.entrySet()) {
//			if(entry.getKey().toString().equals("checkInConfig")) {
//				JSONArray jsonArray = JSONArray.fromObject(entry.getValue().toString());
//				for (int i = 0; i < jsonArray.size(); i++) {
//					JSONObject jsonObj = jsonArray.getJSONObject(i);
//					mapDay.put(jsonObj.get("day"), jsonObj.get("gold"));
//				}
//				break;
//			}
//		}
//		
//		//签到历史数据
//		List<AwardHisVo> list = this.awardHisDao.selectAwardHisCheckList(phoneNum);
//		Map<Object, Object> resultMap = new HashMap<>();
//		List<Map<Object, Object>> mapList = new ArrayList<>();
//		if(list != null) {
//			Map<Object, Object> map = new HashMap<>();
//			String currentDate = DateUtil.getCurrentDate();
//			int checkInNum = 0;
//			int i = 1;
//			while(true) {
//				String date = DateUtil.formatDate(DateUtils.addDays(DateUtil.parseDate(currentDate), -i));
//				int isCheckIn = getCheckInFlag(date, list);
//				if(isCheckIn == 1) {
//					checkInNum++;
//				}else {
//					break;
//				}
//				i++;
//			}
//			//如果已连续签到7日，则重新计算
//			if(checkInNum==7) {
//				checkInNum = 0;
//				try {
//					AwardHisVo awardHisVo = new AwardHisVo();
//					awardHisVo.setPhoneNum(phoneNum);
//					awardHisVo.setAwardType(3);
//					this.awardHisDao.updateUserAwardTotalFlag(awardHisVo);
//				} catch (Exception e) {
//					logger.error("更新用户签到记录标识异常：" + e.getMessage(), e);
//				}
//			}
//			
//			//当天之前的签到数据情况
//			for (int j = 1; j <= checkInNum; j++) {
//				map = new HashMap<>();
//				map.put("day", j);
//				map.put("isCheckIn", 1);
//				map.put("gold", mapDay.get(j));
//				map.put("date", "");
//				mapList.add(map);
//			}
//			
//			//当天签到数据情况
//			int todayIsCheckIn = getCheckInFlag(currentDate, list);
//			int j = checkInNum+1;
//			map = new HashMap<>();
//			map.put("day", j);
//			map.put("isCheckIn", todayIsCheckIn);
//			map.put("gold", mapDay.get(j));
//			map.put("date", currentDate);
//			checkInInfoMap.putAll(map);
//			mapList.add(map);
//			if(j == 7) {
//				checkInInfoMap.put("nextGold", mapDay.get(1));
//			}
//			
//			//当天后签到数据情况
//			for (int k = checkInNum+2; k < 8; k++) {
//				map = new HashMap<>();
//				map.put("day", k);
//				map.put("isCheckIn", 0);
//				map.put("gold", mapDay.get(k));
//				map.put("date", "");
//				mapList.add(map);
//				//返回第二天奖励金币
//				if(k == checkInNum+2) {
//					checkInInfoMap.put("nextGold", mapDay.get(k));
//				}
//			}
//		}
//		mapList.sort(new Comparator<Map<Object, Object>>() {
//			@Override
//			public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
//				return (int)o1.get("day") - (int)o2.get("day");
//			}
//		});
//		resultMap.put("list", mapList);
//		resultMap.put("checkInInfo", checkInInfoMap);
//		return resultMap;
//	}
//	
//	/**
//	 * 通过签到奖励数据看是否已签到过
//	 * @param date
//	 * @param list
//	 * @return 1=已签到；0=未签到
//	 */
//	public int getCheckInFlag(String date, List<AwardHisVo> list) {
//		for (AwardHisVo awardHisVo : list) {
//			String createDate = awardHisVo.getCreateDate().substring(0, 10);
//			if(createDate.equals(date)) {
//				return 1;
//			}
//		}
//		return 0;
//	}
	
//	/**
//	 * 计算预期获得金币值
//	 * @param phoneNum
//	 * @return 可预期获得金币值
//	 */
//	public int calAwardGold(String phoneNum) {
//		//用户数据
//		AppUserVo appUserVo = this.appUserDao.selectByPhoneNum(phoneNum);
//		//配置信息
//		AppConfigVo appConfigVo = this.appConfigDao.selectAppConfig();
//		//随机金币奖励
//		int gold = GlobalUtils.getRanomGold(appConfigVo.getCheckInGold());
//		String checkInConfig = appConfigVo.getCheckInConfig();
//		int userGold = Integer.valueOf(appUserVo.getGold());
//		//最终获得金币
//		int finalGlod = GlobalUtils.getContinueCheckInGold(gold, userGold, checkInConfig);
//		logger.info("phoneNum=" + phoneNum + ", userGold=" + userGold + ", 获得随机金币gold=" + gold
//				+ ", 获得金币glod=" + finalGlod + ", 最终余额result=" + (userGold + finalGlod));
//		return finalGlod;
//	}
	
	public Long selectCountByNewUserTask(Map<String, Object> map) {
		return this.awardHisDao.selectCountByNewUserTask(map);
	}

	public Long selectCountByDailyTask(Map<String, Object> map) {
		return this.awardHisDao.selectCountByDailyTask(map);
	}
	
	public AwardHisVo selectLastestAwardInfo(Map<String, Object> map) {
		return this.awardHisDao.selectLastestAwardInfo(map);
	}
	
	public List<AwardHisVo> queryStudents(String phoneNum) {
		return this.awardHisDao.selectStudents(phoneNum);
	}
	
	public List<AwardHisVo> queryStudentsDetail(String phoneNum) {
		return this.awardHisDao.selectStudentsDetail(phoneNum);
	}
	
	/**
	 * 查当日时限任务完成的次数
	 * @param map
	 * @return
	 */
	public Long getTimeTaskCountOfDay(Map<String, Object> map) {
		return this.awardHisDao.selectTimeTaskCountOfDay(map);
	}
	
	/**
	 * 获得当日最新的时限任务奖励数据
	 * @param map
	 * @return
	 */
	public AwardHisVo getLastestTimeTaskOfDay(Map<String, Object> map) {
		return this.awardHisDao.selectLastestTimeTaskOfDay(map);
	}
	
	/**
	 * 寒假活动-查询累计活动时间内用户邀请有效徒弟个数(返回有效徒弟贡献次数的数组)
	 * @param map
	 * @return
	 */
	public String holidayActivitySelectRealInviteUser(String phoneNum){
		Map<String, Object> mapParams = new HashMap<>();
		//活动起始时间
		String startTime = "2018-01-19 18:00:00";
		String endTime = "2018-02-02 18:00:00";
		mapParams.put("phoneNum", phoneNum);
		mapParams.put("startTime", startTime);
		mapParams.put("endTime", endTime);
		return this.awardHisDao.holidayActivitySelectRealInviteUser(mapParams);
	}

	/**
	 * 寒假活动-查询某用户当天已抽奖的次数
	 * @param phoneNum
	 * @return
	 */
	public String holidayActivitySelectLuckyDraw(String phoneNum) {
		Map<String, Object> map = new HashMap<>();
		//获取当天时间的零点
		String curTime = DateUtil.getCurrentDate() + " 00:00:00" ;
		//获取当天时间的23：59：59
		String tomorrowTime = DateUtil.getCurrentDate() + " 23:59:59";
		map.put("phoneNum", phoneNum);
		map.put("curTime", curTime);
		map.put("tomorrowTime", tomorrowTime);
		return this.awardHisDao.holidayActivitySelectLuckyDrawCount(map);
	}
	
	/**
	 * 寒假活动-计算预计收益
	 * @param realUserCount
	 * @return
	 */
	public double calAllIncome(Integer realUserCount) {
		if(realUserCount >0 && realUserCount < 4) return 0 ;
		else if (realUserCount >=4 && realUserCount < 10) return 4*2 ;
		else if (realUserCount >=10 && realUserCount < 20) return 10*1.8 ;
		else if (realUserCount >=20 && realUserCount < 40) return 20*1.8 ;
		else if (realUserCount >=40 && realUserCount < 60) return 40*1.9 ;
		else if (realUserCount >=60 && realUserCount < 100) return 60*1.9 ;
		else if (realUserCount >=100 && realUserCount < 300) return 100*1.9 ;
		else if (realUserCount >=300 && realUserCount < 500) return 300*1.9 ;
		else if (realUserCount >=500 && realUserCount < 800) return 500*2 ;
		else if (realUserCount >=800 && realUserCount < 1000) return 800*1.9 ;
		else if (realUserCount >=1000 && realUserCount < 1500) return 1000*2 ;
		else if (realUserCount >=1500 && realUserCount < 2000) return 1500*1.9 ;
		else if (realUserCount >=2000 && realUserCount < 3000) return 2000*1.9 ;
		else if (realUserCount >=3000 && realUserCount < 4000) return 3000*1.9 ;
		else if (realUserCount >=4000 && realUserCount < 5000) return 4000*1.9 ;
		else if (realUserCount >=5000) return 5000*2 ;
		return 0;
	}
}
