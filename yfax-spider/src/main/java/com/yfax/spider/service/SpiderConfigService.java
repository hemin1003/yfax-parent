package com.yfax.spider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.spider.dao.SpiderConfigDao;
import com.yfax.spider.vo.SpiderConfigVo;

/**
 * 信息配置
 * @author Minbo
 */
@Service
public class SpiderConfigService {
	
	protected static Logger logger = LoggerFactory.getLogger(SpiderConfigService.class);
	
	@Autowired
	private SpiderConfigDao dao;
	
	public SpiderConfigVo selectSpiderConfig(String tag) {
		return this.dao.selectSpiderConfig(tag);
	}
}
