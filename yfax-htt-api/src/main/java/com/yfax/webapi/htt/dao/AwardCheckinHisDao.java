package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.AwardCheckinHisVo;

public interface AwardCheckinHisDao {
	public List<AwardCheckinHisVo> selectAwardCheckinHisList(String phoneNum);
	public boolean insertAwardCheckinHis(AwardCheckinHisVo awardCheckinHisVo) throws Exception;
	public Long selectAwardHisCheckInAward(Map<String, Object> map);
	public boolean updateUserAwardHisCheckInTotalFlag(AwardCheckinHisVo awardCheckinHisVo) throws Exception;
	public List<AwardCheckinHisVo> selectAllAwardCheckinHisList(String phoneNum);
}
