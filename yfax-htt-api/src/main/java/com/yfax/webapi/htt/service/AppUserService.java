package com.yfax.webapi.htt.service;

import java.util.Date;
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

import com.yfax.common.xinge.XgServiceApi;
import com.yfax.utils.AesUtil;
import com.yfax.utils.DateUtil;
import com.yfax.utils.HttpRequestUtil;
import com.yfax.utils.JsonResult;
import com.yfax.utils.JsonUtils;
import com.yfax.utils.NetworkUtil;
import com.yfax.utils.RandomNumberGenerator;
import com.yfax.utils.ResultCode;
import com.yfax.utils.StrUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.AppUserDao;
import com.yfax.webapi.htt.vo.AppConfigVo;
import com.yfax.webapi.htt.vo.AppUserMultiVo;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.InitConfigVo;
import com.yfax.webapi.htt.vo.InviteUserAwardVo;
import com.yfax.webapi.htt.vo.InviteUserHisVo;
import com.yfax.webapi.htt.vo.IpShareCodeVo;
import com.yfax.webapi.htt.vo.TimeHisVo;
import com.yfax.webapi.htt.vo.TraceHisVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户管理服务
 * @author Minbo
 */
@Service
public class AppUserService {
	
	protected static Logger logger = LoggerFactory.getLogger(AppUserService.class);
	
	@Autowired
	private AppUserDao appUserDao;
	@Autowired
	private TimeHisService timeHisService;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private InviteUserHisService inviteUserHisService;
	@Autowired
	private InviteUserAwardService inviteUserAwardService;
	@Autowired
	private IpShareCodeService ipShareCodeService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private AppUserMultiService appUserMultiService;
	@Autowired
	private TraceHisService traceHisService;
	@Autowired
	private InitConfigService initConfigService;
	@Autowired
	private LoginHisNewService loginHisNewService;
	
	public AppUserVo selectByInviteCode(String code) {
		return this.appUserDao.selectByInviteCode(code);
	}
	
	public AppUserVo selectByRealPhoneNum(String phoneNum) {
		return this.appUserDao.selectByRealPhoneNum(phoneNum);
	}
	
	public AppUserVo selectLoginByPhoneNum(String phoneNum) {
		AppUserVo appUserVo = this.selectByPhoneNum(phoneNum);
		if(appUserVo == null) {
			appUserVo = this.selectByRealPhoneNum(phoneNum);
		}
		return appUserVo;
	}
	
	public AppUserVo selectByPhoneNum(String phoneNum) {
		return this.appUserDao.selectByPhoneNum(phoneNum);
	}
	
	public AppUserVo selectByPhoneNumAndPwd(Map<String, Object> params) {
		return this.appUserDao.selectByPhoneNumAndPwd(params);
	}
	
	public List<AppUserVo> selectByRank() {
		return this.appUserDao.selectByRank();
	}
	
	public List<AppUserVo> selectByRankGold() {
		return this.appUserDao.selectByRankGold();
	}
	
	public Long selectByRankSum() {
		return this.appUserDao.selectByRankSum();
	}
	
	public Long selectByTodaySum(Map<String, Object> params) {
		return this.appUserDao.selectByTodaySum(params);
	}
	
//	public Long selectByTotalGold(Map<String, Object> params) {
//		return this.appUserDao.selectByTotalGold(params);
//	}
	
//	public Long selectByStudentTotalGold(String phoneNum) {
//		return this.appUserDao.selectByStudentTotalGold(phoneNum);
//	}
	
	public long selectByRestGold(String phoneNum) {
		return this.appUserDao.selectByRestGold(phoneNum);
	}
	
	@Transactional
	public boolean doLoginOut(String phoneNum) {
		return this.appUserDao.deleteByTokenId(phoneNum);
	}
	
	@Transactional
	public boolean addUser(AppUserVo appUserVo) {
		return this.appUserDao.insertUser(appUserVo);
	}

	@Transactional
	public boolean modifyUser(AppUserVo appUserVo) {
		return this.appUserDao.updateUser(appUserVo);
	}
	
	public long getAppUserCountOfRegisterIp(Map<String, Object> params) {
		return this.appUserDao.selectAppUserOfRegisterIp(params);
	}
	
