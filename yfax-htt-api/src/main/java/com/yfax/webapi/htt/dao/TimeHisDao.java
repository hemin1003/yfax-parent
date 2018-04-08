package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.TimeHisVo;

public interface TimeHisDao {
	public TimeHisVo selectTimeHisDate(String phoneNum);
	public boolean insertTimeHis(TimeHisVo timeHis) throws Exception;
	public boolean updateTimeHis(TimeHisVo timeHis) throws Exception;
}
