package com.yfax.webapi.htt.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.utils.CreateID;
import com.yfax.utils.DateUtil;
import com.yfax.utils.FilterStr;
import com.yfax.utils.JsonResult;
import com.yfax.utils.MD5Util;
import com.yfax.utils.NetworkUtil;
import com.yfax.utils.ResultCode;
import com.yfax.utils.StrUtil;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.AppUserMultiDao;
import com.yfax.webapi.htt.vo.AppUserMultiVo;
import com.yfax.webapi.htt.vo.AppUserVo;
import com.yfax.webapi.htt.vo.TraceHisVo;

/**
 * 统一用户管理服务
 * @author Minbo
 */
@Service
public class AppUserMultiService {
	
	protected static Logger logger = LoggerFactory.getLogger(AppUserMultiService.class);
	
	@Autowired
	private AppUserMultiDao appUserMultiDao;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private TraceHisService traceHisService;
	
	public boolean addMultiUser(AppUserMultiVo appUserMultiVo) {
		return this.appUserMultiDao.insertMultiUser(appUserMultiVo);
	}
	
	public boolean modifyMultiUser(AppUserMultiVo appUserMultiVo) {
		return this.appUserMultiDao.updateMultiUser(appUserMultiVo);
	}
	
	public AppUserMultiVo getAppUserByWechatOpenId(String wechatOpenId) {
		return this.appUserMultiDao.selectByWechatOpenId(wechatOpenId);
	}
	
	public AppUserMultiVo selectByPhoneNum(String phoneNum) {
		return this.appUserMultiDao.selectByPhoneNum(phoneNum);
	}
	
	public AppUserMultiVo getAppUserBySystemUserId(String systemUserId) {
		return this.appUserMultiDao.selectBySystemUserId(systemUserId);
	}
	
