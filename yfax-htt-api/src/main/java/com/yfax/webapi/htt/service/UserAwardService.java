package com.yfax.webapi.htt.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.utils.DateUtil;
import com.yfax.utils.ImgCodeUtil;
import com.yfax.utils.JsonResult;
import com.yfax.utils.ResultCode;
import com.yfax.utils.StrUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.vo.AppConfigVo;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.AwardHisVo;
import com.yfax.webapi.htt.vo.ImgCodeVo;
import com.yfax.webapi.htt.vo.ReadHisVo;
import com.yfax.webapi.htt.vo.TimeHisVo;
import com.yfax.webapi.htt.vo.TraceHisVo;
import com.yfax.webapi.htt.vo.UserTaskVo;
import com.yfax.webapi.htt.vo.VideoHisVo;

/**
 * 用户奖励服务
 * 
 * @author Minbo
 */
@Service
public class UserAwardService {
	
	protected static Logger logger = LoggerFactory.getLogger(UserAwardService.class);
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AwardHisService awardHisService;
	@Autowired
	private ReadHisService readHisService;
	@Autowired
	private AppConfigService appConfigService;
	@Autowired
	private TimeHisService timeHisService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private TraceHisService traceHisService;
	@Autowired
	private ImgCodeService imgCodeService;
	@Autowired
	private VideoHisService videoHisService;
	
