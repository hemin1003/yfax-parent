package com.yfax.task.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.yfax.task.htt.vo.HttWithdrawHisVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;

import net.sf.json.JSONObject;

/**
 * 支付宝自动打款
 * 
 * @author Minbo
 */
@Service
public class HttAlipayService {

	protected static Logger logger = LoggerFactory.getLogger(HttAlipayService.class);

	private static final String APP_ID = "xxx";
	private static final String APP_PRIVATE_KEY = "xxx";
	private static final String ALIPAY_PUBLIC_KEY = "xxx";
	private static AlipayClient alipayClient;
	
	private static final String payer_show_name = "企业账户";
	private static final String remark = "提现转账";
	
	@Autowired
	private HttWithdrawHisService httWithdrawHisService;
	
	static {
		alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID,
				APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
	}

	/**
	 * 转账
	 */
	public int transfer(String out_biz_no, String payee_account, String amount, 
			String payee_real_name, HttWithdrawHisVo httWithdrawHisVo) {
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizContent("{" +
				"    \"out_biz_no\":\"" + out_biz_no + "\"," +
				"    \"payee_type\":\"ALIPAY_LOGONID\"," +
				"    \"payee_account\":\"" + payee_account + "\"," +
				"    \"amount\":\"" + amount + "\"," +
				"    \"payer_show_name\":\"" + payer_show_name + "\"," +
				"    \"payee_real_name\":\"" + payee_real_name + "\"," +
				"    \"remark\":\"" + remark + "\"," +
				"  }");
		try {
			AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
			String json = response.getBody();
			logger.info("打款操作结果json=" + json);
			JSONObject obj = JSONObject.fromObject(json);
			JSONObject objResp = JSONObject.fromObject(obj.get("alipay_fund_trans_toaccount_transfer_response"));
			if(StrUtil.null2Str(objResp.get("code")).equals("10000")) {
				logger.info("打款成功");
				return 0;
				
			}else {
				if(StrUtil.null2Str(objResp.get("sub_code")).equals("PAYER_BALANCE_NOT_ENOUGH")) {
					return 99;
				}
				if(StrUtil.null2Str(objResp.get("sub_code")).equals("aop.SYSTEM_ERROR")) {
					return 88;
				}
				if(StrUtil.null2Str(objResp.get("sub_code")).equals("isp.unknow-error") 
						&& StrUtil.null2Str(objResp.get("sub_msg")).equals("系统繁忙")) {
					return 77;
				}
				if(httWithdrawHisVo != null) {
					//支付宝账号和姓名不匹配，请确认姓名是否正确
					//收款账号不存在
					if(StrUtil.null2Str(objResp.get("sub_code")).equals("PAYEE_USER_INFO_ERROR")
							|| StrUtil.null2Str(objResp.get("sub_code")).equals("PAYEE_NOT_EXIST")) {
						httWithdrawHisVo.setFailCode(StrUtil.null2Str(objResp.get("sub_code")));
						httWithdrawHisVo.setFailMsg(StrUtil.null2Str(objResp.get("sub_msg")));
						httWithdrawHisVo.setStatus("提现失败，金币已返还-" + StrUtil.null2Str(objResp.get("sub_msg")));
						logger.warn("打款失败");
						return 4;
					}
				}
				return 55;
			}			
		} catch (AlipayApiException e) {
			logger.error("转账异常：" + e.getMessage(), e);
			return 2;
		}
	}

	/**
	 * 查询
	 */
	public int query(String out_biz_no) {
		AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
		request.setBizContent("{" +
		"    \"out_biz_no\":\"" + out_biz_no + "\" "
		+ "}");
		try {
			AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
			String json = response.getBody();
			logger.info("查询打款结果json=" + json);
			JSONObject obj = JSONObject.fromObject(json);
			JSONObject objResp = JSONObject.fromObject(obj.get("alipay_fund_trans_order_query_response"));
			if(StrUtil.null2Str(objResp.get("code")).equals("10000") && 
					StrUtil.null2Str(objResp.get("msg")).equals("Success")) {
				logger.info("订单已打款，不能重复打款");
				return 0;
			}
			if(StrUtil.null2Str(objResp.get("code")).equals("40004") 
					&& StrUtil.null2Str(objResp.get("sub_code")).equals("ORDER_NOT_EXIST")) {
				logger.info("订单不存在，可以打款");
				return 4;
				
			}else {
				return 1;
			}
		} catch (AlipayApiException e) {
			logger.error("查询异常：" + e.getMessage(), e);
			return 3;
		}
	}
	
	/**
	 * 悦头条-新版-支付宝批量打款
	 */
	public void alipayBatchPayHtt() {
		List<HttWithdrawHisVo> list = null;  //this.httWithdrawHisService.getList()
		if (list == null) {
			logger.info("无记录，无需打款。");
		}else {
			for (HttWithdrawHisVo httWithdrawHisVo : list) {
				logger.info("---------------------------------------------");
				logger.info("--------------------start--------------------");
				try {
					String name = StrUtil.null2Str(httWithdrawHisVo.getName());
					String amount =httWithdrawHisVo.getIncome().substring(1);
					String tradeNo = httWithdrawHisVo.getId();
					String account = StrUtil.null2Str(httWithdrawHisVo.getAccount());
					logger.info("phoneNum=" + httWithdrawHisVo.getPhoneNum()
							+ ", tradeNo=" + tradeNo
							+ ", name=" + httWithdrawHisVo.getName() 
							+ ", amount=" + amount 
							+ ", account=" + account);
					if(name.equals("") || account.equals("")) {
						logger.warn("提现数据信息不完整，不能提现，作为打款失败处理。");
						httWithdrawHisVo.setStatus("提现失败，金币已返还--个人信息不能为空");
//						this.httWithdrawHisService.failProcess(httWithdrawHisVo);
						continue;
					}
					int flag = this.query(tradeNo);
					if (flag == 0) {
						logger.info("之前已经打款成功，直接更新状态即可。");
						this.updateHttWithdrawHis(httWithdrawHisVo);

					// 不存在此订单数据
					} else if (flag == 4) {
						logger.info("开始打款，然后更新状态。");
						int result = this.transfer(tradeNo, account, amount, name, httWithdrawHisVo);
						if (result == 99) {
							logger.error("打款，后台企业支付宝账户支付余额不足，停止打款", new RuntimeException("余额不足，停止此次打款"));
							break;
						}
						if (result == 88) {
							logger.error("打款，后台企业支付宝账户系统异常，停止打款", new RuntimeException("支付宝账户异常，停止此次打款"));
							break;
						}
						if (result == 77) {
							logger.error("支付宝账户系统繁忙，休眠两秒");
							Thread.sleep(2000);
						}
						if (result == 55) {
							logger.error("打款报错，请检查。", 
									new RuntimeException("打款报错，跳过该用户打款"));
							continue;
						}
						if (result == 0) {
							//打款成功
							this.updateHttWithdrawHis(httWithdrawHisVo);
							
						}else if(result == 4){
							//打款失败
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
	
//	public static void main(String[] args) {
//		HttAlipayService httAlipayService = new HttAlipayService();
//		
//		String out_biz_no = "498884M20180102230948R6B32K5QK";
//		//查询测试
//		int flag = httAlipayService.query(out_biz_no);
////		if (flag == 4) {
////			//转账测试
////			int flag2 = httAlipayService.transfer(out_biz_no, "hemin_it@163.com", "1", "贺敏", null);
////		}
//	}
}