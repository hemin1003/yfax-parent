package com.yfax.task.htt.service;

import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.yfax.task.htt.utils.ConfigUtil;
import com.yfax.task.htt.utils.FsClientWithCertSSL;
import com.yfax.task.htt.utils.MapUtils;
import com.yfax.task.htt.utils.PayCommonUtil;
import com.yfax.task.htt.utils.WeixinConstant;
import com.yfax.task.htt.utils.WeixinPayConfig;
import com.yfax.task.htt.utils.XMLUtil;
import com.yfax.task.htt.vo.HttWithdrawHisVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;

@Service
public class WechatEpayService {

	protected static final Logger logger = Logger.getLogger(WechatEpayService.class);
	
	@Autowired
	private HttWithdrawHisService httWithdrawHisService;
	
	/**
	 * 悦头条-新版-微信批量打款
	 */
	public void wechatBatchPayHtt() {
		List<HttWithdrawHisVo> list = null; 	//this.httWithdrawHisService.getList()
		if (list == null) {
			logger.info("无记录，无需打款。");
		}else {
			for (HttWithdrawHisVo httWithdrawHisVo : list) {
				logger.info("---------------------------------------------");
				logger.info("--------------------start--------------------");
				try {
					String openId = StrUtil.null2Str(httWithdrawHisVo.getAccount());
					String ip = "xxx";
					String amount = String.valueOf(Integer.valueOf(httWithdrawHisVo.getIncome().substring(1)) * 100);
					String tradeNo = httWithdrawHisVo.getId();
					String re_user_name = StrUtil.null2Str(httWithdrawHisVo.getName());
					logger.info("openId=" + openId + ", ip=" + ip + ", amount=" + amount + ", tradeNo=" + tradeNo
							+ ", re_user_name=" + re_user_name);
					if(openId.equals("") || re_user_name.equals("")) {
						logger.warn("提现数据信息不完整，不能提现，作为打款失败处理。");
						httWithdrawHisVo.setStatus("提现失败，金币已返还--个人信息不能为空");
//						this.httWithdrawHisService.failProcess(httWithdrawHisVo);
						continue;
					}
					int flag = this.doEquery(tradeNo);
					if(flag == 88) {
						logger.error("查询，后台企业微信账户系统异常，停止打款", new RuntimeException("微信账户异常，停止此次打款"));
						break;
						
					}else if(flag == 77) {
						logger.error("查询，后台企业微信账户系统订单正在处理中", new RuntimeException("微信账户订单正在处理中，跳过处理"));
						continue;
						
					}else if (flag == 0) {
						logger.info("之前已经打款成功，直接更新状态即可。");
						this.updateHttWithdrawHis(httWithdrawHisVo);

					// 不存在此订单数据
					} else if (flag == 4) {
						logger.info("开始打款，然后更新状态。");
						int result = this.doEpay(openId, ip, amount, tradeNo, re_user_name, httWithdrawHisVo);
						if (result == 99) {
							logger.error("打款，后台企业微信账户支付余额不足，停止打款", new RuntimeException("余额不足，停止此次打款"));
							break;
						}
						if (result == 88) {
							logger.error("打款，后台企业微信账户系统异常，停止打款", new RuntimeException("微信账户异常，停止此次打款"));
							break;
						}
						if (result == 77) {
							logger.error("打款速度过快，微信账户处理繁忙，休眠两秒");
							Thread.sleep(2000);
						}
						if (result == 66) {
							logger.error("该用户今日付款次数超过限制,如有需要请登录微信支付商户平台更改API安全配置。", 
									new RuntimeException("该用户今日付款次数超过限制，跳过该用户的打款"));
							continue;
						}
						if (result == 55) {
							logger.error("打款报错，请检查。", 
									new RuntimeException("打款报错，跳过该用户打款"));
							continue;
						}
						if (result == 0) {	//打款成功
							this.updateHttWithdrawHis(httWithdrawHisVo);
							
						//打款失败
						}else if(result == 4){
//							this.httWithdrawHisService.failProcess(httWithdrawHisVo);
						}
					}
				} catch (Exception e) {
					logger.error("提现记录异常：id=" + httWithdrawHisVo.getId() + ", msg=" + e.getMessage(), e);
				}
				logger.info("--------------------end--------------------");
				logger.info("---------------------------------------------");
			}
		}
	}
	
	/**
	 * 更新提现记录-‘提现成功’状态
	 * 
	 * @param httWithdrawHisVo
	 */
	private void updateHttWithdrawHis(HttWithdrawHisVo httWithdrawHisVo) {
		httWithdrawHisVo.setStatus("提现成功");
		httWithdrawHisVo.setStatusType(2);
		httWithdrawHisVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
//		boolean status = this.httWithdrawHisService.updateHttWithdrawHis(httWithdrawHisVo);
//		if (status) {
//			logger.info("状态更新成功 for 提现成功");
//		} else {
//			logger.info("状态更新失败 for 提现成功");
//		}
	}

