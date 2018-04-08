package com.yfax.spider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.spider.dao.SpiderListDao;
import com.yfax.spider.vo.SpiderInfoVo;

/**
 * 统计记录抓取信息
 * @author Minbo
 *
 */
@Service
public class SpiderListService {
	
	protected static Logger logger = LoggerFactory.getLogger(SpiderListService.class);
	
	@Autowired
	private SpiderListDao dao;
	
	public boolean insertSpiderInfo(SpiderInfoVo spiderInfoVo) {
		try {
			return this.dao.insertSpiderInfo(spiderInfoVo);
		} catch (Exception e) {
			logger.error("统计记录抓取新增信息异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public SpiderInfoVo selectSpiderInfo(SpiderInfoVo spiderInfoVo) {
		return this.dao.selectSpiderInfo(spiderInfoVo);
	}
	
	public boolean updateSpiderInfo(SpiderInfoVo spiderInfoVo) throws Exception {
		try {
			return this.dao.updateSpiderInfo(spiderInfoVo);
		} catch (Exception e) {
			logger.error("统计记录抓取更新信息异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public long selectDaysOfNewsNum(SpiderInfoVo spiderInfoVo) {
		return this.dao.selectDaysOfNewsNum(spiderInfoVo);
	}
}
