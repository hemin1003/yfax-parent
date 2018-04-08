package com.yfax.task.htt.dao;

import com.yfax.task.htt.vo.HttReadInfoReportVo;

public interface HttReadInfoReportDao {

	public HttReadInfoReportVo selectReadInfoReportByDate(String day);

	public Boolean insertReadInfoReport(HttReadInfoReportVo httReadInfoReportVo);

}
