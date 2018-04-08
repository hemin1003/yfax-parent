package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.TimeHisDao;
import com.yfax.webapi.htt.vo.TimeHisVo;


@Component
public class TimeHisDaoImpl implements TimeHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertTimeHis(TimeHisVo timeHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertTimeHis", timeHis);
		return i > 0 ? true : false;
	}

	@Override
	public TimeHisVo selectTimeHisDate(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectTimeHisDate", phoneNum);
	}

	@Override
	public boolean updateTimeHis(TimeHisVo timeHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateTimeHis", timeHis);
		return i > 0 ? true : false;
	}

}
