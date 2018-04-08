package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.IncomeSetVo;

public interface IncomeSetDao {
	public List<IncomeSetVo> selectIncomeSet(String goodType);
	public IncomeSetVo selectIncomeSetByTypeAndIncome(Map<String, Object> map);
}
