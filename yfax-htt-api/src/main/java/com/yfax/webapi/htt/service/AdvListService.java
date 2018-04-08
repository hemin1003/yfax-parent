package com.yfax.webapi.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.AdvListDao;
import com.yfax.webapi.htt.vo.AdvListVo;

/**
 * 广告来源配置
 * @author Minbo
 */
@Service
public class AdvListService{
	
	protected static Logger logger = LoggerFactory.getLogger(AdvListService.class);
	
	@Autowired
	private AdvListDao dao;
	
	public List<AdvListVo> selectAdvList() {
		return this.dao.selectAdvList();
	}

}
