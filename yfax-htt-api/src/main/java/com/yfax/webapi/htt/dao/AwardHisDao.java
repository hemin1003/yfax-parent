package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.AwardHisVo;

public interface AwardHisDao {
	public boolean insertAwardHis(AwardHisVo awardHisVo) throws Exception;
	public List<AwardHisVo> selectAwardHisByPhoneNum(String phoneNum);
	public AwardHisVo selectAwardHisIsCheckIn(Map<String, Object> map);
	public Long selectUserTotalOfGold(Map<String, Object> map);
	public Long selectUserAwardTotal(Map<String, Object> map);
	public List<AwardHisVo> selectAwardHisCheckList(String phoneNum);
	public boolean updateUserAwardTotalFlag(AwardHisVo awardHisVo) throws Exception;
	public Long selectCountByNewUserTask(Map<String, Object> map);
	public Long selectCountByDailyTask(Map<String, Object> map);
	public AwardHisVo selectLastestAwardInfo(Map<String, Object> map);
	public List<AwardHisVo> selectStudents(String phoneNum);
	public List<AwardHisVo> selectStudentsDetail(String phoneNum);
	public Long selectTimeTaskCountOfDay(Map<String, Object> map);
	public AwardHisVo selectLastestTimeTaskOfDay(Map<String, Object> map);
	
	//寒假活动-查询累计三天内用户邀请有效徒弟个数(返回有效徒弟贡献次数的数组)
	public String holidayActivitySelectRealInviteUser(Map<String, Object> map);
	//寒假活动-查询某用户当天已抽奖的次数
	public String holidayActivitySelectLuckyDrawCount(Map<String, Object> map);
}
