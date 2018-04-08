package com.yfax.task.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttVideoInfoReportDao;
import com.yfax.task.htt.vo.HttVideoInfoReportVo;

@Component
public class HttVideoInfoReportDaoImpl implements HttVideoInfoReportDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public HttVideoInfoReportVo selectVideoInfoReportByDate(String day) {
		return this.sqlSessionTemplate.selectOne("selectVideoInfoReportByDate", day);
	}

	@Override
	public Boolean insertVideoInfoReport(HttVideoInfoReportVo httVideoInfoReportVo) {
		int i = this.sqlSessionTemplate.insert("insertVideoInfoReport",httVideoInfoReportVo) ;
		return i > 0 ? true :false;
	}

}
