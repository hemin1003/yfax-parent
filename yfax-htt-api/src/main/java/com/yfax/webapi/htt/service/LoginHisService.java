//package com.yfax.webapi.htt.service;
//
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.yfax.webapi.htt.dao.LoginHisDao;
//import com.yfax.webapi.htt.vo.LoginHisVo;
//
///**
// * 记录用户登录历史
// * @author Minbo
// */
//@Service
//public class LoginHisService{
//	
//	protected static Logger logger = LoggerFactory.getLogger(LoginHisService.class);
//	
//	@Autowired
//	private LoginHisDao dao;
//	
//	public LoginHisVo selectLoginHisDate(String phoneNum) {
//		return this.dao.selectLoginHisDate(phoneNum);
//	}
//	
//	public boolean addLoginHis(LoginHisVo loginHisVo){
//		try {
//			return this.dao.insertLoginHis(loginHisVo);
//		} catch (Exception e) {
//			logger.error("记录用户登录历史异常：" + e.getMessage(), e);
//			return false;
//		}
//	}
//	
//	public boolean modifyLoginHis(LoginHisVo loginHisVo){
//		try {
//			return this.dao.updateLoginHis(loginHisVo);
//		} catch (Exception e) {
//			logger.error("更新用户登录历史异常：" + e.getMessage(), e);
//			return false;
//		}
//	}
//	
//	/**
//	 * 查询imei当日登录设备的账户数
//	 * @param params
//	 * @return
//	 */
//	public Long queryCountOfLoginHisImei(Map<String, Object> params) {
//		return this.dao.selectCountOfLoginHisImei(params);
//	}
//	
//	/**
//	 * 查询imei当日账户登录的设备数
//	 * @param params
//	 * @return
//	 */
//	public Long queryCountOfLoginHisPhoneNum(Map<String, Object> params) {
//		return this.dao.selectCountOfLoginHisPhoneNum(params);
//	}
//
//}
