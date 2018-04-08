package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.AwardCheckinHisDao;
import com.yfax.webapi.htt.vo.AwardCheckinHisVo;

@Component
public class AwardCheckinHisDaoImpl implements AwardCheckinHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<AwardCheckinHisVo> selectAllAwardCheckinHisList(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectAllAwardCheckinHisList", phoneNum);
	}
	
	@Override
	public List<AwardCheckinHisVo> selectAwardCheckinHisList(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectAwardCheckinHisList", phoneNum);
	}
	
	@Override
	@Transactional
	public boolean insertAwardCheckinHis(AwardCheckinHisVo awardCheckinHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertAwardCheckinHis", awardCheckinHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public Long selectAwardHisCheckInAward(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectAwardHisCheckInAward", map);
	}
	
	@Override
	public boolean updateUserAwardHisCheckInTotalFlag(AwardCheckinHisVo awardCheckinHisVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateUserAwardHisCheckInTotalFlag", awardCheckinHisVo);
		return i > 0 ? true : false;
	}
}