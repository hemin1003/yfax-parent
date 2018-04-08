package com.yfax.webapi.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.ArticleHisDetailDao;
import com.yfax.webapi.htt.vo.ArticleHisDetailVo;

@Component
public class ArticleHisDetailDaoImpl implements ArticleHisDetailDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	@Transactional
	public boolean insertArticleHisDetail(ArticleHisDetailVo articleHisDetailVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertArticleHisDetail", articleHisDetailVo);
		return i > 0 ? true : false;
	}

	@Override
	public ArticleHisDetailVo selectArticleHisDetailByPidAndIp(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectArticleHisDetailByPidAndIp", map);
	}
}
