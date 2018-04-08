package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.HolidayActivityRankingDao;
import com.yfax.webapi.htt.vo.HolidayActivityRankingVo;


@Component
public class HolidayActivityRankingDaoImpl implements HolidayActivityRankingDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public List<HolidayActivityRankingVo> selectRanking(String date) {
		return this.sqlSessionTemplate.selectList("selectRanking",date);
	}
	
	@Override
	public HolidayActivityRankingVo selectRankingByPhoneNum(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectRankingByPhoneNum",map);
	}

	@Override
	public HolidayActivityRankingVo selectLastRanking(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectLastRanking", map);
	}

}
