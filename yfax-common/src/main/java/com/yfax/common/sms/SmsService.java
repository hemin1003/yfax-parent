package com.yfax.common.sms;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 短信服务
 * @author Minbo
 */
public class SmsService {
	
	protected static Logger logger = LoggerFactory.getLogger(SmsService.class);
	
	public static final String ACOUNT_SID = "xxx";
	public static final String AUTH_TOKEN = "xxx";

	/**
	 * 发送验证码短信
	 * @param phoneNum	手机号码
	 * @param msgCode	验证码
	 * @param project	项目标识
	 */
	public static HashMap<String, Object> sendSms(String phoneNum, String msgCode, String project, 
			final String templateId, final String appId) {
		logger.info("发送验证码短信，phoneNum=" + phoneNum + ", msgCode=" + msgCode + ", project=" + project);
		logger.info("acount_sid=" + ACOUNT_SID + ", auth_token=" + AUTH_TOKEN);
		logger.info("templateId=" + templateId + ", appId=" + appId);
		
		//初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		
		//******************************注释*********************************************
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
//		restAPI.init("sandboxapp.cloopen.com", "8883");
		restAPI.init("app.cloopen.com", "8883");
		
		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
		restAPI.setAccount(ACOUNT_SID, AUTH_TOKEN);
		
		
		//******************************注释*********************************************
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		//*******************************************************************************
		restAPI.setAppId(appId);
		
		
		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信                                                                  *
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。																														       *
		//**************************************************************************************************
		
		//**************************************举例说明***********************************************************************
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
		HashMap<String, Object> result = restAPI.sendTemplateSMS(phoneNum, templateId, new String[]{msgCode, "10"});
		logger.info("短信发送结果，result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			logger.info("发送成功。");
			
//			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){
//				Object object = data.get(key);
//				logger.info(key +" = "+object);
//			}
		}else{
			//异常返回输出错误码和错误信息
			logger.warn("发送失败。错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		return result;
	}

	/**
	 * 发送用户拉活短信
	 * @param phoneNum	手机号码
	 * @param gold		金币值
	 * @param linkUrl	链接地址
	 * @param project	项目标识
	 */
	public static HashMap<String, Object> sendUserSms(String phoneNum, String gold, String linkUrl, String project, 
			final String templateId, final String appId) {
		logger.info("发送用户拉活短信，phoneNum=" + phoneNum + ", project=" + project);
		logger.info("acount_sid=" + ACOUNT_SID + ", auth_token=" + AUTH_TOKEN);
		logger.info("templateId=" + templateId + ", appId=" + appId);
		
		//初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		
		//******************************注释*********************************************
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
//		restAPI.init("sandboxapp.cloopen.com", "8883");
		restAPI.init("app.cloopen.com", "8883");
		
		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
		restAPI.setAccount(ACOUNT_SID, AUTH_TOKEN);
		
		
		//******************************注释*********************************************
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		//*******************************************************************************
		restAPI.setAppId(appId);
		
		
		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信                                                                  *
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。																														       *
		//**************************************************************************************************
		
		//**************************************举例说明***********************************************************************
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
		HashMap<String, Object> result = restAPI.sendTemplateSMS(phoneNum, templateId, new String[]{gold, linkUrl});
		logger.info("短信发送结果，result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			logger.info("发送成功。");
			
//			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){
//				Object object = data.get(key);
//				logger.info(key +" = "+object);
//			}
		}else{
			//异常返回输出错误码和错误信息
			logger.warn("发送失败。错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		return result;
	}
}
