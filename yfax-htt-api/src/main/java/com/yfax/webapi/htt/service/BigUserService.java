package com.yfax.webapi.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.BigUserDao;
import com.yfax.webapi.htt.vo.BigUserVo;

/**
 * 大咖用户数据
 * @author Minbo
 */
@Service
public class BigUserService{
	
	protected static Logger logger = LoggerFactory.getLogger(BigUserService.class);
	
	@Autowired
	private BigUserDao dao;
	
	public BigUserVo getBigUserByCode(String code) {
		return this.dao.selectBigUserByCode(code);
	}

}
