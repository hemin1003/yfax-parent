package com.yfax.task.htt.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttSysExpenseReportInfoDao;
import com.yfax.task.htt.vo.HttSysExpenseReportInfoVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;

/**
 * 金币支出统计
 * @author Minbo
 */
@Service
public class HttSysExpenseReportInfoService {
	
	protected static Logger logger = LoggerFactory.getLogger(HttSysExpenseReportInfoService.class);
	
	@Autowired
	private HttSysExpenseReportInfoDao httSysReportInfoDao;
	@Autowired
	private HttExpenseInfoService httExpenseInfoService;

	/**
	 * 用户总金币
	 */
	public HttSysExpenseReportInfoVo selectAllGold() {
		return this.httSysReportInfoDao.selectAllGold();
	}
	
	/**
	 * 用户金币余额
	 */
	public HttSysExpenseReportInfoVo selectUserGoldOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserGoldOfDay(params);
	}

	/**
	 * 当日新增用户数
	 */
	public HttSysExpenseReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectNewUserOfDay(params);
	}
	
	/**
	 * 全部邀请奖励
	 */
	public HttSysExpenseReportInfoVo selectAllInviteAwardOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllInviteAwardOfDay(params);
	}
	
	/**
	 * 全部阅读奖励
	 */
	public HttSysExpenseReportInfoVo selectAllReadAwardOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllReadAwardOfDay(params);
	}
	
	/**
	 * 全部其他奖励
	 */
	public HttSysExpenseReportInfoVo selectAllOtherAwardOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllOtherAwardOfDay(params);
	}
	
	/**
	 * 申请提现金额
	 */
	public HttSysExpenseReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectUserWithdrawOfDay(params);
	}
	
	/**
	 * 成功打款金额
	 */
	public HttSysExpenseReportInfoVo selectSuccessWithdrawOfDay(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectSuccessWithdrawOfDay(params);
	}

	/**
	 * 预计总支出金额
	 */
	public HttSysExpenseReportInfoVo selectAllUserWithdraw(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllUserWithdraw(params);
	}

	/**
	 * 实际已支出总金额
	 */
	public HttSysExpenseReportInfoVo selectAllSuccessWithdraw(Map<String, Object> params) {
		return this.httSysReportInfoDao.selectAllSuccessWithdraw(params);
	}
	
	/**
	 * @param day 0-6
	 * @param type 101=当日新增用户数, 102=全部邀请奖励, 103=全部阅读奖励, 104=全部其他奖励, 
	 * 105=申请提现金额, 106=成功打款金额，107=用户总金币，108=用户当日金币，109=预计总支出金额，110=实际已支出总金额
	 * @return
	 */
	public String getResult(int day, int type, int withdrawType) {
//		Map<String, Object> params = new HashMap<>();
//		params.put("currentTime", DateUtil.getCurrentDate(day));
//		params.put("tomorrowTime", DateUtil.getCurrentDate(day+1));
		Map<String, Object> params = new HashMap<>();
//		String curTime = DateUtil.getCurrentLongDateTime();
//		params.put("lastHour", DateUtil.formatLongDate(DateUtils.addHours(DateUtil.parseLongDateTime(curTime), -1)) );
//		params.put("currentTime", curTime);
		params.put("lastHour", DateUtil.getCurrentDate(day));
		params.put("currentTime", DateUtil.getCurrentDate(day+1));
		params.put("withdrawType", withdrawType);
		//格式化，保留三位小数，四舍五入
		HttSysExpenseReportInfoVo sysExpenseReportInfoVo = new HttSysExpenseReportInfoVo();
		String result = "";
		switch (type) {
			case 101:
				result = this.selectNewUserOfDay(params).getValue();
				result = StrUtil.null2Str(result).equals("")?"0":result;
				this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "newUser", 
						"新增（注册用户）", result);
				return result;
			case 102:
				result = this.selectAllInviteAwardOfDay(params).getValue();
				result = StrUtil.null2Str(result).equals("")?"0":result;
				this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "allInviteAward", 
						"全部邀请奖励", result);
				return result;	
			case 103:
				result = this.selectAllReadAwardOfDay(params).getValue();
				result = StrUtil.null2Str(result).equals("")?"0":result;
				this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "allReadAward", 
						"全部阅读奖励", result);
				return result;	
			case 104:
				result = this.selectAllOtherAwardOfDay(params).getValue();
				result = StrUtil.null2Str(result).equals("")?"0":result;
				this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "allOtherAward", 
						"全部其他奖励", result);
				return result;	
			case 105:
				sysExpenseReportInfoVo = this.selectUserWithdrawOfDay(params);
				if(StrUtil.null2Str(sysExpenseReportInfoVo.getValue()).equals("")) {
					sysExpenseReportInfoVo.setValue("0");
				}
				if(withdrawType == 2) {
					this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "userWithdrawOfAlipay", 
							"支付宝申请提现金额（元）", sysExpenseReportInfoVo.getValue());
				}else if(withdrawType == 3) {
					this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "userWithdrawOfWechat", 
							"微信申请提现金额（元）", sysExpenseReportInfoVo.getValue());
				}
				return sysExpenseReportInfoVo.getValue();
			case 106:
				sysExpenseReportInfoVo = this.selectSuccessWithdrawOfDay(params);
				if(StrUtil.null2Str(sysExpenseReportInfoVo.getValue()).equals("")) {
					sysExpenseReportInfoVo.setValue("0");
				}
				if(withdrawType == 2) {
					this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "successWithdrawOfAlipay", 
							"支付宝成功打款金额（元）", sysExpenseReportInfoVo.getValue());
				}else if(withdrawType == 3) {
					this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "successWithdrawOfWechat", 
							"微信成功打款金额（元）", sysExpenseReportInfoVo.getValue());
				}
				return sysExpenseReportInfoVo.getValue();
			case 107:
				result = this.selectAllGold().getValue();
				result = StrUtil.null2Str(result).equals("")?"0":result;
				return result;	
			case 108:
				result = this.selectUserGoldOfDay(params).getValue();
				result = StrUtil.null2Str(result).equals("")?"0":result;
				this.httExpenseInfoService.addHttExpenseInfo(DateUtil.getCurrentDate(day), "allAward", 
						"全部金币奖励", result);
				return result;	
			case 109:
				result = this.selectAllUserWithdraw(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			case 110:
				result = this.selectAllSuccessWithdraw(params).getValue();
				return StrUtil.null2Str(result).equals("")?"0":result;	
			default:
				throw new RuntimeException("错误类型，请检查。day=" + day + ", type=" + type);
		}
	}
	
	/**
	 * 支出统计数据处理
	 */
	public void processHttExpenseInfo() {
		logger.info("开始支出统计数据处理...");
//		Map<String, Object> params = new HashMap<>();
		int j = -1;
//		int j = 0;
//		params.put("currentTime", DateUtil.getCurrentDate(j));
//		params.put("tomorrowTime", DateUtil.getCurrentDate(0));
		ExpenseInfoList expenseInfo = new ExpenseInfoList();
		expenseInfo.setDateStr(DateUtil.getCurrentDate(j));;
		expenseInfo.setNewUser(this.getResult(j, 101, 0));
		expenseInfo.setAllInviteAward(this.getResult(j, 102, 0));
		expenseInfo.setAllReadAward(this.getResult(j, 103, 0));
		expenseInfo.setAllOtherAward(this.getResult(j, 104, 0));
		expenseInfo.setAllAward(this.getResult(j, 108, 0));
		expenseInfo.setUserWithdrawOfAlipay(this.getResult(j, 105, 2));
		expenseInfo.setSuccessWithdrawOfAlipay(this.getResult(j, 106, 2));
		expenseInfo.setUserWithdrawOfWechat(this.getResult(j, 105, 3));
		expenseInfo.setSuccessWithdrawOfWechat(this.getResult(j, 106, 3));
		logger.info(expenseInfo.toString());
		logger.info("支出统计数据处理完成");
	}
	
	public class ExpenseInfoList implements Serializable{
		
		private static final long serialVersionUID = -79583709832163829L;
		
		private String dateStr;
		private String allAward;
		private String allInviteAward;
		private String allOtherAward;
		private String allReadAward;
		private String newUser;
		private String successWithdrawOfAlipay;
		private String successWithdrawOfWechat;
		private String userWithdrawOfAlipay;
		private String userWithdrawOfWechat;

		public String getDateStr() {
			return dateStr;
		}
		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}
		public String getAllAward() {
			return allAward;
		}
		public void setAllAward(String allAward) {
			this.allAward = allAward;
		}
		public String getAllInviteAward() {
			return allInviteAward;
		}
		public void setAllInviteAward(String allInviteAward) {
			this.allInviteAward = allInviteAward;
		}
		public String getAllOtherAward() {
			return allOtherAward;
		}
		public void setAllOtherAward(String allOtherAward) {
			this.allOtherAward = allOtherAward;
		}
		public String getAllReadAward() {
			return allReadAward;
		}
		public void setAllReadAward(String allReadAward) {
			this.allReadAward = allReadAward;
		}
		public String getNewUser() {
			return newUser;
		}
		public void setNewUser(String newUser) {
			this.newUser = newUser;
		}
		public String getSuccessWithdrawOfAlipay() {
			return successWithdrawOfAlipay;
		}
		public void setSuccessWithdrawOfAlipay(String successWithdrawOfAlipay) {
			this.successWithdrawOfAlipay = successWithdrawOfAlipay;
		}
		public String getSuccessWithdrawOfWechat() {
			return successWithdrawOfWechat;
		}
		public void setSuccessWithdrawOfWechat(String successWithdrawOfWechat) {
			this.successWithdrawOfWechat = successWithdrawOfWechat;
		}
		public String getUserWithdrawOfAlipay() {
			return userWithdrawOfAlipay;
		}
		public void setUserWithdrawOfAlipay(String userWithdrawOfAlipay) {
			this.userWithdrawOfAlipay = userWithdrawOfAlipay;
		}
		public String getUserWithdrawOfWechat() {
			return userWithdrawOfWechat;
		}
		public void setUserWithdrawOfWechat(String userWithdrawOfWechat) {
			this.userWithdrawOfWechat = userWithdrawOfWechat;
		}
		@Override
		public String toString() {
			return "ExpenseInfoList [dateStr=" + dateStr + ", allAward=" + allAward + ", allInviteAward="
					+ allInviteAward + ", allOtherAward=" + allOtherAward + ", allReadAward=" + allReadAward
					+ ", newUser=" + newUser + ", successWithdrawOfAlipay=" + successWithdrawOfAlipay
					+ ", successWithdrawOfWechat=" + successWithdrawOfWechat + ", userWithdrawOfAlipay="
					+ userWithdrawOfAlipay + ", userWithdrawOfWechat=" + userWithdrawOfWechat + "]";
		}
	}
}
