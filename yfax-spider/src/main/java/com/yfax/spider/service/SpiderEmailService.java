package com.yfax.spider.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.spider.dao.SpiderEmailDao;
import com.yfax.spider.vo.SpiderEmailVo;

/**
 * 邮件配置
 * @author Minbo
 */
@Service
public class SpiderEmailService {
	
	protected static Logger logger = LoggerFactory.getLogger(SpiderEmailService.class);
	
	@Autowired
	private SpiderEmailDao dao;
	
	public List<SpiderEmailVo> selectSpiderEmail() {
		return this.dao.selectSpiderEmail(null);
	}
}