	/**
	 * 查询imei绑定用户数
	 * @param imei
	 * @return
	 */
	public Long selectAppUserOfMeiCount(String imei) {
		return this.appUserDao.selectAppUserOfMeiCount(imei);
	}
	
	/**
	 * 用户注册
	 * @param phoneNum
	 * @param userPwd
	 * @param inviteCode
	 * @param isBindWechat
	 * @param isBindPhoneNum
	 * @param isBindAlipay
	 * @param request
	 * @param imei
	 * @param msgCode
	 * @return
	 */
	public JsonResult registerUser(String phoneNum, String userPwd, 
			String inviteCode, int isBindWechat, int isBindPhoneNum, int isBindAlipay, 
			HttpServletRequest request, String imei, String msgCode, String channel) {
		try {
			boolean isMaster = false;	//师徒有效标识
			AppUserVo appUserVoOfMaster = new AppUserVo();	//师傅个人信息
			if(!StrUtil.null2Str(inviteCode).equals("")) {
				appUserVoOfMaster = this.appUserDao.selectByInviteCode(inviteCode);
				if(appUserVoOfMaster == null) {
					logger.warn("phoneNum=" + phoneNum + "，inviteCode=" + inviteCode + "，无效验证码，不存在");
					return new JsonResult(ResultCode.SUCCESS_INVALID_CODE);
				}else{
					if(appUserVoOfMaster.getBlackList() == 1) {
						logger.warn("师傅的phoneNum=" + appUserVoOfMaster.getPhoneNum() + "是黑名单用户，不能绑定关系");
					}else {
						isMaster = true;
					}
				}
			}
			
			//1. 注册用户
			AppUserVo appUserVo = new AppUserVo();
			appUserVo.setPhoneNum(phoneNum);
			if(phoneNum.length() == 11) {
				//设置真正的手机号码
				appUserVo.setRealPhoneNum(phoneNum);		
			}
			appUserVo.setUserPwd(userPwd);
			appUserVo.setGold("0");
			appUserVo.setBalance("0.00");
			String cTime = DateUtil.getCurrentLongDateTime();
			appUserVo.setRegisterDate(cTime);
			appUserVo.setLastLoginDate(cTime);
			appUserVo.setUpdateDate(cTime);
			appUserVo.setIsBindWechat(isBindWechat);
			appUserVo.setIsBindPhoneNum(isBindPhoneNum);
			appUserVo.setIsBindAlipay(isBindAlipay);
			appUserVo.setRegisterIp(NetworkUtil.getIpAddress(request));
			appUserVo.setImei(imei);
			appUserVo.setMsgCode(msgCode);
			appUserVo.setChannel(channel);
			//设置师傅标识
			if(isMaster) {
				appUserVo.setIsMaster(1);
			}
			//生成邀请码
			String ownInviteCode = "";
			//五次生成机会，否则置空
			for (int i = 0; i < 5; i++) {
				ownInviteCode = RandomNumberGenerator.generateNumber();
				long count = this.appUserDao.selectIsInviteCodeExist(ownInviteCode);
				if(count == 0) {
					break;
				}
			}
			appUserVo.setInviteCode(ownInviteCode);
			boolean flag = this.appUserDao.insertUser(appUserVo);
			if(flag) {
				// 更新完成任务奖励
				this.userTaskService.finishBindToPhoneNumTask(phoneNum);
				
				//2. 增加一条时段记录，减掉配置时间，以便于第一次获得时段奖励
				AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
				//配置时间再减一个小时，确认能领取奖励
				boolean flag2 = this.timeHisService.addTimeHis(appUserVo.getPhoneNum(), 
						DateUtil.formatLongDate(DateUtils.addHours(new Date(), - appConfigVo.getTimeDuration() - 1)));
				if(!flag2) {
					logger.error("phoneNum=" + phoneNum + " 注册后记录timeHis时段记录失败，flag2=" + flag2, new RuntimeException("时段记录失败"));
				}
				
				//3. 绑定师徒关系
				if(isMaster) {
					this.initailMasStuAwardDetail(appUserVoOfMaster, cTime, phoneNum, 1, request, imei, msgCode);
				}else {
					//如果用户没有输入邀请码，则计算IP规则，看是否能绑定师傅账户
					boolean flagOfIpMaster = this.processInvite(NetworkUtil.getIpAddress(request), phoneNum, cTime, request);
					if(flagOfIpMaster) {
						AppUserVo appUserVo2 = new AppUserVo();
						appUserVo2.setPhoneNum(appUserVo.getPhoneNum());
						appUserVo2.setIsMaster(1);
						boolean resultOfIpMaster = this.appUserDao.updateUser(appUserVo2);
						if(!resultOfIpMaster) {
							logger.error("phoneNum=" + phoneNum + " 通过IP绑定师傅关系标识结果：resultOfIpMaster=" 
									+ resultOfIpMaster, new RuntimeException("IP绑定师傅关系失败"));
						}
					}
				}
				return new JsonResult(ResultCode.SUCCESS);
			}else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
		} catch (Exception e) {
			logger.error("用户注册服务异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
	}
	
	@Transactional
	public JsonResult linkToMasterUser(String phoneNum, String inviteCode, String mId, String tId, 
			String sId, String uId, HttpServletRequest request) {
		try {
			if(!StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") 
					&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("") 
					&& sId.length() == 20 && uId.length() == 32) {
				TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
				if(traceHisVo != null) {
					logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				String paramsStr = "?phoneNum=" + phoneNum + "&inviteCode=" + inviteCode + "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
				boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
				if(!flag) {
					logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				//记录历史
				this.traceHisService.addTraceHis(request, "doLinkToMasterUser");
				
			}else {
				logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
		
			//逻辑处理
			AppUserVo appUserVo = this.appUserDao.selectByPhoneNum(phoneNum);
			if(appUserVo.getIsMaster() == 1) {
				logger.info("用户已绑定了师徒关系，不能重复绑定，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_NOT_RIGHT);
			}
			
			boolean isMaster = false;	//师徒有效标识
			AppUserVo appUserVoOfMaster = new AppUserVo();	//师傅个人信息
			if(!StrUtil.null2Str(inviteCode).equals("")) {
				appUserVoOfMaster = this.appUserDao.selectByInviteCode(inviteCode);
				if(appUserVoOfMaster == null) {
					logger.warn("phoneNum=" + phoneNum + "，inviteCode=" + inviteCode + "，无效验证码，不存在");
					return new JsonResult(ResultCode.SUCCESS_INVALID_CODE);
				}else{
					if(appUserVoOfMaster.getPhoneNum().equals(phoneNum)) {
						logger.warn("phoneNum=" + phoneNum + "，inviteCode=" + inviteCode 
								+ "，不能自己绑定自身邀请码，跳过处理。");
						return new JsonResult(ResultCode.SUCCESS_NOT_RIGHT);
					}else {
						Map<String, String> map = new HashMap<>();
						map.put("masterPhoneNum", phoneNum);
						map.put("studentPhoneNum", appUserVoOfMaster.getPhoneNum());
						InviteUserHisVo inviteUserHisVo = this.inviteUserHisService.selectInviteUserByMasAndStuPhoneNum(map);
						if(inviteUserHisVo != null) {
							logger.warn("studentPhoneNum=" + phoneNum + ", masterPhoneNum=" + appUserVoOfMaster.getPhoneNum() 
							+ "，inviteCode=" + inviteCode + "，已经是师徒关系，不能互相绑定关系，跳过处理。");
							return new JsonResult(ResultCode.SUCCESS_DUPLICATE);
						}else {
							isMaster = true;
						}
					}
				}
			}else {
				logger.info("phoneNum=" + phoneNum + "，验证码参数错误，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			
			if(isMaster) {
				//1. 更新用户
				String cTime = DateUtil.getCurrentLongDateTime();
				appUserVo.setUpdateDate(cTime);
				//设置师傅标识
				appUserVo.setIsMaster(1);
				boolean flag = this.appUserDao.updateUser(appUserVo);
				if(flag) {
					//绑定师徒关系
					if(isMaster) {
						this.initailMasStuAwardDetail(appUserVoOfMaster, cTime, phoneNum, 2, request, mId, appUserVo.getMsgCode());
					}
					return new JsonResult(ResultCode.SUCCESS);
				}else {
					logger.error("更新师徒关系标识失败，请检查。flag=" + flag, new RuntimeException("更新失败"));
					return new JsonResult(ResultCode.SUCCESS_FAIL);
				}
			}else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
		} catch (Exception e) {
			logger.error("师徒关系绑定异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
	} 
	
	/**
	 * 初始化师徒奖励邀请数据
	 * @param appUserVoOfMaster 师傅信息
	 * @param cTime 当前时间
	 * @param studentPhoneNum	徒弟手机号码
	 * @param registerType	注册类型
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	private void initailMasStuAwardDetail(AppUserVo appUserVoOfMaster, String cTime, 
			String studentPhoneNum, Integer registerType, HttpServletRequest request, String studentImei, String studentMsgCode) {
		InviteUserHisVo inviteUserHisVo = new InviteUserHisVo();
		inviteUserHisVo.setId(UUID.getUUID());
		inviteUserHisVo.setMasterPhoneNum(appUserVoOfMaster.getPhoneNum());
		inviteUserHisVo.setStudentPhoneNum(studentPhoneNum);
		inviteUserHisVo.setCreateDate(cTime);
		inviteUserHisVo.setUpdateDate(cTime);
		inviteUserHisVo.setMasterRegisterIp(appUserVoOfMaster.getRegisterIp());
		inviteUserHisVo.setStudentRegisterIp(NetworkUtil.getIpAddress(request));
		inviteUserHisVo.setRegisterType(registerType);
		inviteUserHisVo.setMasterImei(appUserVoOfMaster.getImei());
		inviteUserHisVo.setStudentImei(studentImei);
		inviteUserHisVo.setStudentMsgCode(studentMsgCode);
		boolean flag = this.inviteUserHisService.addInviteUserHis(inviteUserHisVo);
		logger.info("师徒关系绑定结果：flag=" + flag);
		if(flag) {
			// 更新完成任务奖励
			this.userTaskService.finishBindToMasterTask(studentPhoneNum);
		}
		AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
		//初始化师徒奖励明细
		JSONObject jsonObject = JSONObject.fromObject(appConfigVo.getParams().toString());
		Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
		for (Entry<String, Object> entry : mapJson.entrySet()) {
			if(entry.getKey().toString().equals("inviteConfig")) {
				JSONArray jsonArray = JSONArray.fromObject(entry.getValue().toString());
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					String awardRule = StrUtil.null2Str(jsonObj.get("awardRule"));
					JSONArray awardRuleJsonArray = JSONArray.fromObject(awardRule);
					for (int j = 0; j < awardRuleJsonArray.size(); j++) {
						JSONObject awardRuleJsonObj = awardRuleJsonArray.getJSONObject(j);
						//处理
						int time = Integer.valueOf(StrUtil.null2Str(awardRuleJsonObj.get("time")));
						String descStr = StrUtil.null2Str(awardRuleJsonObj.get("desc"));
						String gold = StrUtil.null2Str(awardRuleJsonObj.get("gold"));
						this.addInviteUserAwardVo(appUserVoOfMaster.getPhoneNum(), studentPhoneNum, time, descStr, 
								gold, cTime);
					}
					
					String goldConfig = StrUtil.null2Str(jsonObj.get("goldConfig"));
					JSONArray goldConfigJsonArray = JSONArray.fromObject(goldConfig);
					for (int j = 0; j < goldConfigJsonArray.size(); j++) {
						JSONObject goldConfigJsonObj = goldConfigJsonArray.getJSONObject(j);
						this.addInviteUserAwardVo(appUserVoOfMaster.getPhoneNum(), studentPhoneNum, 0, "首次当天奖励", 
								StrUtil.null2Str(goldConfigJsonObj.get("firstAward")), cTime);
					}
				}
				break;
			}
		}
		//如果是2=登录后再邀请码绑定；3=大咖用户IP注册，则给师傅徒弟数+1
		if(flag) {
			if(registerType == 2 || registerType == 3) {
				this.inviteUserAwardService.processInviteUserAward(studentPhoneNum);
			}
		}
	}
	
	/**
	 * 初始化师徒奖励记录
	 * @param masterPhoneNum
	 * @param studentPhoneNum
	 * @param time
	 * @param descStr
	 * @param gold
	 * @param cTime
	 * @return
	 */
	private boolean addInviteUserAwardVo(String masterPhoneNum, String studentPhoneNum, int time, 
			String descStr, String gold, String cTime) {
		InviteUserAwardVo inviteUserAwardVo = new InviteUserAwardVo();
		inviteUserAwardVo.setId(UUID.getUUID());
		inviteUserAwardVo.setMasterPhoneNum(masterPhoneNum);
		inviteUserAwardVo.setStudentPhoneNum(studentPhoneNum);
		inviteUserAwardVo.setTime(time);
		inviteUserAwardVo.setDescStr(descStr);
		inviteUserAwardVo.setGold(gold);
		inviteUserAwardVo.setCreateDate(cTime);
		inviteUserAwardVo.setUpdateDate(cTime);
		boolean flag = this.inviteUserAwardService.addInviteUserAward(inviteUserAwardVo);
		logger.info("id=" + inviteUserAwardVo.getId()
			+ ", masterPhoneNum=" + inviteUserAwardVo.getMasterPhoneNum()
			+ ", studentPhoneNum=" + inviteUserAwardVo.getStudentPhoneNum()
			+ ", time=" + inviteUserAwardVo.getTime()
			+ ", descStr=" + inviteUserAwardVo.getDescStr() 
			+ ", gold=" + inviteUserAwardVo.getGold() 
			+ ", DB操作结果flag=" + flag);
		return flag;
	}
	
	/**
	 * 处理大咖的注册用户IP绑定的被邀请逻辑
	 * @throws Exception 
	 */
	private boolean processInvite(String sourceIp, String studentPhoneNum, String cTime, HttpServletRequest request){
		 logger.info("判断是否是由其他人IP邀请加入的...");
		 Map<String, Object> map = new HashMap<>();
		 map.put("sourceIp", sourceIp);
		 IpShareCodeVo ipShareCodeVo = this.ipShareCodeService.selectIpShareCodeIsFromIp(map);
		 if(ipShareCodeVo != null) {
			 String currentDate = DateUtil.getCurrentLongDateTime();
			 String createDate = ipShareCodeVo.getCreateDate().substring(0, 19);
			 Date endDate = DateUtils.addHours(DateUtil.parseLongDateTime(createDate), 3);
			 if(DateUtil.parseLongDateTime(currentDate).after(endDate)) {
				 logger.warn("邀请时间已超过三小时，跳过处理。");
				 return false;
			 }
			 try {
				 //天翼空间-任务记录
				 if(!StrUtil.null2Str(ipShareCodeVo.getTyToken()).equals("") 
						 && !StrUtil.null2Str(ipShareCodeVo.getTyTaskCode()).equals("") ) {
					 //未完成记录
					 if(ipShareCodeVo.getTyIsFinished() != null && ipShareCodeVo.getTyIsFinished() == 0
							 && StrUtil.null2Str(ipShareCodeVo.getTyTaskCode()).equals("A_0001")) {
						 this.processTianYiRequest(ipShareCodeVo, cTime);
					 }
				 }
			 } catch (Exception e) {
				 logger.error("天翼空间-任务记录处理异常：" + e.getMessage(), e);
			 }
			 return this.processDakaRequest(ipShareCodeVo, studentPhoneNum, cTime, request);
		 }
		 return false;
	}
	
	/**
	 * 处理大咖的IP绑定注册用户
	 */
	public boolean processDakaRequest(IpShareCodeVo ipShareCodeVo, String studentPhoneNum, 
			String cTime, HttpServletRequest request) {
		logger.info("获取到是由IP邀请的。ipShareCodeVo=" + ipShareCodeVo.toString());
		 //师傅账户信息
		 AppUserVo appUserVoOfMaster = this.appUserDao.selectByInviteCode(ipShareCodeVo.getShareCode());
		 if(appUserVoOfMaster != null) {
			 logger.info("获取到师傅信息。appUserVoOfMaster=" + appUserVoOfMaster.toString());
			 //更新ip与邀请码的isUsed标识位
			 ipShareCodeVo.setIsUsed(2);		//该记录已使用
			 ipShareCodeVo.setUpdateDate(cTime);
			 try {
				 boolean flag4 = this.ipShareCodeService.updateIpShareCode(ipShareCodeVo);
				 if(flag4) {
					 logger.info("该账户phoneNum=" + studentPhoneNum + " 成功绑定到了大咖师傅名下。");
					 this.initailMasStuAwardDetail(appUserVoOfMaster, cTime, studentPhoneNum, 3, request, null, null);
					 return true;
				 }else {
					 logger.error("邀请人信息更新失败，请检查", new RuntimeException("flag4=" + flag4));
					 return false;
				 }
		 	 } catch (Exception e) {
				logger.error("注册时邀请处理异常：" + e.getMessage(), e);
				return false;
			 }
		 }
		 return false;
	}
	
	private void processTianYiRequest(IpShareCodeVo ipShareCodeVo, String cTime) {
		logger.info("天翼空间过来的记录。ipShareCodeVo=" + ipShareCodeVo.toString());
		 //更新ip与邀请码的isUsed标识位
		 
		 String url = "xxx";		//正式接口地址
		 
		 String result = "";
		 int tyIsFinished = 0;
		 try {
			String params = "userid=" + AesUtil.userid + "&sig=" + AesUtil.getTySig(ipShareCodeVo.getTyToken());
			result = HttpRequestUtil.sendPost(url, params);
			logger.info("params=" + params + ", result=" + result);
			if(result.contains("200") && result.contains("任务完成")) {
				tyIsFinished = 1;
			}
		} catch (Exception e) {
			logger.error("天翼空间请求处理异常：" + e.getMessage(), new RuntimeException("报错"));
			tyIsFinished = 0;
		}
		//回调接口，告知任务完成结果
		ipShareCodeVo.setUpdateDate(cTime);
		ipShareCodeVo.setTyResult(result);
		ipShareCodeVo.setTyIsFinished(tyIsFinished);
		boolean flag = this.ipShareCodeService.updateIpShareCode(ipShareCodeVo);
		logger.info("天翼空间，任务完成结果result=" + result + ", 更新数据记录flag=" + flag);
	}
	
	/**
	 * rest推送测试用
	 */
	public String testPushNotify(String phoneId, int type) {
		String result = "";
		Map<String, Object> map = new HashMap<>();
		map.put("gold", "2000");
		map.put("balance", "0.2");
		JSONObject json = JSONObject.fromObject(map);
		if(type == 1) {
//			result =  XgServiceApi.pushNotify(phoneId, "通知栏消息", "恭喜获得奖励，任务[X]已完成，获得收益：XXX元", GlobalUtils.XG_ACCESS_ID, GlobalUtils.XG_SECRET_KEY);
			result = XgServiceApi.pushNotify(phoneId, "通知栏消息", json.toString(), GlobalUtils.XG_ACCESS_ID, GlobalUtils.XG_SECRET_KEY);
		}else if(type == 2){
//			result =  XgServiceApi.pushNotifyByMessage(phoneId, "透传消息", "恭喜获得奖励，任务[X]已完成，获得收益：XXX元", GlobalUtils.XG_ACCESS_ID, GlobalUtils.XG_SECRET_KEY);
			result = XgServiceApi.pushNotifyByMessage(phoneId, "透传消息", json.toString(), GlobalUtils.XG_ACCESS_ID, GlobalUtils.XG_SECRET_KEY);;
		}
		logger.info("推送通知给用户[phoneId=" + phoneId + ", type=" + type + "]，推送发送结果result=" + result);
		return result;
	}
	
	/**
	 * 个人信息处理
	 * @param phoneNum
	 * @param imei
	 * @param traceId
	 * @param request
	 * @return
	 */
	public JsonResult getUserInfo(String phoneNum, Integer source, String versionCode,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		//是否通过请求校验
		boolean isReqPassAuth = false;
		if(!StrUtil.null2Str(source).equals("") 
				&& !StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") 
				&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("") 
				&& sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.warn("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&source=" + source
					+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.warn("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "queryUserInfo");
			isReqPassAuth = true;
		}else {
			logger.warn("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		// 放行特定IMEI的值
		boolean isGo = false;
		if(StrUtil.null2Str(mId).equals(GlobalUtils.APP_FREE_IMEI)) {
			isGo = true;
		}
		if(!isGo) {
			//条件判断
			Map<String, Object> paramsCondition = new HashMap<>();
			paramsCondition.put("imei", mId);
			paramsCondition.put("phoneNum", phoneNum);
			paramsCondition.put("currentTime", DateUtil.getCurrentDate());
			long phoneNumCount = this.loginHisNewService.queryCountOfLoginHisNewImei(paramsCondition);
			if(phoneNumCount >= 2) {
				logger.warn("phoneNum=" + phoneNum + "，该设备imei=" + mId 
						+ "，该设备已登录过多个账号，请用原账号登录。");
				return new JsonResult(ResultCode.SUCCESS_REGISTER_LIMIT);
			}
		}
		
		//组装个人信息
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("phoneNum", phoneNum);
		paramsMap.put("currentTime", DateUtil.getCurrentDate());
		Map<String, Object> resultMap = new HashMap<>();
		AppUserVo appUserVo = this.selectLoginByPhoneNum(phoneNum);
		if(appUserVo == null) {
			logger.warn("phoneNum=" + phoneNum + "，该账号不存在（提示未登录），跳过处理。");
			return new JsonResult(ResultCode.NOT_LOGIN);
		}
		if (appUserVo.getBlackList() == 1) {
			logger.warn("phoneNum=" + phoneNum + "，黑名单用户，禁止登陆。");
			return new JsonResult(ResultCode.SUCCESS_USER_SUSPICIOUS, "您的账号状态异常。如需帮助，请联系客服，"
					+ "以免影响您的收益(" + appUserVo.getInviteCode() + ")");
		}
		//今日金币
		Long todayGlod = this.selectByTodaySum(paramsMap);
		appUserVo.setTodayGlod(todayGlod);
		
		if(StrUtil.null2Str(appUserVo.getTabArticle()).equals("") 
				|| StrUtil.null2Str(appUserVo.getTabArticle()).equals("[]")
				|| (!StrUtil.null2Str(source).equals("") && source != appUserVo.getSource())) {
			//取初始Tabs配置数据，如果初次为空或者源切换，则重新初始化
			InitConfigVo initConfigVo = this.initConfigService.selectInitConfig();
			JSONObject obj = JSONObject.fromObject(initConfigVo.getSourceParams().toString());
			String sourceConfig = StrUtil.null2Str(obj.get("source"));
			if (!sourceConfig.equals("")) {
				JSONArray datajsonArray = JSONArray.fromObject(sourceConfig);
				for (int j = 0; j < datajsonArray.size(); j++) {
					JSONObject datjsonObj = datajsonArray.getJSONObject(j);
					int id = datjsonObj.getInt("id");
					if(id == source) {
						AppUserVo appUserVo2 = new AppUserVo();
						appUserVo2.setPhoneNum(appUserVo.getPhoneNum());
						appUserVo2.setTabArticle(datjsonObj.get("tabArticle").toString());
						appUserVo2.setTabVideo(datjsonObj.get("tabVideo").toString());
						appUserVo.setTabArticle(appUserVo2.getTabArticle());
						appUserVo.setTabVideo(appUserVo2.getTabVideo());
						appUserVo.setSourceAppId(datjsonObj.get("appId").toString());
						appUserVo.setSource(source);
						String cTime = DateUtil.getCurrentLongDateTime();
						appUserVo2.setUpdateDate(cTime);
						appUserVo2.setSourceAppId(appUserVo.getSourceAppId());
						appUserVo2.setSource(source);
						appUserVo2.setVersionCode(versionCode);
						appUserVo2.setVersion(appUserVo.getVersion());
						boolean flag = this.modifyUser(appUserVo2);
						if (!flag) {
							logger.error("phoneNum=" + phoneNum + "，更新用户新闻标签数据flag=" + flag 
									+ ", desc=" + datjsonObj.get("desc") + ", appId=" + datjsonObj.get("appId"), new RuntimeException("更新用户新闻标签数据"));
						}
					}
				}
			}
		}
		if(!StrUtil.null2Str(appUserVo.getTabArticle()).equals("")) {
			appUserVo.setTabArticle(JsonUtils.JsonArrayToList(appUserVo.getTabArticle().toString()));
		}
		if(!StrUtil.null2Str(appUserVo.getTabVideo()).equals("")) {
			appUserVo.setTabVideo(JsonUtils.JsonArrayToList(appUserVo.getTabVideo().toString()));
		}
		//绑定相关信息
		Map<String, Object> bindInfo = new HashMap<>();
		AppUserMultiVo appUserMultiVo = this.appUserMultiService.getAppUserBySystemUserId(appUserVo.getPhoneNum());
		if(appUserMultiVo != null) {
			bindInfo.put("systemUserId", appUserMultiVo.getSystemUserId());
			if(appUserVo.getIsBindPhoneNum() == 1) {
				if(StrUtil.null2Str(appUserMultiVo.getOwnPhoneNum()).equals("")) {
					bindInfo.put("ownPhoneNum", appUserVo.getPhoneNum());
				}else {
					bindInfo.put("ownPhoneNum", appUserMultiVo.getOwnPhoneNum());
				}
			}
			bindInfo.put("wechatOpenId", appUserMultiVo.getWechatOpenId());
			bindInfo.put("wechatNickname", appUserMultiVo.getWechatNickname());
			bindInfo.put("wechatHeadImgurl", appUserMultiVo.getWechatHeadImgurl());
			bindInfo.put("wechatName", appUserMultiVo.getWechatName());
			bindInfo.put("wechatPhoneNum", appUserMultiVo.getWechatPhoneNum());
			bindInfo.put("telPhoneNum", appUserMultiVo.getTelPhoneNum());
			bindInfo.put("alipayAccount", appUserMultiVo.getAlipayAccount());
			bindInfo.put("alipayName", appUserMultiVo.getAlipayName());
			
		}else {
			bindInfo.put("systemUserId", appUserVo.getPhoneNum());
			bindInfo.put("ownPhoneNum", appUserVo.getPhoneNum());
			bindInfo.put("wechatOpenId", null);
			bindInfo.put("wechatNickname", null);
			bindInfo.put("wechatHeadImgurl", null);
			bindInfo.put("wechatName", null);
			bindInfo.put("wechatPhoneNum", null);
			bindInfo.put("telPhoneNum", null);
			bindInfo.put("alipayAccount", null);
			bindInfo.put("alipayName", null);
		}
		appUserVo.setBindInfo(bindInfo);
		//有师父信息，才处理
		if(appUserVo.getIsMaster() == 1 && appUserVo.getIsInviteStep() == 0 
				&& appUserVo.getIsMasterFinished() ==0 && isReqPassAuth) {
			//处理师徒邀请贡献奖励
			this.inviteUserAwardService.processInviteUserAward(appUserVo.getPhoneNum());
		}
		appUserVo.setUserPwd(null); //设置为空，去掉密码
		if(StrUtil.null2Str(appUserVo.getRealPhoneNum()).equals("")) {
			appUserVo.setRealPhoneNum(appUserVo.getPhoneNum());
		}
		//个人信息数据
		resultMap.put("appUserVo", appUserVo);
		//是否更新版本号
		if(appUserVo.getVersionCode() == null || 
				!StrUtil.null2Str(versionCode).equals(StrUtil.null2Str(appUserVo.getVersionCode()))) {
			AppUserVo appUserVo2 = this.selectLoginByPhoneNum(phoneNum);
			appUserVo2.setVersionCode(versionCode);
			boolean flag = this.modifyUser(appUserVo2);
			if (!flag) {
				logger.error("phoneNum=" + phoneNum + ", 版本信息更新失败", new RuntimeException("版本字段更新失败，flag=" + flag));
			}
		}
		return new JsonResult(ResultCode.SUCCESS, resultMap);
	}
}
