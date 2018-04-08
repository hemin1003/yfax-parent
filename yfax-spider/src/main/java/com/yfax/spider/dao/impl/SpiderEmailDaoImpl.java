package com.yfax.spider.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.spider.dao.SpiderEmailDao;
import com.yfax.spider.vo.SpiderEmailVo;

@Component
public class SpiderEmailDaoImpl implements SpiderEmailDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;


	@Override
	public List<SpiderEmailVo> selectSpiderEmail(SpiderEmailVo spiderEmailVo) {
		return this.sqlSessionTemplate.selectList("selectSpiderEmail", spiderEmailVo);
	}
}
