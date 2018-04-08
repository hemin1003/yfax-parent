package com.yfax.spider.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.spider.dao.SpiderSmsDao;
import com.yfax.spider.vo.SpiderSmsVo;

/**
 * 短信配置
 * @author Minbo
 */
@Service
public class SpiderSmsService {
	
	protected static Logger logger = LoggerFactory.getLogger(SpiderSmsService.class);
	
	@Autowired
	private SpiderSmsDao dao;
	
	public List<SpiderSmsVo> selectSpiderSms() {
		return this.dao.selectSpiderSms(null);
	}
}
