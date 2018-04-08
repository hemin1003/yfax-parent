package com.yfax.webapi.htt.dao;

import java.util.Map;

import com.yfax.webapi.htt.vo.InviteUserHisVo;

public interface InviteUserHisDao {
	public Long selectCountByPhoneNum(String phoneNum);
	public boolean insertInviteUserHis(InviteUserHisVo inviteUserHisVo) throws Exception;
	public InviteUserHisVo selectInviteUserByStudentPhoneNum(String phoneNum);
	public InviteUserHisVo selectInviteUserByMasAndStuPhoneNum(Map<String, String> map);
	public boolean updateInviteUserHis(InviteUserHisVo inviteUserHisVo) throws Exception;
	
	//寒假活动-用户邀请徒弟记录查询
	public Integer holidayActivitySelectInviteUserCount(Map<String, String> map);
}
