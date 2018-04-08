package com.yfax.webapi.htt.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.utils.MD5Util;
import com.yfax.utils.NetworkUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.htt.dao.ArticleHisDao;
import com.yfax.webapi.htt.vo.ArticleHisDetailVo;
import com.yfax.webapi.htt.vo.ArticleHisVo;
import com.yfax.webapi.htt.vo.InitConfigVo;

/**
 * 用户分享文章奖励历史
 * 
 * @author Minbo
 */
@Service
public class ArticleHisService {

	protected static Logger logger = LoggerFactory.getLogger(ArticleHisService.class);

	@Autowired
	private ArticleHisDao ArticleHisDao;
	@Autowired
	private AwardHisService awardHisService;
	@Autowired
	private ArticleHisDetailService articleHisDetailService;
	@Autowired
	private InitConfigService initConfigService;

	public ArticleHisVo getArticleHisByPhoneNumAndArticleUrlMd(Map<String, Object> map) {
		return this.ArticleHisDao.selectArticleHisByPhoneNumAndArticleUrlMd(map);
	}

	public boolean addArticleHis(ArticleHisVo articleHisVo) {
		try {
			return this.ArticleHisDao.insertArticleHis(articleHisVo);
		} catch (Exception e) {
			logger.error("新增用户分享文章奖励历史异常：" + e.getMessage(), e);
			return false;
		}
	}

	public boolean modifyArticleHis(ArticleHisVo articleHisVo) {
		try {
			return this.ArticleHisDao.updateArticleHis(articleHisVo);
		} catch (Exception e) {
			logger.error("修改用户分享文章奖励历史异常：" + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 处理文章是否需要奖励，以及详情信息记录
	 */
	public boolean processArticle(String articleUrl, String phoneNum, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("phoneNum", phoneNum);
		String articleUrlMd = MD5Util.encodeByMD5(articleUrl);
		map.put("articleUrlMd", articleUrlMd);
		String cTime = DateUtil.getCurrentLongDateTime();
		// 初始配置数据
		InitConfigVo initConfigVo = this.initConfigService.selectInitConfig();
		String ip = NetworkUtil.getIpAddress(request);
		// 是否已存在历史记录
		ArticleHisVo articleHisVo = this.getArticleHisByPhoneNumAndArticleUrlMd(map);
		if (articleHisVo == null) {
			// 记录文章已获取金币标识
			articleHisVo = new ArticleHisVo();
			articleHisVo.setId(UUID.getUUID());
			articleHisVo.setPhoneNum(phoneNum);
			articleHisVo.setArticleUrl(articleUrl);
			articleHisVo.setArticleUrlMd(articleUrlMd);
			articleHisVo.setIsAward(1); // 已奖励标识
			articleHisVo.setAwardCount(1); // 奖励次数
			articleHisVo.setCreateDate(cTime);
			articleHisVo.setUpdateDate(cTime);
			boolean flag = this.addArticleHis(articleHisVo);
			if (!flag) {
				logger.error("phoneNum=" + phoneNum + "，处理文章是否需要奖励-新增失败", new RuntimeException("phoneNum=" + phoneNum));
				return false;
			}
		} else {
			if (articleHisVo.getAwardCount() >= initConfigVo.getArticleAwardLimit()) {
				logger.warn(
						"phoneNum=" + phoneNum + "，分享的文章已奖励limit=" + initConfigVo.getArticleAwardLimit() + "次了，跳过处理。");
				return false;
			}
			ArticleHisDetailVo articleHisDetailVo = this.articleHisDetailService
					.getArticleHisDetailByPidAndIp(articleHisVo.getId(), ip);
			if (articleHisDetailVo != null) {
				logger.warn("phoneNum=" + phoneNum + "，ip=" + ip + "，该ip已给分享文章奖励了，跳过处理。");
				return false;
			}
			articleHisVo.setIsAward(1); // 已奖励标识
			articleHisVo.setAwardCount(articleHisVo.getAwardCount() + 1); // 奖励次数加1
			articleHisVo.setUpdateDate(cTime);
			boolean flag = this.modifyArticleHis(articleHisVo);
			if (!flag) {
				logger.error("phoneNum=" + phoneNum + "，处理文章是否需要奖励-更新失败", new RuntimeException("phoneNum=" + phoneNum));
				return false;
			}
		}
		// 记录ip奖励详情
		this.articleHisDetailService.addArticleHisDetail(articleHisVo.getId(), ip);
		// 发放奖励
		this.awardHisService.addAwardHis(phoneNum, Integer.valueOf(initConfigVo.getArticleAwardGold()),
				GlobalUtils.AWARD_TYPE_ARTICLE, null, null, null, null, null, null, null, null, null, null, null);
		return true;
	}
}