package com.yfax.task.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.task.htt.vo.HttDakaReportVo;


public interface HttDakaReportDao {
	
	public List<HttDakaReportVo> selectDakaReportByDate(Map<String, Object> params);
	
	public boolean batchInsertDakaReport(List<HttDakaReportVo> list);
	
}
