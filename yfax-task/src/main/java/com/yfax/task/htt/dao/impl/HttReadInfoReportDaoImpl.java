package com.yfax.task.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttReadInfoReportDao;
import com.yfax.task.htt.vo.HttReadInfoReportVo;

@Component
public class HttReadInfoReportDaoImpl implements HttReadInfoReportDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public HttReadInfoReportVo selectReadInfoReportByDate(String day) {
		return this.sqlSessionTemplate.selectOne("selectReadInfoReportByDate", day);
	}

	@Override
	public Boolean insertReadInfoReport(HttReadInfoReportVo httReadInfoReportVo) {
		int i = this.sqlSessionTemplate.insert("insertReadInfoReport", httReadInfoReportVo);
		return i > 0 ? true : false;
	}

}
