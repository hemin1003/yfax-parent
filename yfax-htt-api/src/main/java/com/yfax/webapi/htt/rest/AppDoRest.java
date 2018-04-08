package com.yfax.webapi.htt.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yfax.common.sms.SmsService;
import com.yfax.utils.DateUtil;
import com.yfax.utils.FilterStr;
import com.yfax.utils.JsonResult;
import com.yfax.utils.NetworkUtil;
import com.yfax.utils.ResultCode;
import com.yfax.utils.StrUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.service.AppUserMultiService;
import com.yfax.webapi.htt.service.AppUserService;
import com.yfax.webapi.htt.service.BlacklistSetService;
import com.yfax.webapi.htt.service.LoginHisNewService;
import com.yfax.webapi.htt.service.TraceHisService;
import com.yfax.webapi.htt.service.UserAwardService;
import com.yfax.webapi.htt.service.UserFeedbackService;
import com.yfax.webapi.htt.service.UserSmsService;
import com.yfax.webapi.htt.vo.AppUserMultiVo;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.LoginHisNewVo;
import com.yfax.webapi.htt.vo.TraceHisVo;
import com.yfax.webapi.htt.vo.UserFeedbackVo;
import com.yfax.webapi.htt.vo.UserSmsVo;

/**
 * @author Minbo.He 受理接口
 */
@RestController
@RequestMapping("/api/htt")
public class AppDoRest {

	protected static Logger logger = LoggerFactory.getLogger(AppDoRest.class);

	@Autowired
	private AppUserService appUserService;
	@Autowired
	private UserSmsService userSmsService;
	@Autowired
	private LoginHisNewService loginHisNewService;
	@Autowired
	private UserFeedbackService userFeedbackService;
	@Autowired
	private AppUserMultiService appUserMultiService;
	@Autowired
	private UserAwardService userAwardService;
	@Autowired
	private TraceHisService traceHisService;
	@Autowired
	private BlacklistSetService blacklistSetService;

