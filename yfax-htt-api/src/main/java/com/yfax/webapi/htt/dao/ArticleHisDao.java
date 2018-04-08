package com.yfax.webapi.htt.dao;

import java.util.Map;

import com.yfax.webapi.htt.vo.ArticleHisVo;

public interface ArticleHisDao {
	public ArticleHisVo selectArticleHisByPhoneNumAndArticleUrlMd(Map<String, Object> map);

	public boolean insertArticleHis(ArticleHisVo articleHisVo) throws Exception;

	public boolean updateArticleHis(ArticleHisVo articleHisVo) throws Exception;
}
