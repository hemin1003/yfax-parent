package com.yfax.spider.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.spider.dao.SpiderListDao;
import com.yfax.spider.vo.SpiderInfoVo;

@Component
public class SpiderListDaoImpl implements SpiderListDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public boolean insertSpiderInfo(SpiderInfoVo spiderInfoVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertSpiderInfo", spiderInfoVo);
		return i > 0 ? true : false;
	}

	@Override
	public SpiderInfoVo selectSpiderInfo(SpiderInfoVo spiderInfoVo) {
		return this.sqlSessionTemplate.selectOne("selectSpiderInfo", spiderInfoVo);
	}

	@Override
	public boolean updateSpiderInfo(SpiderInfoVo spiderInfoVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateSpiderInfo", spiderInfoVo);
		return i > 0 ? true : false;
	}

	@Override
	public long selectDaysOfNewsNum(SpiderInfoVo spiderInfoVo) {
		return this.sqlSessionTemplate.selectOne("selectDaysOfNewsNum", spiderInfoVo);
	}

}
