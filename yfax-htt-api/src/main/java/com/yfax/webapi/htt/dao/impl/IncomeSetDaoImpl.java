package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.IncomeSetDao;
import com.yfax.webapi.htt.vo.IncomeSetVo;


@Component
public class IncomeSetDaoImpl implements IncomeSetDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public List<IncomeSetVo> selectIncomeSet(String goodType) {
		return this.sqlSessionTemplate.selectList("selectIncomeSet", goodType);
	}

	@Override
	public IncomeSetVo selectIncomeSetByTypeAndIncome(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectIncomeSetByTypeAndIncome", map);
	}

}
