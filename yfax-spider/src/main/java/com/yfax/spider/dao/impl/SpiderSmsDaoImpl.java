package com.yfax.spider.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.spider.dao.SpiderSmsDao;
import com.yfax.spider.vo.SpiderSmsVo;

@Component
public class SpiderSmsDaoImpl implements SpiderSmsDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;


	@Override
	public List<SpiderSmsVo> selectSpiderSms(SpiderSmsVo spiderSmsVo) {
		return this.sqlSessionTemplate.selectList("selectSpiderSms", spiderSmsVo);
	}
}