	/**
	 * 微信登录处理
	 * @param openId
	 * @param nickName
	 * @param headImgurl
	 * @param unionId
	 * @return
	 */
	@Transactional
	public JsonResult wechatLogin(String openId, String nickName, String headImgurl, String unionId, 
			String mId, String tId, String sId, String uId, String channel, HttpServletRequest request) {
		
		if(!StrUtil.null2Str(openId).equals("") && !StrUtil.null2Str(nickName).equals("")  && !StrUtil.null2Str(unionId).equals("") 
				&& !StrUtil.null2Str(mId).equals("") && !StrUtil.null2Str(tId).equals("") && !StrUtil.null2Str(sId).equals("") && !StrUtil.null2Str(uId).equals("") 
				&& !StrUtil.null2Str(channel).equals("")
				&& sId.length() == 20 && uId.length() == 32) {
//			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
//			if(traceHisVo != null) {
//				logger.info("openId=" + openId + "，过期无效请求，跳过处理。");
//				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
//			}
			String paramsStr = "?openId=" + openId + "&headImgurl=" + headImgurl + "&unionId=" + unionId 
					+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId + "&channel=" + channel;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.info("openId=" + openId + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
//			//记录历史
//			this.traceHisService.addTraceHis(request, "doWechatLogin");
			
		}else {
			logger.info("openId=" + openId + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	
		//逻辑处理
		Map<Object, Object> map = new HashMap<>();
		AppUserMultiVo appUserMultiVo = this.getAppUserByWechatOpenId(openId);
		String cTime = DateUtil.getCurrentLongDateTime();
		boolean flag = false;
		if(appUserMultiVo == null) {
			
			if(!StrUtil.null2Str(mId).equals("")) {
				// 放行特定IMEI的值
				boolean isGo = false;
				if(StrUtil.null2Str(mId).equals(GlobalUtils.APP_FREE_IMEI)) {
					isGo = true;
				}
				if(!isGo) {
					//判断Ime绑定次数
					long imeiCount = this.appUserService.selectAppUserOfMeiCount(mId);
					if(imeiCount >= 1) {
						logger.info("openId=" + openId + "，mId=" + mId +"，该设备已注册，请用原账号登录。");
						return new JsonResult(ResultCode.SUCCESS_LOGIN_LIMIT);
					}
				}
			}
			
			//判断IP注册次数（单IP，24小时内最多注册5个账号）
			String registerIp = NetworkUtil.getIpAddress(request);
			Map<String, Object> params = new HashMap<>();
			params.put("registerIp", registerIp);
			params.put("date1", DateUtil.getCurrentDate());
			params.put("date2", DateUtil.getCurrentDate(1));
			long ipCount = this.appUserService.getAppUserCountOfRegisterIp(params);
			if(ipCount >= 5) {
				logger.info("openId=" + openId + "，registerIp=" + registerIp +"，该ip已注册多个，不能再注册。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			
			appUserMultiVo = new AppUserMultiVo();
			
			appUserMultiVo.setSystemUserId(CreateID.generateIds());
			
			appUserMultiVo.setWechatOpenId(openId);
			appUserMultiVo.setWechatNickname(FilterStr.filterAll(nickName));
			appUserMultiVo.setWechatHeadImgurl(headImgurl);
			appUserMultiVo.setWechatUnionId(unionId);
			appUserMultiVo.setWechatPwd(MD5Util.encodeByMD5(appUserMultiVo.getWechatOpenId()));
			
			appUserMultiVo.setCreateDate(cTime);
			appUserMultiVo.setUpdateDate(cTime);
			
			JsonResult result = this.appUserService.registerUser(appUserMultiVo.getSystemUserId(), null, 
					null, 0, 0, 0, request, mId, null, channel);
			if(!result.getCode().equals(ResultCode.SUCCESS.val())) {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
			flag = this.appUserMultiDao.insertMultiUser(appUserMultiVo);
			
			//设置昵称
			AppUserVo appUserVo = new AppUserVo();
			appUserVo.setPhoneNum(appUserMultiVo.getSystemUserId());
			appUserVo.setUserName(appUserMultiVo.getWechatNickname());
			appUserVo.setIsBindWechat(1);
			appUserVo.setUpdateDate(cTime);
			this.appUserService.modifyUser(appUserVo);
			
		}else {
			appUserMultiVo.setUpdateDate(cTime);
			flag = this.appUserMultiDao.updateMultiUser(appUserMultiVo);
		}
		if(flag) {
			map.put("systemUserId", appUserMultiVo.getSystemUserId());
			return new JsonResult(ResultCode.SUCCESS, map);
		}
		return new JsonResult(ResultCode.PARAMS_ERROR);
	}
	
	/**
	 * 手机/微信/支付宝-登录后绑定到微信
	 * @param phoneNum
	 * @param openId
	 * @param nickName
	 * @param headImgurl
	 * @param unionId
	 * @return
	 */
	@Transactional
	public JsonResult bindToWechat(String phoneNum, String openId, String nickName, String headImgurl, 
			String unionId, String wechatName, String wechatPhoneNum,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		try {
			if(!StrUtil.null2Str(mId).equals("")  && !StrUtil.null2Str(tId).equals("") && !StrUtil.null2Str(sId).equals("") 
					&& !StrUtil.null2Str(uId).equals("")  && sId.length() == 20 && uId.length() == 32) {
				TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
				if(traceHisVo != null) {
					logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				String paramsStr = "?phoneNum=" + phoneNum + "&openId=" + openId + "&headImgurl=" + headImgurl 
						+ "&unionId=" + unionId + "&wechatName=" + wechatName + "&wechatPhoneNum=" + wechatPhoneNum 
						+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
				boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
				if(!flag) {
					logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
					return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
				}
				//记录历史
				this.traceHisService.addTraceHis(request, "doBindToWechat");
			}else {
				logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
		
			//逻辑处理
			AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
			if(appUserVo == null) {
				return new JsonResult(ResultCode.SUCCESS_NO_USER);
			}
			AppUserMultiVo appUserMultiVo = this.getAppUserByWechatOpenId(openId);
			if(appUserMultiVo != null && !appUserMultiVo.getSystemUserId().equals(phoneNum)) {
				logger.info("phoneNum=" + phoneNum + "，此微信已绑定过，不能重复绑定，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BIND);
			}
			String cTime = DateUtil.getCurrentLongDateTime();
			Map<Object, Object> map = new HashMap<>();
			appUserMultiVo = this.getAppUserBySystemUserId(appUserVo.getPhoneNum());
			boolean flag = false;
			if(appUserMultiVo == null) {
				appUserMultiVo = new AppUserMultiVo();
				
				appUserMultiVo.setSystemUserId(appUserVo.getPhoneNum());
				
				appUserMultiVo.setWechatOpenId(openId);
				appUserMultiVo.setWechatNickname(FilterStr.filterAll(nickName));
				appUserMultiVo.setWechatHeadImgurl(headImgurl);
				appUserMultiVo.setWechatUnionId(unionId);
				appUserMultiVo.setWechatPwd(MD5Util.encodeByMD5(appUserMultiVo.getWechatOpenId()));
				appUserMultiVo.setWechatName(FilterStr.filterAll(wechatName));
				appUserMultiVo.setWechatPhoneNum(wechatPhoneNum);
				
				appUserMultiVo.setCreateDate(cTime);
				appUserMultiVo.setUpdateDate(cTime);
				flag = this.appUserMultiDao.insertMultiUser(appUserMultiVo);
				
			}else {
				appUserMultiVo.setWechatOpenId(openId);
				appUserMultiVo.setWechatNickname(FilterStr.filterAll(nickName));
				appUserMultiVo.setWechatHeadImgurl(headImgurl);
				appUserMultiVo.setWechatUnionId(unionId);
				appUserMultiVo.setWechatPwd(MD5Util.encodeByMD5(appUserMultiVo.getWechatOpenId()));
				appUserMultiVo.setWechatName(FilterStr.filterAll(wechatName));
				appUserMultiVo.setWechatPhoneNum(wechatPhoneNum);
				
				appUserMultiVo.setUpdateDate(cTime);
				
				flag = this.appUserMultiDao.updateMultiUser(appUserMultiVo);
			}
			
			//更新用户标识
			appUserVo.setIsBindWechat(1);
			appUserVo.setUpdateDate(cTime);
			appUserVo.setUserName(appUserMultiVo.getWechatNickname());
			boolean flag1 = this.appUserService.modifyUser(appUserVo);
			
			logger.info("phoneNum=" + phoneNum + "，绑定微信，操作结果：新增统一用户标识flag=" + flag + ", 更新用户标志flag1=" + flag1);
			if(flag && flag1) {
				return new JsonResult(ResultCode.SUCCESS, map);
			}else {
				return new JsonResult(ResultCode.SUCCESS_FAIL);
			}
			
		} catch (Exception e) {
			logger.error("登录后绑定到微信异常：phoneNum=" + phoneNum 
					+ "，message=" + e.getMessage(), e);
			return new JsonResult(ResultCode.SUCCESS_BERIGHT_WECHAT);
		}
	}
	
	/**
	 * 手机/微信/支付宝-登录后绑定到支付宝
	 * @param phoneNum
	 * @param openId
	 * @param nickName
	 * @param headImgurl
	 * @param unionId
	 * @return
	 */
	@Transactional
	public JsonResult bindToAlipay(String phoneNum, String alipayAccount, String alipayName,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		
		if(!StrUtil.null2Str(mId).equals("")  && !StrUtil.null2Str(tId).equals("") && !StrUtil.null2Str(sId).equals("") 
				&& !StrUtil.null2Str(uId).equals("")  && sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&alipayAccount=" + alipayAccount + "&alipayName=" + alipayName 
					+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "doBindToAlipay");
		}else {
			logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
	
		//逻辑处理
		AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
		if(appUserVo == null) {
			return new JsonResult(ResultCode.SUCCESS_NO_USER);
		}
		String cTime = DateUtil.getCurrentLongDateTime();
		Map<Object, Object> map = new HashMap<>();
		AppUserMultiVo appUserMultiVo = this.getAppUserBySystemUserId(appUserVo.getPhoneNum());
		boolean flag = false;
		if(appUserMultiVo == null) {
			appUserMultiVo = new AppUserMultiVo();
			
			appUserMultiVo.setSystemUserId(appUserVo.getPhoneNum());
			
			appUserMultiVo.setAlipayAccount(alipayAccount);
			appUserMultiVo.setAlipayName(alipayName);
			
			appUserMultiVo.setCreateDate(cTime);
			appUserMultiVo.setUpdateDate(cTime);
			flag = this.appUserMultiDao.insertMultiUser(appUserMultiVo);
			
		}else {
			appUserMultiVo.setAlipayAccount(alipayAccount);
			appUserMultiVo.setAlipayName(alipayName);
			
			appUserMultiVo.setUpdateDate(cTime);
			
			flag = this.appUserMultiDao.updateMultiUser(appUserMultiVo);
		}
		
		//更新用户标识
		appUserVo.setIsBindAlipay(1);
		appUserVo.setUpdateDate(cTime);
		boolean flag1 = this.appUserService.modifyUser(appUserVo);
		
		logger.info("phoneNum=" + phoneNum + "，绑定支付宝，操作结果：新增统一用户标识flag=" + flag + ", 更新用户标志flag1=" + flag1);
		if(flag && flag1) {
			return new JsonResult(ResultCode.SUCCESS, map);
		}else {
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
	}
	
	/**
	 * 微信/支付宝-登录后，绑定到登录手机
	 * @param phoneNum
	 * @param toPhoneNum
	 * @return
	 */
	@Transactional
	public JsonResult bindToPhoneNum(String phoneNum, String toPhoneNum, String userPwd,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		
		if(!StrUtil.null2Str(mId).equals("")  && !StrUtil.null2Str(tId).equals("") && !StrUtil.null2Str(sId).equals("") 
				&& !StrUtil.null2Str(uId).equals("")  && sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&toPhoneNum=" + toPhoneNum + "&userPwd=" + userPwd 
					+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "doBindToPhoneNum");
		}else {
			logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		//逻辑处理
		AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(toPhoneNum);
		if(appUserVo != null) {
			logger.info("phoneNum=" + phoneNum + "，用户记录存在，则此手机号码已被使用过，不能重复绑定。");
			return new JsonResult(ResultCode.SUCCESS_IS_BIND);
		}
		AppUserMultiVo appUserMultiVo = this.selectByPhoneNum(toPhoneNum);
		if(appUserMultiVo != null) {
			logger.info("phoneNum=" + phoneNum + "，该手机已绑定，则此手机号码已被使用过，不能重复绑定。");
			return new JsonResult(ResultCode.SUCCESS_IS_BIND);
		}
		appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
		if(appUserVo == null) {
			return new JsonResult(ResultCode.SUCCESS_NO_USER);
		}
		Map<Object, Object> map = new HashMap<>();
		String cTime = DateUtil.getCurrentLongDateTime();
		appUserMultiVo = this.getAppUserBySystemUserId(phoneNum);
		boolean flag = false;
		if(appUserMultiVo == null) {
			appUserMultiVo = new AppUserMultiVo();
			
			appUserMultiVo.setSystemUserId(phoneNum);
			
			appUserMultiVo.setOwnPhoneNum(appUserVo.getPhoneNum());
			appUserMultiVo.setOwnUserPwd(appUserVo.getUserPwd());
			appUserMultiVo.setTelPhoneNum(toPhoneNum);
			
			appUserMultiVo.setCreateDate(cTime);
			appUserMultiVo.setUpdateDate(cTime);
			flag = this.appUserMultiDao.insertMultiUser(appUserMultiVo);
			
		}else {
			appUserMultiVo.setOwnPhoneNum(toPhoneNum);
			appUserMultiVo.setOwnUserPwd(userPwd);
			appUserMultiVo.setTelPhoneNum(toPhoneNum);
			
			appUserMultiVo.setUpdateDate(cTime);
			flag = this.appUserMultiDao.updateMultiUser(appUserMultiVo);
		}
		if(appUserVo != null) {
			appUserVo.setIsBindPhoneNum(1);
			appUserVo.setIsBindTelPhoneNum(1);
			appUserVo.setUpdateDate(cTime);
			appUserVo.setUserPwd(userPwd);
			appUserVo.setRealPhoneNum(toPhoneNum);
		}
		boolean flag1 = this.appUserService.modifyUser(appUserVo);
		logger.info("phoneNum=" + phoneNum + "，绑定到登录手机，操作结果：更新统一用户标识flag=" + flag + ", 更新用户标志flag1=" + flag1);
		if(flag && flag1) {
			this.userTaskService.finishBindToPhoneNumTask(phoneNum);
			return new JsonResult(ResultCode.SUCCESS, map);
		}else {
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
	}
	
	/**
	 * 微信/支付宝登录后，绑定到话费手机
	 * @param phoneNum
	 * @param toPhoneNum
	 * @return
	 */
	@Transactional
	public JsonResult bindToTelPhoneNum(String phoneNum, String toPhoneNum,
			String mId, String tId, String sId, String uId, HttpServletRequest request) {
		
		if(!StrUtil.null2Str(mId).equals("")  && !StrUtil.null2Str(tId).equals("") && !StrUtil.null2Str(sId).equals("") 
				&& !StrUtil.null2Str(uId).equals("")  && sId.length() == 20 && uId.length() == 32) {
			TraceHisVo traceHisVo = this.traceHisService.getTraceHisByUid(uId);
			if(traceHisVo != null) {
				logger.info("phoneNum=" + phoneNum + "，过期无效请求，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			String paramsStr = "?phoneNum=" + phoneNum + "&toPhoneNum=" + toPhoneNum 
					+ "&mId=" + mId + "&tId=" + tId + "&sId=" + sId;
			boolean flag = GlobalUtils.verifyParams(paramsStr, uId);
			if(!flag) {
				logger.info("phoneNum=" + phoneNum + "，请求md5值校验失败，跳过处理。");
				return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
			}
			//记录历史
			this.traceHisService.addTraceHis(request, "doBindToTelPhoneNum");
		}else {
			logger.info("phoneNum=" + phoneNum + "，参数错误，跳过处理。");
			return new JsonResult(ResultCode.SUCCESS_IS_BUSY);
		}
		
		//逻辑处理
		AppUserVo appUserVo = this.appUserService.selectLoginByPhoneNum(phoneNum);
		if(appUserVo == null) {
			return new JsonResult(ResultCode.SUCCESS_NO_USER);
		}
		Map<Object, Object> map = new HashMap<>();
		String cTime = DateUtil.getCurrentLongDateTime();
		if(appUserVo != null) {
			appUserVo.setIsBindTelPhoneNum(1);
			appUserVo.setUpdateDate(cTime);
		}
		AppUserMultiVo appUserMultiVo = this.getAppUserBySystemUserId(phoneNum);
		boolean flag = false;
		if(appUserMultiVo == null) {
			appUserMultiVo = new AppUserMultiVo();
			
			appUserMultiVo.setSystemUserId(appUserVo.getPhoneNum());
			appUserMultiVo.setTelPhoneNum(toPhoneNum);
			
			appUserMultiVo.setOwnPhoneNum(appUserVo.getPhoneNum());
			appUserMultiVo.setOwnUserPwd(appUserVo.getUserPwd());
			
			appUserMultiVo.setCreateDate(cTime);
			appUserMultiVo.setUpdateDate(cTime);
			flag = this.appUserMultiDao.insertMultiUser(appUserMultiVo);
			
		}else {
			appUserMultiVo.setTelPhoneNum(toPhoneNum);
			appUserMultiVo.setUpdateDate(cTime);
			flag = this.appUserMultiDao.updateMultiUser(appUserMultiVo);
		}
		boolean flag1 = this.appUserService.modifyUser(appUserVo);
		logger.info("phoneNum=" + phoneNum + "，绑定话费手机，操作结果：更新统一用户标识flag=" + flag + ", 更新用户标志flag1=" + flag1);
		if(flag && flag1) {
			return new JsonResult(ResultCode.SUCCESS, map);
		}else {
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
	}
	
}