	/**
	 * 验证图形码
	 * @return
	 */
	@Transactional
	public JsonResult doVerifyImgCode(String phoneNum, String imgCode,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		
		if(!StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") 
				&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("")
				&& sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&imgCode=" + imgCode + "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "doVerifyImgCode");
			
		}else {
			logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		//逻辑处理
		ImgCodeVo imgCodeVo = new ImgCodeVo();
		imgCodeVo.setPhoneNum(phoneNum);
		imgCodeVo.setImgCode(imgCode);
		imgCodeVo = this.imgCodeService.getImgCodeByPhoneNum(imgCodeVo);
		if(imgCodeVo == null) {
			logger.info("phoneNum=" + phoneNum + "，验证码记录不存在，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		if(imgCodeVo.getIsUsed() == 1) {
			logger.info("phoneNum=" + phoneNum + "，验证码记录已被使用过，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		String createDate = imgCodeVo.getCreateDate().substring(0, 19);
	    //60秒内的验证码才有效
	    Date endDate = DateUtils.addSeconds(DateUtil.parseLongDateTime(createDate), 60);
	    String currentDate = DateUtil.getCurrentLongDateTime();
	    logger.info("phoneNum=" + phoneNum + "，currentDate=" + currentDate + ", createDate=" + createDate 
	            + ", ---> endDate=" + DateUtil.formatLongDate(endDate));
	    if(DateUtil.parseLongDateTime(currentDate).before(endDate)) {
	    		boolean flag = this.imgCodeService.modifyImgCode(imgCodeVo.getId());
			if(flag) {
				return new JsonResult(ResultCode.SUCCESS);
			}else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
	    }
		return new JsonResult(ResultCode.SUCCESS_FAIL);
	}
	
	/**
	 * 时段奖励
	 * @param phoneNum
	 * @return
	 */
	public JsonResult doTimeAward(String phoneNum,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		if(!StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") 
				&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("")
				&& sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "doTimeAward");
			
		}else {
			logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
		if(appUserVo == null) {
			logger.warn("phoneNum=" + phoneNum + "，用户不存在，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		//如果为黑名单用户，直接返回已获取奖励，不继续逻辑处理
		if(appUserVo.getBlackList() == 1) {
			logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，黑名单用户，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		//逻辑处理
		String currentDate = DateUtil.getCurrentLongDateTime();
		TimeHisVo timeHisVo = this.timeHisService.selectTimeHisDate(appUserVo.getPhoneNum());
		if(timeHisVo == null) {
			// 增加一条时段记录，减掉配置时间，以便获得时段奖励
			AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
			boolean flag = this.timeHisService.addTimeHis(appUserVo.getPhoneNum(), 
					DateUtil.formatLongDate(DateUtils.addHours(new Date(), - appConfigVo.getTimeDuration() - 1)));
			if(!flag) {
				logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，无时段奖励timeHis历史记录，要新增记录", 
						new RuntimeException("为空的时段记录，新增失败了，flag="  + flag));
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}else {
				timeHisVo = this.timeHisService.selectTimeHisDate(appUserVo.getPhoneNum());
			}
		}
		
		//逻辑处理
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("phoneNum", appUserVo.getPhoneNum());
		paramsMap.put("awardType", GlobalUtils.AWARD_TYPE_TIME);
		AwardHisVo awardHisVo = this.awardHisService.selectLastestAwardInfo(paramsMap);
		if(awardHisVo != null) {
			String createDate = awardHisVo.getCreateDate().substring(0, 19);
			long[] dates = DateUtil.getDistanceTimes(DateUtil.getCurrentLongDateTime(), createDate);
			logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，day=" + dates[0]  + "，hour=" + dates[1] 
					+ "，min=" + dates[2] + "，sec=" + dates[3]);
			if(dates[0] == 0 && dates[1] == 0 && dates[2] == 0 && dates[3] <= 10) {
				logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，太频繁的调用（时段奖励），跳过处理");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
		}
		
		paramsMap.put("phoneNum", appUserVo.getPhoneNum());
		AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
		String loginHisDate = timeHisVo.getCreateDate().substring(0, 19);
		Date endDate = DateUtils.addHours(DateUtil.parseLongDateTime(loginHisDate), appConfigVo.getTimeDuration());
		logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，currentDate=" + currentDate + ", loginHisDate=" + loginHisDate 
				+ ", ---> endDate=" + DateUtil.formatLongDate(endDate));
		
		if(DateUtil.parseLongDateTime(currentDate).after(endDate)) {

			if(timeHisVo.getIsUsed() == 1) {
				boolean flag = this.timeHisService.addTimeHis(appUserVo.getPhoneNum(), 
						DateUtil.formatLongDate(DateUtils.addHours(new Date(), - appConfigVo.getTimeDuration() - 1)));
				logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，该条时段奖励timeHisId=" + timeHisVo.getId() 
					+ "的登记录标识是已领取状态，新增一条，跳过处理。新增标志结果flag=" + flag);
				return new JsonResult(ResultCode.SUCCESS_IS_NOTRIGHT_TIME);
			}
			
			//1. 更新已使用
			timeHisVo.setIsUsed(1);	//已使用
			timeHisVo.setUpdateDate(currentDate);
			boolean flag = this.timeHisService.modifyTimeHis(timeHisVo);
			if(flag) {
				//可获得奖励
				JsonResult result = this.awardHisService.addAwardHis(appUserVo.getPhoneNum(), appConfigVo.getTimeGold()
						, GlobalUtils.AWARD_TYPE_TIME, null, null, null, null, null, null, null, null, null, null, null);
				if(!result.getCode().equals("200")) {
					logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，发放时段奖励失败，跳过处理", new RuntimeException("时段奖励发放失败"));
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				
				//2. 新增一条记录
				String cTime = DateUtil.getCurrentLongDateTime();
				TimeHisVo timeHisVo2 = new TimeHisVo();
				String id = UUID.getUUID();
				timeHisVo2.setId(id);
				timeHisVo2.setPhoneNum(appUserVo.getPhoneNum());
				timeHisVo2.setIsUsed(0);//未使用
				timeHisVo2.setCreateDate(cTime);
				timeHisVo2.setUpdateDate(cTime);
				boolean flag2 = this.timeHisService.addTimeHis(timeHisVo2);
				if(flag2) {
					paramsMap.put("isAward", 1);				//已获得奖励
					Map<String, Object> timeMap = new HashMap<>();
					timeMap.put("min", appConfigVo.getTimeDuration() * 60);
					timeMap.put("sec", 0);
					paramsMap.put("restTime", timeMap);
					paramsMap.put("gold", appConfigVo.getTimeGold());
				}else {
					logger.error("phoneNum=" + appUserVo.getPhoneNum() +"，新增一条时段记录失败。timeHis id=" + id, 
							new RuntimeException("新增时段记录失败"));
				}
				
			}else {
				logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，获得时段奖励接口：更新标识时段记录失败。");
			}
			
		}else {
			long[] dates = DateUtil.getDistanceTimes(currentDate, DateUtil.formatLongDate(endDate));
			logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，hour=" + dates[1] + "，min=" + dates[2] + "，sec=" + dates[3]);
			
			//小时的话
			if(dates[1] == appConfigVo.getTimeDuration() && dates[2] == 0) {
				paramsMap.put("isAward", 0);				//未获得奖励
				HashMap<String, Object> timeMap = new HashMap<>();
				timeMap.put("min", appConfigVo.getTimeDuration() * 60);
				timeMap.put("sec", 0);
				paramsMap.put("restTime", timeMap);
				
			}else{
				paramsMap.put("isAward", 0);				//未可获得奖励
				HashMap<String, Object> timeMap = new HashMap<>();
				timeMap.put("min", dates[2]);
				timeMap.put("sec", (dates[3] + 1));	//多一秒间隔，防止时间重合获取不到奖励
				paramsMap.put("restTime", timeMap);
			}
			paramsMap.put("gold", 0);
		}
		logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，paramsMap=" + paramsMap.toString());
		return new JsonResult(ResultCode.SUCCESS, paramsMap);
	}
	
	/**
	 * 阅读奖励
	 * @param phoneNum
	 * @param primaryKey
	 * @return
	 */
	public JsonResult doReadAward(String phoneNum, String primaryKey,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		try {
			if(!StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") 
					&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("")
					&& sId.length() == 20 && uId.length() == 32) {
				TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
				if(traceHisVo != null) {
					logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				String paramsStr = "?phoneNum=" + phoneNum + "&primaryKey=" + primaryKey
						+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
				boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
				if(!flag) {
					logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				//记录历史
				this.traceHisService.addTraceHis(request, "doReadAward");
				
			}else {
				logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			
			AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
			if(appUserVo == null) {
				logger.warn("phoneNum=" + phoneNum + "，用户不存在，跳过处理");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//如果为黑名单用户，直接返回已获取奖励，不继续逻辑处理
			if(appUserVo.getBlackList() == 1) {
				logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，黑名单用户，跳过处理");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			
			//逻辑处理
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("phoneNum", appUserVo.getPhoneNum());
			paramsMap.put("awardType", GlobalUtils.AWARD_TYPE_READ);
			AwardHisVo awardHisVo = this.awardHisService.selectLastestAwardInfo(paramsMap);
			if(awardHisVo != null) {
				String createDate = awardHisVo.getCreateDate().substring(0, 19);
				long[] dates = DateUtil.getDistanceTimes(DateUtil.getCurrentLongDateTime(), createDate);
				logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，day=" + dates[0]  + "，hour=" + dates[1] 
						+ "，min=" + dates[2] + "，sec=" + dates[3]);
				if(dates[0] == 0 && dates[1] == 0 && dates[2] == 0 && dates[3] <= 25) {
					logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，太频繁的调用，跳过处理");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
			}
			
			Map<String, Object> mapParam = new HashMap<>();
			mapParam.put("phoneNum", appUserVo.getPhoneNum());
			mapParam.put("primaryKey", primaryKey);
			//配置信息
			AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
			//1. 先判断历史文章是否已存在
//			ReadHisVo readHisVo = this.readHisService.selectReadInfoHisByPhoneNumAndPrimaryKey(mapParam);
//			if(readHisVo == null) {
//				logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，primaryKey=" + primaryKey + "，文章历史不能为空。", 
//						new RuntimeException("文章历史不能为空"));
//				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
//			}
			ReadHisVo readHisVo2 = this.readHisService.selectReadHisByPhoneNumAndPrimaryKey(mapParam);
			boolean flag = false;
			String readHisId = "";
			if(readHisVo2 == null) {
				readHisVo2 = new ReadHisVo();
				readHisId = UUID.getUUID();
				readHisVo2.setId(readHisId);
				readHisVo2.setPhoneNum(appUserVo.getPhoneNum());
				readHisVo2.setPrimaryKey(primaryKey);
				readHisVo2.setIsAward(0); //新建时默认未奖励
				readHisVo2.setAwardCount(0);
				String cTime = DateUtil.getCurrentLongDateTime();
				readHisVo2.setCreateDate(cTime);
				readHisVo2.setUpdateDate(cTime);
				flag = this.readHisService.addReadHis(readHisVo2);
				
			}else {
				boolean result = this.processVersionCode(request, readHisVo2, appUserVo, appConfigVo);
				if(result) {
					return new JsonResult(ResultCode.SUCCESS_ARTICLE_DAILY_LIMIT);
				}
				readHisId = readHisVo2.getId();
				flag = true;
			}
			if(flag) {
				Map<String, Object> map = new HashMap<>();
				map.put("phoneNum", appUserVo.getPhoneNum());
				//先判断是否达到每日阅读奖励上限
				map.put("awardType", GlobalUtils.AWARD_TYPE_READ);
				map.put("currentTime", DateUtil.getCurrentDate());
				Long total = this.awardHisService.selectUserTotalOfGold(map);		//统计个人今日阅读领取金币
				if(total>=appConfigVo.getGoldLimit()) {
					logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，result=单日阅读领取金币已达上限，跳过处理");
					return new JsonResult(ResultCode.SUCCESS_DAILY_LIMIT);
				}
				//3. 每达到一次的次数，则提示验证码
				long awardCount = this.readHisService.getReadHisByAwardCount(map);
				if(awardCount !=0 && awardCount % appConfigVo.getReadAwardLimit() == 0) {
					ImgCodeVo imgCodeVo = this.imgCodeService.getLastestImgCodeByPhoneNum(phoneNum);
					boolean verifyResult = false;
					if(imgCodeVo != null && imgCodeVo.getIsUsed() == 1) {
						String createDate = imgCodeVo.getCreateDate().substring(0, 19);
		            	    //30秒内的验证结果才有效
		                Date endDate = DateUtils.addSeconds(DateUtil.parseLongDateTime(createDate), 30);
		                String currentDate = DateUtil.getCurrentLongDateTime();
		                logger.info("phoneNum=" + phoneNum + "，currentDate=" + currentDate + ", createDate=" + createDate 
		                        + ", ---> endDate=" + DateUtil.formatLongDate(endDate));
		                if(DateUtil.parseLongDateTime(currentDate).before(endDate)) {
		                		logger.info("phoneNum=" + phoneNum + "，30秒内的验证结果有效。");
		                		verifyResult = true;
		                }
					}
					if(!verifyResult) {
						Map<String, Object> mapTmp = new HashMap<>();
						String imgCode = ImgCodeUtil.getImgCode();
						boolean flagOfImgCode = this.imgCodeService.addImgCodeVo(phoneNum, imgCode);
						if (flagOfImgCode) {
							mapTmp.put("imgCode", imgCode);
							return new JsonResult(ResultCode.SUCCESS_IMG_CODE, mapTmp);
						}else {
							logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，图形码生成失败。", 
									new RuntimeException("图形码生成失败"));
							return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
						}
					}
				}
				//4. 随机金币奖励，计算阅读奖励次数校验
				int gold = this.calReadGold(awardCount, appUserVo.getTotalGold(), appConfigVo.getGoldRange());
				if(gold == 0) {
					logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，今天的量已经跑光了，不发奖励。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，阅读随机奖励，gold=" + gold + "，readHisId=" + readHisId);
				return this.awardHisService.addAwardHis(appUserVo.getPhoneNum(), gold, 
						GlobalUtils.AWARD_TYPE_READ, null, null, null, readHisId, null, null, null, null, null, null, null);
				
			}else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
			
		} catch (Exception e) {
			logger.error("phoneNum=" + phoneNum + "，获得阅读奖励异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	}
	
	/**
	 * 特定版本以下用户，每篇文章的奖励次数固定为8，以上用户奖励次数由后台配置；
	 * Android 版本号小于等于16时，奖励次数固定为8；大于16由后台配置；
	 * iOS 版本号小于等于10时，奖励次数固定为8；大于10由后台配置
	 * @param request
	 * @param readHisVo
	 * @param appUserVo
	 * @param appConfigVo
	 * @return
	 */
	private boolean processVersionCode(HttpServletRequest request, ReadHisVo readHisVo, 
			AppUserVo appUserVo, AppConfigVo appConfigVo) {
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
		Integer compareVersionCode = 0;
		//如果是ios请求
		if(isIosReq) {
			compareVersionCode = 10;
		//安卓请求
		}else {
			compareVersionCode = 16;
		}
		Integer curVersionCode = Integer.valueOf(
				StrUtil.null2Str(appUserVo.getVersionCode()).equals("")?"0":appUserVo.getVersionCode());
		if(curVersionCode<=compareVersionCode) {
			if(readHisVo.getAwardCount() >= 8) {
				String result = "这篇文章的奖励已经发完，换个文章看看吧，跳过处理";
				logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，result=" + result);
				return true;
			}
		}else {
			if(readHisVo.getAwardCount() >= appConfigVo.getReadAwardRepeat()) {
				String result = "这篇文章的奖励已经发完，换个文章看看吧，跳过处理";
				logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，result=" + result);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 任务奖励
	 * @param phoneNum
	 * @param taskType
	 * @param taskId
	 * @return
	 */
	public JsonResult doTaskAward(String phoneNum, int taskType, String taskId,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		
		if(!StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") 
				&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("")
				&& sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&taskType=" + taskType + "&taskId=" + taskId
					+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "doTaskAward");
			
		}else {
			logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
		if(appUserVo == null) {
			logger.warn("phoneNum=" + phoneNum + "，用户不存在，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		//如果为黑名单用户，直接返回已获取奖励，不继续逻辑处理
		if(appUserVo.getBlackList() == 1) {
			logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，黑名单用户，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_DUPLICATE);
		}
		
		//逻辑处理
		UserTaskVo userTaskVo = new UserTaskVo();
		userTaskVo.setPhoneNum(appUserVo.getPhoneNum());
		userTaskVo.setTaskId(taskId);
		//新手任务
		if(taskType == 1) {
			userTaskVo = this.userTaskService.selectNewUserTask(userTaskVo);
		//日常任务
		}else if(taskType == 2) {
			userTaskVo.setCurrentTime(DateUtil.getCurrentDate());
			userTaskVo = this.userTaskService.selectDailyUserTask(userTaskVo);
		}else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
		
		if (userTaskVo == null) {
			logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，taskId=" + taskId + "任务为空，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_NO_DATA);
		}
		if(userTaskVo.getStatus() == 2) {
			logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，taskId=" + taskId + "任务已完成，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_DUPLICATE);
		}
		if(userTaskVo.getIsDeleted() == 1) {
			logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，任务标识为已过期数据，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_DUPLICATE);
		}
		//可领取状态
		if(userTaskVo.getStatus() == 1) {
			//金币奖励
			String taskGold = userTaskVo.getTaskGold();
			int gold = Integer.valueOf(taskGold.substring(1, taskGold.length()));
			logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，获得任务完成奖励，taskId=" 
					+ userTaskVo.getTaskId()+ "，gold=" + gold);
			
			userTaskVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
			//时限任务处理
			if(taskId.equals(GlobalUtils.TASK_TYPE_VIDEO) || taskId.equals(GlobalUtils.TASK_TYPE_MONEY)) {
				boolean flag = this.userTaskService.isTimeTaskInterval(userTaskVo);
				if(flag) {
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				int status = this.userTaskService.processTimeTaskStatus(userTaskVo, null);
				if(status != 2) {
					userTaskVo.setStatus(0);	//重置任务状态，可重新发起任务
				}
			}else {
				//更新任务状态信息
				userTaskVo.setStatus(2);		//已完成
			}
			//如果是日常任务，且已完成，则更新过期删除标识
			if(userTaskVo.getTaskType() == 2 && userTaskVo.getStatus() == 2) {
				userTaskVo.setIsDeleted(1);
			}
			//记录奖励
			JsonResult result = this.awardHisService.addAwardHis(appUserVo.getPhoneNum(), gold, 
					GlobalUtils.AWARD_TYPE_TASK, null, null, null, null, null, null, null, null, null, 
						userTaskVo.getTaskId(), userTaskVo.getTaskTitle());
			if(result.getCode().equals("200")) {
				boolean flag = this.userTaskService.modifyUserTask(userTaskVo);
				if(!flag) {
					logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，任务状态更新失败，taskId=" 
						+ userTaskVo.getTaskId()+ "，gold=" + gold, new RuntimeException("更新错误"));
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				return result;
			}else {
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			
		}else {
			logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，taskId=" + taskId + "任务状态为不可领取，跳过处理");
			return new JsonResult(ResultCode.SUCCESS_NO_DATA);
		}
	}
	
	/**
	 * 视频奖励
	 * @param phoneNum
	 * @param primaryKey
	 * @return
	 */
	public JsonResult doVideoAward(String phoneNum, String primaryKey,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		try {
			if(!StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") 
					&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("")
					&& sId.length() == 20 && uId.length() == 32) {
				TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
				if(traceHisVo != null) {
					logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				String paramsStr = "?phoneNum=" + phoneNum + "&primaryKey=" + primaryKey
						+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
				boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
				if(!flag) {
					logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				//记录历史
				this.traceHisService.addTraceHis(request, "doVideoAward");
				
			}else {
				logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			
			AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
			if(appUserVo == null) {
				logger.warn("phoneNum=" + phoneNum + "，用户不存在，跳过处理");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//如果为黑名单用户，直接返回已获取奖励，不继续逻辑处理
			if(appUserVo.getBlackList() == 1) {
				logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，黑名单用户，跳过处理");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			
			//逻辑处理
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("phoneNum", appUserVo.getPhoneNum());
			paramsMap.put("awardType", GlobalUtils.AWARD_TYPE_VIDEO);
			AwardHisVo awardHisVo = this.awardHisService.selectLastestAwardInfo(paramsMap);
			if(awardHisVo != null) {
				String createDate = awardHisVo.getCreateDate().substring(0, 19);
				long[] dates = DateUtil.getDistanceTimes(DateUtil.getCurrentLongDateTime(), createDate);
				logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，day=" + dates[0]  + "，hour=" + dates[1] 
						+ "，min=" + dates[2] + "，sec=" + dates[3]);
				if(dates[0] == 0 && dates[1] == 0 && dates[2] == 0 && dates[3] <= 25) {
					logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，太频繁的调用，跳过处理");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
			}
			
			Map<String, Object> mapParam = new HashMap<>();
			mapParam.put("phoneNum", appUserVo.getPhoneNum());
			mapParam.put("primaryKey", primaryKey);
			//配置信息
			AppConfigVo appConfigVo = this.appConfigService.selectAppConfig();
			//1. 先判断历史视频是否已存在
//			VideoHisVo videoHisVo = this.videoHisService.selectVideoInfoHisByPhoneNumAndPrimaryKey(mapParam);
//			if(videoHisVo == null) {
//				logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，primaryKey=" + primaryKey + "，视频历史不能为空。", 
//						new RuntimeException("视频历史不能为空"));
//				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
//			}
			VideoHisVo videoHisVo2 = this.videoHisService.selectVideoHisByPhoneNumAndPrimaryKey(mapParam);
			boolean flag = false;
			String videoHisId = "";
			if(videoHisVo2 == null) {
				videoHisVo2 = new VideoHisVo();
				videoHisId = UUID.getUUID();
				videoHisVo2.setId(videoHisId);
				videoHisVo2.setPhoneNum(appUserVo.getPhoneNum());
				videoHisVo2.setPrimaryKey(primaryKey);
				videoHisVo2.setIsAward(0); //新建时默认未奖励
				videoHisVo2.setAwardCount(0);
				String cTime = DateUtil.getCurrentLongDateTime();
				videoHisVo2.setCreateDate(cTime);
				videoHisVo2.setUpdateDate(cTime);
				flag = this.videoHisService.addVideoHis(videoHisVo2);
				
			}else {
				if(videoHisVo2.getAwardCount() >= appConfigVo.getVideoAwardRepeat()) {
					String result = "这个视频的奖励已经发完，换个视频看看吧，跳过处理";
					logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，result=" + result);
					return new JsonResult(ResultCode.SUCCESS_VIDEO_DAILY_LIMIT);
				}
				videoHisId = videoHisVo2.getId();
				flag = true;
			}
			if(flag) {
				Map<String, Object> map = new HashMap<>();
				map.put("phoneNum", appUserVo.getPhoneNum());
				//先判断是否达到每日视频奖励上限
				map.put("awardType", GlobalUtils.AWARD_TYPE_VIDEO);
				map.put("currentTime", DateUtil.getCurrentDate());
				Long total = this.awardHisService.selectUserTotalOfGold(map);		//统计个人今日视频领取金币
				if(total>=appConfigVo.getVideoGoldLimit()) {
					logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，result=今日视频奖励已达上限，跳过处理");
					return new JsonResult(ResultCode.SUCCESS_ALL_GONE);
				}
				//3. 每达到一次的次数，则提示验证码
				long awardCount = this.videoHisService.getVideoHisByAwardCount(map);
				if(awardCount !=0 && awardCount % appConfigVo.getReadAwardLimit() == 0) {
					ImgCodeVo imgCodeVo = this.imgCodeService.getLastestImgCodeByPhoneNum(phoneNum);
					boolean verifyResult = false;
					if(imgCodeVo != null && imgCodeVo.getIsUsed() == 1) {
						String createDate = imgCodeVo.getCreateDate().substring(0, 19);
		            	    //30秒内的验证结果才有效
		                Date endDate = DateUtils.addSeconds(DateUtil.parseLongDateTime(createDate), 30);
		                String currentDate = DateUtil.getCurrentLongDateTime();
		                logger.info("phoneNum=" + phoneNum + "，currentDate=" + currentDate + ", createDate=" + createDate 
		                        + ", ---> endDate=" + DateUtil.formatLongDate(endDate));
		                if(DateUtil.parseLongDateTime(currentDate).before(endDate)) {
		                		logger.info("phoneNum=" + phoneNum + "，30秒内的验证结果有效。");
		                		verifyResult = true;
		                }
					}
					if(!verifyResult) {
						Map<String, Object> mapTmp = new HashMap<>();
						String imgCode = ImgCodeUtil.getImgCode();
						boolean flagOfImgCode = this.imgCodeService.addImgCodeVo(phoneNum, imgCode);
						if (flagOfImgCode) {
							mapTmp.put("imgCode", imgCode);
							return new JsonResult(ResultCode.SUCCESS_IMG_CODE, mapTmp);
						}else {
							logger.error("phoneNum=" + appUserVo.getPhoneNum() + "，图形码生成失败。", 
									new RuntimeException("图形码生成失败"));
							return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
						}
					}
				}
				//4. 随机金币奖励
				int gold = GlobalUtils.getProbabilityGold(appConfigVo.getVideoGoldRange());
				//计算视频奖励次数校验
				gold = this.calVideoGold(gold, awardCount);
				if(gold == 0) {
					logger.warn("phoneNum=" + appUserVo.getPhoneNum() + "，今天的量已经跑光了，不发奖励。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				logger.info("phoneNum=" + appUserVo.getPhoneNum() + "，视频随机奖励，gold=" + gold + "，videoHisId=" + videoHisId);
				return this.awardHisService.addAwardHis(appUserVo.getPhoneNum(), gold, 
						GlobalUtils.AWARD_TYPE_VIDEO, null, null, null, videoHisId, null, null, null, null, null, null, null);
				
			}else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
			
		} catch (Exception e) {
			logger.error("phoneNum=" + phoneNum + "，获得视频奖励异常：" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	}
	
	/**
	 * 阅读奖励，计算金币
	 * @param awardCount 奖励次数
	 * @param totalGold 总金币
	 * @param goldRange 阅读金币概率后台配置
	 * @return
	 */
	private int calReadGold(long awardCount, long totalGold, String goldRange) {
		if(awardCount>=720) {
			return 0;	//无奖励
		}
		if(awardCount<=10 && totalGold<=10000) {
			return GlobalUtils.getProbabilityGold("150&30#300&50#600&20");
			
		}else if(awardCount<=10) {
			return GlobalUtils.getProbabilityGold("600&5#300&10#150&85");
			
		}else {
			//格式化
			DecimalFormat dFormat = new DecimalFormat("#0");
			//后台配置
			double tmp = GlobalUtils.getProbabilityGold(goldRange);
			if(awardCount>=180) {
				tmp = tmp * 0.5;
				
			}else if(awardCount>=360) {
				tmp = tmp * 0.1;
			}
			return Integer.valueOf(dFormat.format(tmp));
		}
	}
	
	/**
	 * 视频奖励，计算金币
	 * @param gold
	 * @param awardCount
	 * @return
	 */
	private int calVideoGold(int gold, long awardCount) {
		//格式化
		DecimalFormat dFormat = new DecimalFormat("#0");
		double tmp = gold;
		if(awardCount>=720) {
			tmp = 0;		//无奖励
			
		}else if(awardCount>=360) {
			tmp = gold * 0.5;
		}
		return Integer.valueOf(dFormat.format(tmp));
	}
}