	/**
	 * 用户退出登录接口
	 */
	@RequestMapping(value = "/doLoginOut", method = { RequestMethod.POST })
	public JsonResult doLoginOut(String phoneNum) {
		if (StrUtil.null2Str(phoneNum).equals("")) {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
		boolean flag = this.appUserService.doLoginOut(phoneNum);
		if (flag) {
			return new JsonResult(ResultCode.SUCCESS);
		}
		return new JsonResult(ResultCode.SUCCESS_FAIL);
	}

	/**
	 * 用户注册接口（限定手机号码）
	 */
	@RequestMapping(value = "/doRegister", method = { RequestMethod.POST })
	public JsonResult doRegister(String phoneNum, String userPwd, String inviteCode, String msgCode, String mId,
			String tId, String sId, String uId, String channel, HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(userPwd).equals("")) {
			if (!StrUtil.null2Str(msgCode).equals("") && !StrUtil.null2Str(mId).equals("")
					&& !StrUtil.null2Str(tId).equals("") && !StrUtil.null2Str(sId).equals("")
					&& !StrUtil.null2Str(uId).equals("") && !StrUtil.null2Str(channel).equals("")
					&& msgCode.length() == 6 && sId.length() == 20 && uId.length() == 32) {
				TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
				if (traceHisVo != null) {
					logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				String paramsStr = "?phoneNum=" + phoneNum + "&userPwd=" + userPwd + "&msgCode=" + msgCode + "&mId="
						+ mId + "&tId=" + tId + "&sId=" + sId + "&channel=" + channel;
				boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
				if (!flag) {
					logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				// 记录历史
				this.traceHisService.addTraceHis(request, "doRegister");

				// 放行特定IMEI的值
				boolean isGo = false;
				if(StrUtil.null2Str(mId).equals(GlobalUtils.APP_FREE_IMEI)) {
					isGo = true;
				}
				if(!isGo) {
					// 判断Ime绑定次数
					long imeiCount = this.appUserService.selectAppUserOfMeiCount(mId);
					if (imeiCount >= 1) {
						logger.info("phoneNum=" + phoneNum + "，mId=" + mId + "，该设备已注册，请用原账号登录。");
						return new JsonResult(ResultCode.SUCCESS_LOGIN_LIMIT);
					}
				}
				// 判断短信验证码是否存在
				UserSmsVo smsVo = this.userSmsService.getUserSms(phoneNum, msgCode);
				if (smsVo == null) {
					logger.info("phoneNum=" + phoneNum + ", 短信验证码msgCode=" + msgCode + " 记录为空，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
			} else {
				logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}

			// 判断IP注册次数（单IP，24小时内最多注册5个账号）
			String registerIp = NetworkUtil.getIpAddress(request);
			Map<String, Object> params = new HashMap<>();
			params.put("registerIp", registerIp);
			params.put("date1", DateUtil.getCurrentDate());
			params.put("date2", DateUtil.getCurrentDate(1));
			long ipCount = this.appUserService.getAppUserCountOfRegisterIp(params);
			if (ipCount >= 5) {
				logger.info("phoneNum=" + phoneNum + "，registerIp=" + registerIp + "，该ip已注册多个，不能再注册。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}

			// 判断是否已注册
			AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
			if (appUserVo != null) {
				return new JsonResult(ResultCode.SUCCESS_EXIST);
			} else {
				AppUserMultiVo appUserMultiVo = this.appUserMultiService.selectByPhoneNum(phoneNum);
				if (appUserMultiVo != null) {
					return new JsonResult(ResultCode.SUCCESS_IS_USED);
				}
				// 注册逻辑
				return this.appUserService.registerUser(phoneNum, userPwd, inviteCode, 0, 1, 0, request, mId, msgCode,
						channel);
			}
		} else {
			logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	}

	/**
	 * 重置用户登录密码
	 */
	@RequestMapping(value = "/doResetPwd", method = { RequestMethod.POST })
	public JsonResult doResetPwd(String phoneNum, String userPwd) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(userPwd).equals("")) {
			AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
			appUserVo.setPhoneNum(phoneNum);
			appUserVo.setUserPwd(userPwd);
			String cTime = DateUtil.getCurrentLongDateTime();
			appUserVo.setUpdateDate(cTime);
			boolean flag = this.appUserService.modifyUser(appUserVo);
			if (flag) {
				return new JsonResult(ResultCode.SUCCESS);
			} else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}

	/**
	 * 用户个人信息接口（限定手机号码）
	 */
	@RequestMapping(value = "/doUserInfo", method = { RequestMethod.POST })
	public JsonResult doUserInfo(String phoneNum, String userPwd, String userName, String address, String wechat,
			String qq, String email, String tabArticle, String tabVideo) {
		AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
		appUserVo.setUserPwd(StrUtil.null2Str(userPwd));
		appUserVo.setUserName(StrUtil.null2Str(userName));
		appUserVo.setAddress(StrUtil.null2Str(address));
		appUserVo.setWechat(StrUtil.null2Str(wechat));
		appUserVo.setQq(StrUtil.null2Str(qq));
		appUserVo.setEmail(StrUtil.null2Str(email));
		appUserVo.setTabArticle(StrUtil.null2Str(tabArticle));
		appUserVo.setTabVideo(StrUtil.null2Str(tabVideo));
		String cTime = DateUtil.getCurrentLongDateTime();
		appUserVo.setUpdateDate(cTime);
		boolean flag = this.appUserService.modifyUser(appUserVo);
		if (flag) {
			return new JsonResult(ResultCode.SUCCESS);
		} else {
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
	}

	/**
	 * 短信验证码接口
	 */
	@RequestMapping(value = "/doSms", method = { RequestMethod.POST })
	public JsonResult doSms(String phoneNum, String msgCode) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(msgCode).equals("")) {
			// 1. 短信码相同的间隔时长
			UserSmsVo userSmsVo2 = this.userSmsService.getUserSmsByMsgCode(msgCode);
			if (userSmsVo2 != null) {
				String createDate = userSmsVo2.getCreateDate().substring(0, 19);
				// 三分钟内不能生成重复验证码
				Date endDate = DateUtils.addSeconds(DateUtil.parseLongDateTime(createDate), 180);
				String currentDate = DateUtil.getCurrentLongDateTime();
				logger.info("phoneNum=" + phoneNum + "，currentDate=" + currentDate + ", createDate=" + createDate
						+ ", ---> endDate=" + DateUtil.formatLongDate(endDate) + ", msgCode=" + msgCode);
				if (DateUtil.parseLongDateTime(currentDate).before(endDate)) {
					logger.info("phoneNum=" + phoneNum + "，生成的验证码msgCode=" + msgCode + "间隔时间太短，不能发短信，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
			}
			// 2. 手机号码发送是否达上限
			long count = this.userSmsService.getUserSmsByPhoneNum(phoneNum);
			if (count >= 3) {
				logger.info("phoneNum=" + phoneNum + ", 短信验证码已发送三次，不能再发送短信，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			// 发送短信
			HashMap<String, Object> result = SmsService.sendSms(phoneNum, msgCode, GlobalUtils.PROJECT_HTT,
					GlobalUtils.SMS_TEMPLATE_ID, GlobalUtils.SMS_APP_ID);
			if ("000000".equals(result.get("statusCode"))) {
				UserSmsVo userSms = new UserSmsVo();
				userSms.setId(UUID.getUUID());
				userSms.setPhoneNum(phoneNum);
				userSms.setMsgCode(msgCode);
				userSms.setProjectCode(GlobalUtils.PROJECT_HTT);
				String cTime = DateUtil.getCurrentLongDateTime();
				userSms.setCreateDate(cTime);
				userSms.setUpdateDate(cTime);
				boolean flag = this.userSmsService.addUserSms(userSms);
				if (!flag) {
					logger.warn("短信记录失败，请查核");
				}
				return new JsonResult(ResultCode.SUCCESS, result);
			} else {
				return new JsonResult(ResultCode.SUCCESS_FAIL, result);
			}
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}

	/**
	 * 短信验证码接口-新版
	 */
	@PostMapping(value = "/doSmsNew")
	public JsonResult doSmsNew(String phoneNum, String msgCode, String mId, String tId, String sId, String uId,
			HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(msgCode).equals("")
				&& !StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("")
				&& !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("") && msgCode.length() == 6
				&& sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if (traceHisVo != null) {
				logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&msgCode=" + msgCode + "&mId=" + mId + "&tId=" + tId + "&sId="
					+ sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if (!flag) {
				logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			// 记录调用历史
			this.traceHisService.addTraceHis(request, "doSmsNew");
			// 1. 短信码相同的间隔时长
			UserSmsVo userSmsVo2 = this.userSmsService.getUserSmsByMsgCode(msgCode);
			if (userSmsVo2 != null) {
				String createDate = userSmsVo2.getCreateDate().substring(0, 19);
				// 三分钟内不能生成重复验证码
				Date endDate = DateUtils.addSeconds(DateUtil.parseLongDateTime(createDate), 180);
				String currentDate = DateUtil.getCurrentLongDateTime();
				logger.info("phoneNum=" + phoneNum + "，currentDate=" + currentDate + ", createDate=" + createDate
						+ ", ---> endDate=" + DateUtil.formatLongDate(endDate) + ", msgCode=" + msgCode);
				if (DateUtil.parseLongDateTime(currentDate).before(endDate)) {
					logger.info("phoneNum=" + phoneNum + "，生成的验证码msgCode=" + msgCode + "间隔时间太短，不能发短信，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
			}
			// 2. 手机号码发送是否达上限
			long count = this.userSmsService.getUserSmsByPhoneNum(phoneNum);
			if (count >= 3) {
				logger.info("phoneNum=" + phoneNum + ", 短信验证码已发送三次，不能再发送短信，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			// 发送短信
			HashMap<String, Object> result = SmsService.sendSms(phoneNum, msgCode, GlobalUtils.PROJECT_HTT,
					GlobalUtils.SMS_TEMPLATE_ID, GlobalUtils.SMS_APP_ID);
			logger.info("phoneNum=" + phoneNum + "，短信发送结果result= " + result.toString());
			if ("000000".equals(result.get("statusCode"))) {
				UserSmsVo userSms = new UserSmsVo();
				userSms.setId(UUID.getUUID());
				userSms.setPhoneId(mId);
				userSms.setPhoneNum(phoneNum);
				userSms.setMsgCode(msgCode);
				userSms.setProjectCode(GlobalUtils.PROJECT_HTT);
				String cTime = DateUtil.getCurrentLongDateTime();
				userSms.setCreateDate(cTime);
				userSms.setUpdateDate(cTime);
				boolean flag1 = this.userSmsService.addUserSms(userSms);
				if (!flag1) {
					logger.warn("phoneNum=" + phoneNum + "，短信记录失败，请查核");
				}
				return new JsonResult(ResultCode.SUCCESS);
			} else {
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
		} else {
			logger.warn("phoneNum=" + phoneNum + "，参数错误。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	}

	/**
	 * 记录用户登录设备数据接口
	 */
	@RequestMapping(value = "/doLoginHis", method = { RequestMethod.POST })
	public JsonResult doLoginHis(String phoneNum, String deviceName, String imei, String ip, String mac,
			String location, String longitude, String latitude, String position, String wifi, String installedList,
			String wifiMac, String isRoot, String channel, HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("")) {
			LoginHisNewVo loginHisNewVo = new LoginHisNewVo();
			loginHisNewVo.setId(UUID.getUUID());
			loginHisNewVo.setPhoneNum(phoneNum);
			loginHisNewVo.setDeviceName(deviceName);
			loginHisNewVo.setImei(imei);
			loginHisNewVo.setIp(ip);
			loginHisNewVo.setMac(mac);
			loginHisNewVo.setLongitude(longitude);
			loginHisNewVo.setLatitude(latitude);
			loginHisNewVo.setPosition(position);
			loginHisNewVo.setWifi(FilterStr.filterAll(wifi));
			if (installedList != null) {
				if (installedList.length() > 50000) {
					loginHisNewVo.setInstalledList(FilterStr.filterEmoji(installedList.substring(0, 50000)));
				} else {
					loginHisNewVo.setInstalledList(FilterStr.filterEmoji(installedList));
				}
			}
			loginHisNewVo.setWifiMac(wifiMac);
			loginHisNewVo.setIsRoot(isRoot);
			loginHisNewVo.setsIp(NetworkUtil.getIpAddress(request));
			String cTime = DateUtil.getCurrentLongDateTime();
			loginHisNewVo.setCreateDate(cTime);
			loginHisNewVo.setUpdateDate(cTime);
			loginHisNewVo.setChannel(channel);
			boolean flag = this.loginHisNewService.addLoginHisNew(loginHisNewVo);
			if (flag) {
				this.blacklistSetService.processBlackList(loginHisNewVo);
				return new JsonResult(ResultCode.SUCCESS);
			} else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}

	/*	 
	 * 用户反馈信息接口
	 */
	@RequestMapping(value = "/doUserFeedback", method = { RequestMethod.POST })
	public JsonResult doUserFeedback(String phoneNum, String info) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(info).equals("")) {
			UserFeedbackVo userFeedbackVo = new UserFeedbackVo();
			userFeedbackVo.setId(UUID.getUUID());
			userFeedbackVo.setPhoneNum(phoneNum);
			userFeedbackVo.setInfo(FilterStr.filterAll(info));
			String cTime = DateUtil.getCurrentLongDateTime();
			userFeedbackVo.setCreateDate(cTime);
			userFeedbackVo.setUpdateDate(cTime);
			boolean result = this.userFeedbackService.addUserFeedback(userFeedbackVo);
			if (result) {
				return new JsonResult(ResultCode.SUCCESS);
			} else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}

	/**
	 * rest推送测试用接口 参数说明： phoneNum，手机号码 type，消息类型（1=通知栏消息，2=透传消息）
	 */
	@RequestMapping("/testPushNotify")
	public String testPushNotify(String phoneNum, int type) {
		return this.appUserService.testPushNotify(phoneNum, type);
	}

	/**
	 * 微信登录接口
	 */
	@RequestMapping(value = "/doWechatLogin", method = { RequestMethod.POST })
	public JsonResult doWechatLogin(String openId, String nickName, String headImgurl, String unionId, String mId,
			String tId, String sId, String uId, String channel, HttpServletRequest request) {
		if (!StrUtil.null2Str(openId).equals("")) {
			return this.appUserMultiService.wechatLogin(openId, nickName, headImgurl, unionId, mId, tId, sId, uId,
					channel, request);
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}

	/**
	 * 绑定微信
	 */
	@RequestMapping(value = "/doBindToWechat", method = { RequestMethod.POST })
	public JsonResult doBindToWechat(String phoneNum, String openId, String nickName, String headImgurl, String unionId,
			String wechatName, String wechatPhoneNum, String mId, String tId, String sId, String uId,
			HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(openId).equals("")
				&& !StrUtil.null2Str(wechatName).equals("") && !StrUtil.null2Str(wechatPhoneNum).equals("")) {
			return this.appUserMultiService.bindToWechat(phoneNum, openId, nickName, headImgurl, unionId, wechatName,
					wechatPhoneNum, mId, tId, sId, uId, request);
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}

	}

	/**
	 * 绑定支付宝
	 */
	@RequestMapping(value = "/doBindToAlipay", method = { RequestMethod.POST })
	public JsonResult doBindToAlipay(String phoneNum, String alipayAccount, String alipayName, String mId, String tId,
			String sId, String uId, HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(alipayAccount).equals("")
				&& !StrUtil.null2Str(alipayName).equals("")) {
			return this.appUserMultiService.bindToAlipay(phoneNum, alipayAccount, alipayName, mId, tId, sId, uId,
					request);
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}

	}

	/**
	 * 绑定到登录手机
	 */
	@RequestMapping(value = "/doBindToPhoneNum", method = { RequestMethod.POST })
	public JsonResult doBindToPhoneNum(String phoneNum, String toPhoneNum, String userPwd, String mId, String tId,
			String sId, String uId, HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(toPhoneNum).equals("")
				&& !StrUtil.null2Str(userPwd).equals("")) {
			return this.appUserMultiService.bindToPhoneNum(phoneNum, toPhoneNum, userPwd, mId, tId, sId, uId, request);
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}

	/**
	 * 绑定话费手机
	 */
	@RequestMapping(value = "/doBindToTelPhoneNum", method = { RequestMethod.POST })
	public JsonResult doBindToTelPhoneNum(String phoneNum, String toPhoneNum, String mId, String tId, String sId,
			String uId, HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(toPhoneNum).equals("")) {
			return this.appUserMultiService.bindToTelPhoneNum(phoneNum, toPhoneNum, mId, tId, sId, uId, request);
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}

	/**
	 * 图形码验证
	 */
	@RequestMapping(value = "/doVerifyImgCode", method = { RequestMethod.POST })
	public JsonResult doVerifyImgCode(String phoneNum, String imgCode, String mId, String tId, String sId, String uId,
			HttpServletRequest request) {
		if (!StrUtil.null2Str(phoneNum).equals("") && !StrUtil.null2Str(imgCode).equals("")) {
			return this.userAwardService.doVerifyImgCode(phoneNum, imgCode, mId, tId, sId, uId, request);
		} else {
			return new JsonResult(ResultCode.PARAMS_ERROR);
		}
	}
	

}