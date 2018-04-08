package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.AwardHisDao;
import com.yfax.webapi.htt.vo.AwardHisVo;

@Component
public class AwardHisDaoImpl implements AwardHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertAwardHis(AwardHisVo awardHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertAwardHis", awardHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public List<AwardHisVo> selectAwardHisByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectAwardHisByPhoneNum", phoneNum);
	}

	@Override
	public AwardHisVo selectAwardHisIsCheckIn(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectAwardHisIsFirstLogin", map);
	}

	@Override
	public Long selectUserTotalOfGold(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectUserTotalOfGold", map);
	}

	@Override
	public Long selectUserAwardTotal(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectUserAwardTotal", map);
	}

	@Override
	public List<AwardHisVo> selectAwardHisCheckList(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectAwardHisCheckList", phoneNum);
	}

	@Override
	@Transactional
	public boolean updateUserAwardTotalFlag(AwardHisVo awardHisVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateUserAwardTotalFlag", awardHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public Long selectCountByNewUserTask(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectCountByNewUserTask", map);
	}

	@Override
	public Long selectCountByDailyTask(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectCountByDailyTask", map);
	}

	@Override
	public AwardHisVo selectLastestAwardInfo(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectLastestAwardInfo", map);
	}

	@Override
	public List<AwardHisVo> selectStudents(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectStudents", phoneNum);
	}

	@Override
	public List<AwardHisVo> selectStudentsDetail(String phoneNum) {
		return this.sqlSessionTemplate.selectList("selectStudentsDetail", phoneNum);
	}

	@Override
	public Long selectTimeTaskCountOfDay(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectTimeTaskCountOfDay", map);
	}

	@Override
	public AwardHisVo selectLastestTimeTaskOfDay(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectLastestTimeTaskOfDay", map);
	}

	//寒假活动-查询累计活动时间内用户邀请有效徒弟个数(返回有效徒弟贡献次数的数组)
	@Override
	public String holidayActivitySelectRealInviteUser(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("holidayActivitySelectRealInviteUser", map);
	}

	//寒假活动-查询某用户当天已抽奖的次数
	@Override
	public String holidayActivitySelectLuckyDrawCount(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("holidayActivitySelectLuckyDrawCount", map);
	}

}
