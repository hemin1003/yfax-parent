package com.yfax.task.htt.dao;

import java.util.Map;

import com.yfax.task.htt.vo.HttSysExpenseReportInfoVo;

public interface HttSysExpenseReportInfoDao {
	
	/**
	 * 用户总金币
	 */
	public HttSysExpenseReportInfoVo selectAllGold();
	
	/**
	 * 用户金币余额
	 */
	public HttSysExpenseReportInfoVo selectUserGoldOfDay(Map<String, Object> params);

	/**
	 * 当日新增用户数
	 */
	public HttSysExpenseReportInfoVo selectNewUserOfDay(Map<String, Object> params);
	
	/**
	 * 全部邀请奖励
	 */
	public HttSysExpenseReportInfoVo selectAllInviteAwardOfDay(Map<String, Object> params);
	
	/**
	 * 全部阅读奖励
	 */
	public HttSysExpenseReportInfoVo selectAllReadAwardOfDay(Map<String, Object> params);
	
	/**
	 * 全部其他奖励
	 */
	public HttSysExpenseReportInfoVo selectAllOtherAwardOfDay(Map<String, Object> params);
	
	/**
	 * 申请提现金额
	 */
	public HttSysExpenseReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params);
	
	/**
	 * 成功打款金额
	 */
	public HttSysExpenseReportInfoVo selectSuccessWithdrawOfDay(Map<String, Object> params);
	
	/**
	 * 预计总支出金额
	 */
	public HttSysExpenseReportInfoVo selectAllUserWithdraw(Map<String, Object> params);
	
	/**
	 * 实际已支出总金额
	 */
	public HttSysExpenseReportInfoVo selectAllSuccessWithdraw(Map<String, Object> params);
	
}
