package com.yfax.webapi.htt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.utils.DateUtil;
import com.yfax.utils.JsonResult;
import com.yfax.utils.ResultCode;
import com.yfax.utils.StrUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.UserTaskDao;
import com.yfax.webapi.htt.vo.AppConfigVo;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.AwardHisVo;
import com.yfax.webapi.htt.vo.TraceHisVo;
import com.yfax.webapi.htt.vo.UserTaskVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户任务记录
 * 
 * @author Minbo
 */
@Service
public class UserTaskService {

	protected static Logger logger = LoggerFactory.getLogger(UserTaskService.class);

	@Autowired
	private UserTaskDao userTaskDao;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private TraceHisService traceHisService;
	@Autowired
	private AwardHisService awardHisService;

	public UserTaskVo selectUserTask(UserTaskVo userTaskVo) {
		return this.userTaskDao.selectUserTask(userTaskVo);
	}
	
	public UserTaskVo selectNewUserTask(UserTaskVo userTaskVo) {
		return this.userTaskDao.selectNewUserTask(userTaskVo);
	}
	
	public UserTaskVo selectDailyUserTask(UserTaskVo userTaskVo) {
		return this.userTaskDao.selectDailyUserTask(userTaskVo);
	}

	public boolean addUserTask(UserTaskVo userTaskVo) {
		try {
			return this.userTaskDao.insertUserTask(userTaskVo);
		} catch (Exception e) {
			logger.error("新增用户任务异常：" + e.getMessage(), e);
			return false;
		}
	}

