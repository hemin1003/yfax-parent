package com.yfax.task.htt.utils;

/**
 * 微信公众号支付配置信息
 */
public class WeixinPayConfig {
	// 公众号appid
	public final static String APPID = "wx6a6a47bf19588c38"; //
	// 公众号appsecret
	public final static String APPSECRET = "083bcdc3ba76ea809fda05b0a574cbc6";
	// 微信企业支付证书
	 public static String CERT_FILE = System.getProperty("user.dir") + System.getProperty("file.separator") 
	 	+ "cert" + System.getProperty("file.separator") + "apiclient_cert.p12";
//	public static String CERT_FILE = "cert/apiclient_cert.p12";
	// 公众平台商户ID
	public final static String MCHID = "1491435232";
	//API密钥
	public final static String API_KEY = "xxxxx";
	// 公众平台商户KEY
	public final static String KEY = "WINnEi7Rx2JXd8UCMG5VlBlRlSNjQUVM";
	
	// 微信商户平台支付结果通知URL
	// 线上环境
	public final static String NOTIFY_URL = "https://pay.xxxxx.com/weixinpay/jsapi/callback/pay.action";
	// 统一下单URL
	public final static String WECHAT_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 查询订单URL
	public final static String WECHAT_ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

	// 获取token接口(GET)
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	// ticket 接口 (GET)
	public final static String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	// oauth2授权接口(GET)
	public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
}