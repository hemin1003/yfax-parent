package com.yfax.webapi.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.DomainConfigDao;
import com.yfax.webapi.htt.vo.DomainConfigVo;

/**
 * 域名配置
 * @author Minbo
 */
@Service
public class DomainConfigService{
	
	protected static Logger logger = LoggerFactory.getLogger(DomainConfigService.class);
	
	@Autowired
	private DomainConfigDao dao;
	
	public DomainConfigVo selectDomainConfig(Integer flag) {
		return this.dao.selectDomainConfig(flag);
	}
}
