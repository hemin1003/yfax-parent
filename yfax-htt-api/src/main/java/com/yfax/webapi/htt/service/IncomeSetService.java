package com.yfax.webapi.htt.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.IncomeSetDao;
import com.yfax.webapi.htt.vo.IncomeSetVo;

/**
 * 提现配置数据
 * @author Minbo
 */
@Service
public class IncomeSetService{
	
	protected static Logger logger = LoggerFactory.getLogger(IncomeSetService.class);
	
	@Autowired
	private IncomeSetDao dao;
	
	public List<IncomeSetVo> getIncomeSet(String goodType) {
		return this.dao.selectIncomeSet(goodType);
	}
	
	public IncomeSetVo getIncomeSetByTypeAndIncome(Map<String, Object> map) {
		return this.dao.selectIncomeSetByTypeAndIncome(map);
	}

}
