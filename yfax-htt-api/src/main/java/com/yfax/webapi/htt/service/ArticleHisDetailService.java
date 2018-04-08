package com.yfax.webapi.htt.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.htt.dao.ArticleHisDetailDao;
import com.yfax.webapi.htt.vo.ArticleHisDetailVo;

/**
 * 用户分享文章奖励历史详情
 * @author Minbo
 */
@Service
public class ArticleHisDetailService {

	protected static Logger logger = LoggerFactory.getLogger(ArticleHisDetailService.class);

	@Autowired
	private ArticleHisDetailDao articleHisDetailDao;

	public boolean addArticleHisDetail(String pid, String ip) {
		try {
			String cTime = DateUtil.getCurrentLongDateTime();
			ArticleHisDetailVo articleHisDetailVo = new ArticleHisDetailVo();
			articleHisDetailVo.setId(UUID.getUUID());
			articleHisDetailVo.setPid(pid);
			articleHisDetailVo.setIp(ip);
			articleHisDetailVo.setCreateDate(cTime);
			articleHisDetailVo.setUpdateDate(cTime);
			return this.articleHisDetailDao.insertArticleHisDetail(articleHisDetailVo);
		} catch (Exception e) {
			logger.error("新增用户分享文章奖励历史详情异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public ArticleHisDetailVo getArticleHisDetailByPidAndIp(String pid, String ip) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("ip", ip);
		return this.articleHisDetailDao.selectArticleHisDetailByPidAndIp(map);
	}

}