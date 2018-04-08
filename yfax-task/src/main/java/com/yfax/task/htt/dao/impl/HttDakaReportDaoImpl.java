package com.yfax.task.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttDakaReportDao;
import com.yfax.task.htt.vo.HttDakaReportVo;

@Component
public class HttDakaReportDaoImpl implements HttDakaReportDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public List<HttDakaReportVo> selectDakaReportByDate(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectDakaReportByDate", params);
	}

	@Override
	public boolean batchInsertDakaReport(List<HttDakaReportVo> list) {
		int i = this.sqlSessionTemplate.insert("batchInsertDakaReport", list);
		return i > 0 ? true : false;
	}

}
