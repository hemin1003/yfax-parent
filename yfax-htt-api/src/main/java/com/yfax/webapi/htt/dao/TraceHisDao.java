package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.TraceHisVo;

public interface TraceHisDao {
	public boolean insertTraceHis(TraceHisVo traceHis) throws Exception;

	public boolean updateTraceHis(TraceHisVo traceHis) throws Exception;
	
	public TraceHisVo selectTraceHisByUid(String uId);
}
