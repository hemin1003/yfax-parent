package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.DomainConfigDao;
import com.yfax.webapi.htt.vo.DomainConfigVo;


@Component
public class DomainConfigDaoImpl implements DomainConfigDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public DomainConfigVo selectDomainConfig(Integer flag) {
		return this.sqlSessionTemplate.selectOne("selectDomainConfig", flag);
	}

}
