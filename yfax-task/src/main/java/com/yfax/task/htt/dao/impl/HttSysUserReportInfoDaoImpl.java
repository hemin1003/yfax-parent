package com.yfax.task.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttSysUserReportInfoDao;
import com.yfax.task.htt.vo.HttSysUserReportInfoVo;

@Component
public class HttSysUserReportInfoDaoImpl implements HttSysUserReportInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public HttSysUserReportInfoVo selectAllUser() {
		return this.sqlSessionTemplate.selectOne("selectAllUserInfo");
	}

	@Override
	public HttSysUserReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectNewUserInfoOfDay", params);
	}

	@Override
	public HttSysUserReportInfoVo selectInviteUserOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectInviteUserOfDay", params);
	}

//	@Override
//	public HttSysUserReportInfoVo selectInvitePercentOfDay(Map<String, Object> params) {
//		return this.sqlSessionTemplate.selectOne("selectInvitePercentOfDay", params);
//	}

	@Override
	public HttSysUserReportInfoVo selectArticleCountOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectArticleCountOfDay", params);
	}

	@Override
	public HttSysUserReportInfoVo selectReadCountOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectReadCountOfDay", params);
	}

	
}