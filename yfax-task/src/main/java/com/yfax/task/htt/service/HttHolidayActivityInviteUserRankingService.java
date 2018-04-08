package com.yfax.task.htt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttHolidayActivityInviteUserRankingDao;
import com.yfax.task.htt.vo.HttHolidayActivityInviteUserRankingVo;
import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;

/**
 * 寒假活动-有效用户邀请排名
 * 
 * @author Minbo
 *
 */
@Service
public class HttHolidayActivityInviteUserRankingService {

	protected static Logger logger = LoggerFactory.getLogger(HttHolidayActivityInviteUserRankingService.class);

	@Autowired
	private HttHolidayActivityInviteUserRankingDao dao;

	/**
	 * 批量插入用户邀请有效徒弟记录
	 * 
	 * @return
	 */
	public boolean batchInsertRanking() {
		// 活动起始时间
		String startTime = "2018-01-19 18:00:00";
		String endTime = "2018-02-02 18:00:00" ;
		Map<String, Object> rankingMap = new HashMap<String, Object>();
		rankingMap.put("startTime", startTime);
		rankingMap.put("endTime", endTime);
		List<HttHolidayActivityInviteUserRankingVo> rankingList = this.dao.selectRanking(rankingMap);
		// 保证排行榜至少有4位用户
		if (rankingList.size() >= 4) {
			return this.dao.batchInsertRanking(rankingList);
		} else {
			logger.info("无排名记录");
			return false;
		}
	}
	
}
