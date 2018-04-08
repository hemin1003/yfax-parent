package com.yfax.spider.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.spider.dao.SpiderConfigDao;
import com.yfax.spider.vo.SpiderConfigVo;

@Component
public class SpiderConfigDaoImpl implements SpiderConfigDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public SpiderConfigVo selectSpiderConfig(String tag) {
		return this.sqlSessionTemplate.selectOne("selectSpiderConfig", tag);
	}
}
