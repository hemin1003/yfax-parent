package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.RateSetDao;
import com.yfax.webapi.htt.vo.RateSetVo;


@Component
public class RateSetDaoImpl implements RateSetDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public RateSetVo selectRateSet() {
		return this.sqlSessionTemplate.selectOne("selectRateSet");
	}

}