	/**
	 * @param openId
	 * @param ip
	 * @param amount
	 * @param tradeNo
	 * @param re_user_name
	 * @param httWithdrawHisVo
	 * @return 99表示余额不足
	 */
	public int doEpay(String openId, String ip, String amount, String tradeNo, 
			String re_user_name, HttWithdrawHisVo httWithdrawHisVo) {
		if (Strings.isNullOrEmpty(tradeNo) || Strings.isNullOrEmpty(openId)) {
			logger.info("企业支付开始，openid: " + openId + "  tradeNo: " + tradeNo);
			return 1;
		}
		int amountInt = Integer.valueOf(amount).intValue();
//		if (amountInt < 100 || Strings.isNullOrEmpty(amount)) {
//			logger.info("企业支付开始=> 金额不得少于100分(1元)");
//			return 2;
//		}
		try {
			if (Strings.isNullOrEmpty(ip)) {
				InetAddress addr = InetAddress.getLocalHost();
				ip = addr.getHostAddress().toString();
			}
			logger.info("企业支付开始，用户:" + openId + "  订单号:" + tradeNo + " 金额:" + amount + ", ip=" + ip);
			// 设置支付参数
			SortedMap<String, Object> parameters = getSignParams(tradeNo, openId, amountInt, ip, re_user_name);
			parameters.put("sign", PayCommonUtil.createSignPublic(Charsets.UTF_8.toString(), parameters));// sign签名
			String requestXML = PayCommonUtil.getRequestXml(parameters);// 生成xml格式字符串
			String responseStr = FsClientWithCertSSL.doPost(ConfigUtil.PROMOTION_URL, requestXML);
			// 解析结果
			Map<String, Object> resutlMap = XMLUtil.doXMLParse(responseStr);
			logger.info("企业付款，微信返回结果：resutlMap=" + resutlMap.toString());
			// 校验响应结果return_code
			if (resutlMap != null && WeixinConstant.FAIL.equalsIgnoreCase(resutlMap.get("return_code").toString())) {
				logger.warn("企业支付调用失败");
				return 3;
			}
			if(StrUtil.null2Str(resutlMap.get("err_code")).equals("SENDNUM_LIMIT")) {
				return 66;
			}
			if(StrUtil.null2Str(resutlMap.get("err_code")).equals("NOTENOUGH")) {
				return 99;
			}
			if(StrUtil.null2Str(resutlMap.get("err_code")).equals("SYSTEMERROR") 
					&& StrUtil.null2Str(resutlMap.get("err_code_des")).equals("系统繁忙，请稍后再试")) {
				return 77;
			}
			if(StrUtil.null2Str(resutlMap.get("err_code")).equals("SYSTEMERROR")) {
				return 88;
			}
			if (resutlMap != null && WeixinConstant.FAIL.equals(resutlMap.get("result_code"))) {
				logger.warn("企业付款失败：tradeNo=" + tradeNo + "，失败原因：return_msg=" + resutlMap.get("return_msg"));
				//真实姓名不一致.
				//非实名用户账号不可发放
				//用户账号被冻结，无法付款
				if(StrUtil.null2Str(resutlMap.get("err_code")).equals("NAME_MISMATCH")
						|| StrUtil.null2Str(resutlMap.get("err_code")).equals("V2_ACCOUNT_SIMPLE_BAN")
						|| StrUtil.null2Str(resutlMap.get("err_code")).equals("NO_AUTH")) {
					if(httWithdrawHisVo != null) {
						httWithdrawHisVo.setFailCode(resutlMap.get("err_code").toString());
						httWithdrawHisVo.setFailMsg(resutlMap.get("err_code_des").toString());
						httWithdrawHisVo.setStatus("提现失败，金币已返还--" + resutlMap.get("err_code_des").toString());
						return 4;
					}
				}
				return 55;
			}
			if (WeixinConstant.SUCCESS.equalsIgnoreCase(resutlMap.get("result_code").toString())) {
				Map<String, Object> map = buildClientJson(resutlMap);
				logger.info("企业付款成功：" + map.toString());
				return 0;
			}
		} catch (Exception e) {
			logger.error("企业付款异常：" + e.getMessage(), e);
			return 5;
		}
		return 1;
	}

	public int doEquery(String outTradeNo) {
		if (Strings.isNullOrEmpty(outTradeNo)) {
			return 1;
		}
		try {
			// 组装查询参数
			SortedMap<String, Object> params = buildQueryParams(outTradeNo);
			String requestXML = PayCommonUtil.getRequestXml(params);// 生成xml格式字符串
			// 带上post请求支付查询接口
			String responseStr = FsClientWithCertSSL.doPost(ConfigUtil.PROMOTION_QUERY_URL, requestXML);
			SortedMap<String, Object> responseMap = XMLUtil.doXMLParse(responseStr);// 解析响应xml格式字符串
			logger.info("企业付款查询=> " + responseMap.toString());
			// 校验响应结果return_code
			if (WeixinConstant.FAIL.equalsIgnoreCase(responseMap.get("return_code").toString())) {
				logger.warn("企业支付调用失败");
				return 2;
			}
			// 校验业务结果result_code
			if (WeixinConstant.FAIL.equalsIgnoreCase(responseMap.get("result_code").toString())) {
				if("NOT_FOUND".equalsIgnoreCase(responseMap.get("err_code").toString())) {
					logger.warn("指定单号数据不存在");
					return 4;
				}
				return 3;
			}
			if(StrUtil.null2Str(responseMap.get("err_code")).equals("SYSTEMERROR") && 
					StrUtil.null2Str(responseMap.get("status")).equals("PROCESSING")) {
				return 77;
			}
			if(StrUtil.null2Str(responseMap.get("err_code")).equals("SYSTEMERROR")) {
				return 88;
			}
			if (WeixinConstant.SUCCESS.equalsIgnoreCase(responseMap.get("result_code").toString())) {
				logger.warn("已打款，不能重复打款");
				return 0;
			}
			// 组装响应数据
			Map<String, Object> resultMap = buildResponse(responseMap);
			logger.info("企业付款查询结果：" + resultMap.toString());
			return -1;
		} catch (Exception e) {
			logger.error("付款查询异常：" + e.getMessage(), e);
			return 5;
		}
	}

