package com.yfax.task.htt.dao;

import com.yfax.task.htt.vo.HttVideoInfoReportVo;

public interface HttVideoInfoReportDao {

	public HttVideoInfoReportVo selectVideoInfoReportByDate(String day);

	public Boolean insertVideoInfoReport(HttVideoInfoReportVo httVideoInfoReportVo);

}
