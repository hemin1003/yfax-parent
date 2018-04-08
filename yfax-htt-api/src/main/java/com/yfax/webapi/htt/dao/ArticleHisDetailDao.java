package com.yfax.webapi.htt.dao;

import java.util.Map;

import com.yfax.webapi.htt.vo.ArticleHisDetailVo;

public interface ArticleHisDetailDao {

	public boolean insertArticleHisDetail(ArticleHisDetailVo articleHisDetailVo) throws Exception;

	public ArticleHisDetailVo selectArticleHisDetailByPidAndIp(Map<String, Object> map);
}
