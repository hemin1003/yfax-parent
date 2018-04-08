package com.yfax.task.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttHolidayActivityInviteUserRankingDao;
import com.yfax.task.htt.vo.HttHolidayActivityInviteUserRankingVo;

@Component
public class HttHolidayActivityInviteUserRankingDaoImpl implements HttHolidayActivityInviteUserRankingDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public List<HttHolidayActivityInviteUserRankingVo> selectRanking(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("selectRanking",map);
	}
	
	@Override
	public boolean batchInsertRanking(List<HttHolidayActivityInviteUserRankingVo> httHolidayActivityInviteUserRankingVo) {
		int i = this.sqlSessionTemplate.insert("batchInsertRanking", httHolidayActivityInviteUserRankingVo);
		return i > 0 ? true : false;
	}

}
