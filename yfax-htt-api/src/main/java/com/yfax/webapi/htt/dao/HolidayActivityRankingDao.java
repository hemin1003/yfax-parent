package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.HolidayActivityRankingVo;

public interface HolidayActivityRankingDao {

	public List<HolidayActivityRankingVo> selectRanking(String date);
	
	public HolidayActivityRankingVo selectRankingByPhoneNum(Map<String, Object> map);
	
	//查询排行榜上一名的有效徒弟数
	public HolidayActivityRankingVo selectLastRanking(Map<String, Object> map);

}
