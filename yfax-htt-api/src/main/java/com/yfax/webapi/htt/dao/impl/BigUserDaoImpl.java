package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.BigUserDao;
import com.yfax.webapi.htt.vo.BigUserVo;

@Component
public class BigUserDaoImpl implements BigUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public BigUserVo selectBigUserByCode(String code) {
		return this.sqlSessionTemplate.selectOne("selectBigUserByCode", code);
	}

}
