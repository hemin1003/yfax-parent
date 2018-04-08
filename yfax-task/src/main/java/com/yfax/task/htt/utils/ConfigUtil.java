package com.yfax.task.htt.utils;

/**
 *  微信支付相关配置信息
 */
public class ConfigUtil {
	
	// 微信支付统一接口的回调actionhttp://newcapi2.test.xxxxx.com
	// 本地 使用 public final static String NOTIFY_URL = "http://newcapi2.test.xxxxx.com/weixin/pay/callback/pay.action";
	// 测试服 使用 
//	public final static String NOTIFY_URL = "https://pay-test1.xxxxxx.com/weixin/pay/callback/pay.action";
	// 正式服 使用如下
//	public final static String NOTIFY_URL = "https://pay.xxxxxx.com/weixin/pay/callback/pay.action";
	// 微信支付成功支付后跳转的地址 web端使用
//	public final static String SUCCESS_URL = "http://14.117.25.80:8016/wxweb/contents/config/pay_success.jsp";
	// oauth2授权时回调action
//	public final static String REDIRECT_URI = "http://14.117.25.80:8016/GoMyTrip/a.jsp?port=8016";
	/**
	 * 微信基础接口地址
	 */
	// 获取token接口(GET)
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// oauth2授权接口(GET)
	public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 刷新access_token接口（GET）
	public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	// 菜单创建接口（POST）
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询（GET）
	public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（GET）
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	
	/**
	 * 微信支付接口地址
	 */
	// 微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	// 退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	// 短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	// 接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	
	/**
	 * 企业付款地址
	 */
	// 企业付款接口(POST)
	public final static String PROMOTION_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	// 企业付款查询接口(POST)
	public final static String PROMOTION_QUERY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
}