	/**
	 * 组装响应数据
	 * 
	 * @param resutlMap
	 *            付款响应结果
	 * @return
	 */
	private Map<String, Object> buildClientJson(Map<String, Object> resutlMap) {

		if (resutlMap == null || resutlMap.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, Object> returnMap = ImmutableMap.<String, Object>builder()
				.put("trade_no", resutlMap.get("partner_trade_no")).put("payment_no", resutlMap.get("payment_no"))
				.put("payment_time", resutlMap.get("payment_time")).put("result_msg", resutlMap.get("result_code"))
				.build();
		return returnMap;
	}

	/**
	 * 组合sign签名参数
	 * 
	 * @return
	 */
	private SortedMap<String, Object> getSignParams(String tradeNo, String openId, int amount, String ip,
			String re_user_name) {
		Map<String, Object> oparams = ImmutableMap.<String, Object>builder().put("mch_appid", WeixinPayConfig.APPID)
				// 服务号的应用号
				.put("desc", WeixinConstant.EPAY_DESC)
				// 企业付款描述信息
				.put("mchid", WeixinPayConfig.MCHID)
				// 商户号
				.put("nonce_str", PayCommonUtil.CreateNoncestr())
				// 16随机字符串(大小写字母加数字)
				.put("device_info", PayCommonUtil.createConceStr(32).toUpperCase())// 设备号 暂时写死
				.put("partner_trade_no", tradeNo)// 商户订单号
				.put("openid", openId)// 用户openid 注意:微信的openid
				// .put("check_name", "NO_CHECK")// 不校验真实姓名 看后期情况
				.put("check_name", "FORCE_CHECK")// 校验真实姓名
				.put("re_user_name", re_user_name)// 校验真实姓名
				.put("amount", amount)// 金额
				.put("spbill_create_ip", ip)// ip地址
				.build();
		return MapUtils.sortMap(oparams);
	}

	/**
	 * 封装查询结果数据
	 * 
	 * @param responseMap
	 *            查询结果
	 * @return
	 */
	private Map<String, Object> buildResponse(SortedMap<String, Object> responseMap) {
		Map<String, Object> returnMap = ImmutableMap.<String, Object>builder()
				.put("trade_no", responseMap.get("partner_trade_no")).put("payment_no", responseMap.get("detail_id"))
				.put("payment_account", responseMap.get("payment_amount"))
				.put("transfer_time", responseMap.get("transfer_time"))
				.put("result_code", responseMap.get("result_code")).build();
		return returnMap;
	}

	/**
	 * 组装查询参数
	 * 
	 * @param outTradeNo
	 * @return
	 */
	private SortedMap<String, Object> buildQueryParams(String outTradeNo) {
		// 组装查询参数- 可以使用treemap
		Map<String, Object> queryParams = ImmutableMap.<String, Object>builder().put("appid", WeixinPayConfig.APPID)// 商户号的appid
				.put("mch_id", WeixinPayConfig.MCHID)// 商户号
				.put("nonce_str", PayCommonUtil.CreateNoncestr())// 16随机字符串(大小写字母加数字)
				.put("partner_trade_no", outTradeNo)// 商户订单号
				.build();
		// key ASCII 排序
		SortedMap<String, Object> sortMap = MapUtils.sortMap(queryParams);
		// MD5签名
		String createSign = PayCommonUtil.createSignPublic(Charsets.UTF_8.toString(), sortMap);
		sortMap.put("sign", createSign);
		return sortMap;
	}

	// 测试入口
	public static void main(String[] args) {
		WechatEpayService wechat = new WechatEpayService();
		
		// 企业支付-微信实名制打款
//		wechat.doEpay("o12r30j79UJIR9f9kuPGixLL75cQ", "192.168.0.200", "100", "494642M20171112175102RIR2TJGQJ", "易海洋");
		
//		wechat.doEpay("o12r30p2YmgFMQPuwblkz8-LJGQI", "192.168.0.200", "1", "645840M20171113151253R1HIUXA4G", "蔡永军", null);

//		// 企业支付查询
		wechat.doEquery("145751M20180102165651RZCHTWVGQ");
	}
	
}
