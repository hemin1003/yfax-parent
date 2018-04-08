package com.yfax.task.htt.dao;

import java.util.Map;

import com.yfax.task.htt.vo.HttSysUserReportInfoVo;

public interface HttSysUserReportInfoDao {
	
	/**
	 * 用户总数
	 */
	public HttSysUserReportInfoVo selectAllUser();
	
	/**
	 * 当日新增用户数
	 */
	public HttSysUserReportInfoVo selectNewUserOfDay(Map<String, Object> params);
	
	/**
	 * 自邀请新增（有邀请码的新增注册）
	 */
	public HttSysUserReportInfoVo selectInviteUserOfDay(Map<String, Object> params);

	/**
	 * 自邀请比例
	 */
//	public HttSysUserReportInfoVo selectInvitePercentOfDay(Map<String, Object> params);
	
	/**
	 * 阅读文章数
	 */
	public HttSysUserReportInfoVo selectArticleCountOfDay(Map<String, Object> params);
	
	/**
	 * 文章阅读次数
	 */
	public HttSysUserReportInfoVo selectReadCountOfDay(Map<String, Object> params);
	
}
