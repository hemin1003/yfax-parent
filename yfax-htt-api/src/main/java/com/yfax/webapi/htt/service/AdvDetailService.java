package com.yfax.webapi.htt.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.AdvDetailDao;
import com.yfax.webapi.htt.vo.AdvDetailVo;

/**
 * 广告内容详情配置
 * @author Minbo
 */
@Service
public class AdvDetailService{
	
	protected static Logger logger = LoggerFactory.getLogger(AdvDetailService.class);
	
	@Autowired
	private AdvDetailDao dao;
	
	public List<AdvDetailVo> selectAdvDetail(Map<String, Object> map) {
		return this.dao.selectAdvDetail(map);
	}

}
