package com.yfax.task.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttSysExpenseReportInfoDao;
import com.yfax.task.htt.vo.HttSysExpenseReportInfoVo;

@Component
public class HttSysExpenseReportInfoDaoImpl implements HttSysExpenseReportInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	
	@Override
	public HttSysExpenseReportInfoVo selectAllGold() {
		return this.sqlSessionTemplate.selectOne("selectAllGold");
	}
	
	@Override
	public HttSysExpenseReportInfoVo selectUserGoldOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectUserGoldOfDay", params);
	}
	
	@Override
	public HttSysExpenseReportInfoVo selectNewUserOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectNewUserOfDay", params);
	}
	
	@Override
	public HttSysExpenseReportInfoVo selectAllInviteAwardOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectAllInviteAwardOfDay", params);
	}
	
	@Override
	public HttSysExpenseReportInfoVo selectAllReadAwardOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectAllReadAwardOfDay", params);
	}
	
	@Override
	public HttSysExpenseReportInfoVo selectAllOtherAwardOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectAllOtherAwardOfDay", params);
	}

	@Override
	public HttSysExpenseReportInfoVo selectUserWithdrawOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectUserWithdrawOfDay", params);
	}
	
	@Override
	public HttSysExpenseReportInfoVo selectSuccessWithdrawOfDay(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectSuccessWithdrawOfDay", params);
	}

	@Override
	public HttSysExpenseReportInfoVo selectAllUserWithdraw(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectAllUserWithdraw", params);
	}

	@Override
	public HttSysExpenseReportInfoVo selectAllSuccessWithdraw(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectAllSuccessWithdraw", params);
	}
}