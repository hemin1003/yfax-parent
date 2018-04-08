package com.yfax.task.htt.dao;

import java.util.Map;

import com.yfax.task.htt.vo.HttExpenseInfoVo;

public interface HttExpenseInfoDao {

	public HttExpenseInfoVo selectHttExpenseInfo(Map<String, Object> params);
	public boolean insertHttExpenseInfo(HttExpenseInfoVo httExpenseInfoVo);
	public boolean updateHttExpenseInfo(HttExpenseInfoVo httExpenseInfoVo);

}
