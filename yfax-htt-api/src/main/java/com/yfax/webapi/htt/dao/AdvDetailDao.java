package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.AdvDetailVo;

public interface AdvDetailDao {
	
	public List<AdvDetailVo> selectAdvDetail(Map<String, Object> map);
	
}
