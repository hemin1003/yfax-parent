package com.yfax.task.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.task.htt.vo.HttHolidayActivityInviteUserRankingVo;

public interface HttHolidayActivityInviteUserRankingDao {
	
	public List<HttHolidayActivityInviteUserRankingVo> selectRanking(Map<String, Object> map);
	
	public boolean batchInsertRanking(List<HttHolidayActivityInviteUserRankingVo> httHolidayActivityInviteUserRankingVo);
	
}
