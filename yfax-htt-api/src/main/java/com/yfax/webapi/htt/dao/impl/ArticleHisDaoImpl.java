package com.yfax.webapi.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.ArticleHisDao;
import com.yfax.webapi.htt.vo.ArticleHisVo;

@Component
public class ArticleHisDaoImpl implements ArticleHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public ArticleHisVo selectArticleHisByPhoneNumAndArticleUrlMd(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectArticleHisByPhoneNumAndArticleUrlMd", map);
	}

	@Override
	@Transactional
	public boolean insertArticleHis(ArticleHisVo ArticleHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertArticleHis", ArticleHisVo);
		return i > 0 ? true : false;
	}

	@Override
	@Transactional
	public boolean updateArticleHis(ArticleHisVo ArticleHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateArticleHis", ArticleHisVo);
		return i > 0 ? true : false;
	}
}