	public boolean modifyUserTask(UserTaskVo userTaskVo) {
		try {
			return this.userTaskDao.updateUserTask(userTaskVo);
		} catch (Exception e) {
			logger.error("更新用户任务异常：" + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 用户任务完成状态标识处理
	 * @param phoneNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public JsonResult processUserTaskInfo(String phoneNum, String versionCode, HttpServletRequest request) {
		// 判断请求类型 
		boolean isIosReq = false;
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if (name.toUpperCase().equals("USER-AGENT") && request.getHeader(name).contains("iPhone")) {
				String content = request.getHeader(name).toString();
				if (content.startsWith(GlobalUtils.IOS_PRO_NAME)) {
					isIosReq = true;
					break;
				}
			}
		}
		Map<Object, Object> mapResult = new HashMap<>();
		try {
			// 1. 取任务配置数据
			AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
			String params = appConfigVo.getParams().toString();
			JSONObject jsonObject = JSONObject.fromObject(params);
			Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
			String cTime = DateUtil.getCurrentLongDateTime();
			for (Entry<String, Object> entry : mapJson.entrySet()) {
				if (entry.getKey().toString().equals("newUserTaskConfig")) {
					JSONArray jsonArray = JSONArray.fromObject(entry.getValue().toString());
					List<Object> list = new ArrayList<>();
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						UserTaskVo userTaskVo = new UserTaskVo();
						userTaskVo.setPhoneNum(phoneNum);
						userTaskVo.setTaskId(StrUtil.null2Str(jsonObj.get("id")));
						userTaskVo = this.userTaskDao.selectNewUserTask(userTaskVo);
						if (userTaskVo == null) {
							userTaskVo = this.addUserTaskVo(phoneNum, jsonObj, cTime, 1);	// 新手任务
						}
						Map<Object, Object> map = new HashMap<>();
						map.put("id", userTaskVo.getTaskId());
						map.put("status", userTaskVo.getStatus());
						list.add(map);
					}
					mapResult.put("newUserTaskConfig", list);
				}

				if (entry.getKey().toString().equals("dailyTaskConfig")) {
					List<Object> list = new ArrayList<>();
					JSONArray jsonArray = JSONArray.fromObject(entry.getValue().toString());
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						String taskId = StrUtil.null2Str(jsonObj.get("id"));
						//版本号处理
						if(this.processVersionCode(taskId, isIosReq, versionCode)) {
							continue;
						}
						UserTaskVo userTaskVo = new UserTaskVo();
						userTaskVo.setPhoneNum(phoneNum);
						userTaskVo.setTaskId(taskId);
						userTaskVo.setCurrentTime(DateUtil.getCurrentDate());
						userTaskVo = this.userTaskDao.selectDailyUserTask(userTaskVo);
						if (userTaskVo == null) {
							userTaskVo = this.addUserTaskVo(phoneNum, jsonObj, cTime, 2);	// 日常任务
						}
						Map<Object, Object> map = new HashMap<>();
						map.put("id", userTaskVo.getTaskId());
						map.put("status", userTaskVo.getStatus());
						if((userTaskVo.getTaskId().equals(GlobalUtils.TASK_TYPE_VIDEO) 
								|| userTaskVo.getTaskId().equals(GlobalUtils.TASK_TYPE_MONEY)) 
								&& userTaskVo.getStatus() != 2) {
							map.put("status", this.processTimeTaskStatus(userTaskVo, map));
						}
						list.add(map);
					}
					mapResult.put("dailyTaskConfig", list);
				}
			}
			
			//2. 更新用户任务奖励状态（绑定手机号/拜师）
			AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
			if(appUserVo.getIsMaster() == 1) {
				this.finishBindToMasterTask(phoneNum);
			}
			if(appUserVo.getIsBindPhoneNum() == 1) {
				this.finishBindToPhoneNumTask(phoneNum);
			}
		} catch (Exception e) {
			logger.error("用户任务完成状态标识处理异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
		return new JsonResult(ResultCode.SUCCESS, mapResult);
	}
	
	/**
	 * 处理版本号逻辑
	 * @param versionCode
	 * @return true=continue, false=继续执行
	 */
	private boolean processVersionCode(String taskId, boolean isIosReq, String versionCode) {
		//旧版本为空
		if(StrUtil.null2Str(versionCode).equals("")) {
			//看视频5分钟
			if(taskId.equals("d004")) {
				return true;	//跳过处理
			}
			//领红包赚金币
			if(taskId.equals("d005")) {
				return true;	//跳过处理
			}
			//看视频赚金币
			if(taskId.equals(GlobalUtils.TASK_TYPE_VIDEO)) {
				return true;	//跳过处理
			}
			//领红包赚金币
			if(taskId.equals(GlobalUtils.TASK_TYPE_MONEY)) {
				return true;	//跳过处理
			}
			
		}else {
			try {
				int versionCodeTmp = Integer.valueOf(versionCode);
				//如果是IOS请求
				if(isIosReq) {
					//看视频赚金币
					if(taskId.equals(GlobalUtils.TASK_TYPE_VIDEO) && versionCodeTmp<19) {
						return true;	//跳过处理
					}
					//玩游戏
					if(taskId.equals("d009")) {
						return true;	//跳过处理
					}
					
				}else {
					if(versionCodeTmp<=18) {
						//看视频赚金币
						if(taskId.equals(GlobalUtils.TASK_TYPE_VIDEO)) {
							return true;	//跳过处理
						}
						//领红包赚金币
						if(taskId.equals(GlobalUtils.TASK_TYPE_MONEY)) {
							return true;	//跳过处理
						}
					}else if(versionCodeTmp<=20) {
						//领红包赚金币
						if(taskId.equals(GlobalUtils.TASK_TYPE_MONEY)) {
							return true;	//跳过处理
						}
					}
				}
			} catch (Exception e) {
				logger.error("版本号值类型异常：" + e.getMessage(), new RuntimeException("值类型不对"));
				return true;	//跳过处理
			}
		}
		return false;	//继续执行
	}
	
	/**
	 * 处理时限次数任务的标识
	 */
	public Integer processTimeTaskStatus(UserTaskVo userTaskVo, Map<Object, Object> map) {
		Map<String, Object> mapParams = new HashMap<>();
		mapParams.put("phoneNum", userTaskVo.getPhoneNum());
		mapParams.put("currentTime", DateUtil.getCurrentDate());
		mapParams.put("taskId", userTaskVo.getTaskId());
		long count = this.awardHisService.getTimeTaskCountOfDay(mapParams);
		if(count >= userTaskVo.getParamDailyCount()) {
			userTaskVo.setStatus(2);
			return 2;	//已完成，不能再继续
		}else {
			//初始状态
			if(map != null && userTaskVo.getStatus() == 0) {	
				long left = userTaskVo.getParamDailyCount() - count;
				map.put("left", left);
				if(left>0) {
					this.getTimeTaskInterval(userTaskVo, mapParams, map);
				}
			}
			return userTaskVo.getStatus();
		}
	}
	
	/**
	 * 处理时限间隔时间
	 */
	public void getTimeTaskInterval(UserTaskVo userTaskVo, Map<String, Object> mapParams, Map<Object, Object> map) {
		AwardHisVo awardHisVo = this.awardHisService.getLastestTimeTaskOfDay(mapParams);
		if(awardHisVo != null) {
			String createDate = awardHisVo.getCreateDate().substring(0, 19);
			Date endDate = DateUtils.addSeconds(DateUtil.parseLongDateTime(createDate), userTaskVo.getParamInterval());
			String currentDate = DateUtil.getCurrentLongDateTime();
			logger.info("phoneNum=" + userTaskVo.getPhoneNum() + "，currentDate=" + currentDate + ", createDate=" + createDate
					+ ", ---> endDate=" + DateUtil.formatLongDate(endDate));
			long[] dates = DateUtil.getDistanceTimes(currentDate, DateUtil.formatLongDate(endDate));
			logger.info("phoneNum=" + userTaskVo.getPhoneNum() + "，day=" + dates[0] 
					+ "，hour=" + dates[1] + "，min=" + dates[2] + "，sec=" + dates[3]);
			
			if(DateUtil.parseLongDateTime(currentDate).after(endDate)) {
				userTaskVo.setStatus(0);		//可重新开始任务了
				logger.info("phoneNum=" + userTaskVo.getPhoneNum() 
					+ ", 间隔时间已过，时段任务可以重新开始了。taskId=" + userTaskVo.getTaskId());
				this.getRestTime(map, userTaskVo.getParamInterval() / 60, 0, userTaskVo);
				
			}else {
				//多一秒间隔，防止时间重合获取不到奖励
				this.getRestTime(map,  dates[2], (dates[3] + 1), userTaskVo);
				userTaskVo.setStatus(3);		//倒计时中
			}
		}else {
			this.getRestTime(map, userTaskVo.getParamInterval() / 60, 0, userTaskVo);
		}
	}
	
	/**
	 * 返回剩余时间
	 * @param map
	 * @param min
	 * @param sec
	 */
	private void getRestTime(Map<Object, Object> map, long min, long sec, UserTaskVo userTaskVo) {
		HashMap<String, Object> timeMap = new HashMap<>();
		timeMap.put("min", min);
		timeMap.put("sec", sec);
		map.put("restTime", timeMap);
		logger.info("phoneNum=" + userTaskVo.getPhoneNum() + ", taskId=" + userTaskVo.getTaskId() 
			+ "，返回剩余时间timeMap=" + timeMap.toString());
	}
	
	/**
	 * 处理时限次数是否能更新
	 */
	public boolean isTimeTaskInterval(UserTaskVo userTaskVo) {
		Map<String, Object> mapParams = new HashMap<>();
		mapParams.put("phoneNum", userTaskVo.getPhoneNum());
		mapParams.put("currentTime", DateUtil.getCurrentDate());
		mapParams.put("taskId", userTaskVo.getTaskId());
		AwardHisVo awardHisVo = this.awardHisService.getLastestTimeTaskOfDay(mapParams);
		if(awardHisVo != null) {
			String createDate = awardHisVo.getCreateDate().substring(0, 19);
			Date endDate = DateUtils.addSeconds(DateUtil.parseLongDateTime(createDate), userTaskVo.getParamInterval());
			String currentDate = DateUtil.getCurrentLongDateTime();
			logger.info("phoneNum=" + userTaskVo.getPhoneNum() + "，currentDate=" + currentDate + ", createDate=" + createDate
					+ ", ---> endDate=" + DateUtil.formatLongDate(endDate));
			if (DateUtil.parseLongDateTime(currentDate).before(endDate)) {
				logger.warn("phoneNum=" + userTaskVo.getPhoneNum() + "，间隔时间太短，不能完成此任务taskId=" 
						+ userTaskVo.getTaskId() + "，跳过处理。");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 初始化任务列表数据
	 * @param phoneNum
	 * @param jsonObj
	 * @param cTime
	 * @param taskType
	 * @return
	 */
	private UserTaskVo addUserTaskVo(String phoneNum, JSONObject jsonObj, String cTime, int taskType) {
		UserTaskVo userTaskVo = new UserTaskVo();
		userTaskVo.setId(UUID.getUUID());
		userTaskVo.setPhoneNum(phoneNum);
		userTaskVo.setTaskId(StrUtil.null2Str(jsonObj.get("id")));
		userTaskVo.setTaskTitle(StrUtil.null2Str(jsonObj.get("title")));
		userTaskVo.setTaskGold(StrUtil.null2Str(jsonObj.get("gold")));
		userTaskVo.setTaskDesc(StrUtil.null2Str(jsonObj.get("desc")));
		userTaskVo.setTaskTime(Integer.valueOf(StrUtil.null2Str(jsonObj.get("time"))));
		userTaskVo.setStatus(0);
		userTaskVo.setCreateDate(cTime);
		userTaskVo.setUpdateDate(cTime);
		userTaskVo.setTaskType(taskType);
		if(!StrUtil.null2Str(jsonObj.get("params")).equals("")) {
			JSONObject jsonObj2 = JSONObject.fromObject(jsonObj.get("params"));
			userTaskVo.setParamDailyCount(Integer.valueOf(StrUtil.null2Str(jsonObj2.get("dailyCount"))));
			userTaskVo.setParamInterval(Integer.valueOf(StrUtil.null2Str(jsonObj2.get("interval"))));
		}
		try {
			this.userTaskDao.insertUserTask(userTaskVo);
		} catch (Exception e) {
			logger.error("初始化任务列表数据异常：" + e.getMessage(), e);
		}
		return userTaskVo;
	}
	
	/**
	 * 任务状态变更
	 */
	public JsonResult modifyUserTaskStatus(String phoneNum, int taskType, String taskId,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		if(!StrUtil.null2Str(mId).equals("")  && !StrUtil.null2Str(tId).equals("") && !StrUtil.null2Str(sId).equals("") 
				&& !StrUtil.null2Str(uId).equals("")  && sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.warn("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&taskType=" + taskType + "&taskId=" + taskId 
					+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.warn("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "doTaskStatus");
			
		}else {
			logger.warn("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	
		//逻辑处理
		UserTaskVo userTaskVo = new UserTaskVo();
		userTaskVo.setPhoneNum(phoneNum);
		userTaskVo.setTaskId(taskId);
		//新手任务
		if(taskType == 1) {
			userTaskVo = this.userTaskDao.selectNewUserTask(userTaskVo);
			
		//日常任务
		}else if(taskType == 2) {
			userTaskVo.setCurrentTime(DateUtil.getCurrentDate());
			userTaskVo = this.userTaskDao.selectDailyUserTask(userTaskVo);
		}
		if (userTaskVo == null) {
			logger.warn("phoneNum=" + phoneNum + "，taskId=" + taskId + "任务为空，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		if(userTaskVo.getStatus() == 2) {
			logger.warn("phoneNum=" + phoneNum + "，taskId=" + taskId + "任务已完成，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		if(userTaskVo.getIsDeleted() == 1) {
			logger.warn("phoneNum=" + phoneNum + "，任务标识为已过期数据，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		//可领取状态
		if(userTaskVo.getStatus() == 1) {
			logger.warn("phoneNum=" + phoneNum + "，taskId=" + taskId + "任务状态为可领取，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		//时限任务处理
		if(taskId.equals(GlobalUtils.TASK_TYPE_VIDEO) || taskId.equals(GlobalUtils.TASK_TYPE_MONEY)) {
			boolean flag = this.isTimeTaskInterval(userTaskVo);
			if(flag) {
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
		}
		//初始状态下，才能进入可领取状态下
		//更新任务状态信息
		userTaskVo.setStatus(1);		//可领取
		userTaskVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
		boolean flag = false;
		try {
			flag = this.userTaskDao.updateUserTask(userTaskVo);
		} catch (Exception e) {
			logger.error("phoneNum=" + phoneNum + "，更新用户任务异常：" + e.getMessage(), e);
			flag = false;
		}
		if(flag) {
			return new JsonResult(ResultCode.SUCCESS);
		}else {
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
	}
	
	/**
	 * 任务奖励-首次成功收徒
	 * @param phoneNum
	 * @return
	 */
	public boolean finishFirstStuTask(String phoneNum) {
		return this.finishTask(phoneNum, "n002");
	}
	
	/**
	 * 任务奖励-拜师，填写邀请码
	 * @param phoneNum
	 * @return
	 */
	public boolean finishBindToMasterTask(String phoneNum) {
		return this.finishTask(phoneNum, "n003");
	}
	
	/**
	 * 任务奖励-绑定手机号
	 * @param phoneNum
	 * @return
	 */
	public boolean finishBindToPhoneNumTask(String phoneNum) {
		return this.finishTask(phoneNum, "n004");
	}
	
	/**
	 * 完成任务
	 * @param phoneNum
	 * @param taskId
	 * @return
	 */
	private boolean finishTask(String phoneNum, String taskId) {
		//任务列表记录
		UserTaskVo userTaskVo = new UserTaskVo();
		userTaskVo.setPhoneNum(phoneNum);
		userTaskVo.setTaskId(taskId);	
		userTaskVo = this.selectUserTask(userTaskVo);
		if(userTaskVo != null && userTaskVo.getStatus() != 0) {
			return false;
		}
		//初始化状态（0=初始化；1=可领取；2=已完成）
		if(userTaskVo != null && userTaskVo.getStatus() == 0) {	
			//更新任务状态信息
			userTaskVo.setStatus(1);		//可领取
			userTaskVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
			return this.modifyUserTask(userTaskVo);
		}
		return false;
	}
}