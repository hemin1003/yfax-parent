package com.yfax.task.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.task.htt.vo.HttInviteUserHisVo;

public interface HttInviteUserHisDao {
	public Long selectCountByPhoneNum(String phoneNum);
	public boolean insertInviteUserHis(HttInviteUserHisVo inviteUserHisVo) throws Exception;
	public HttInviteUserHisVo selectInviteUserByStudentPhoneNum(String phoneNum);
	public HttInviteUserHisVo selectInviteUserByMasAndStuPhoneNum(Map<String, String> map);
	public boolean updateInviteUserHis(HttInviteUserHisVo inviteUserHisVo) throws Exception;
	public List<HttInviteUserHisVo> selectCalGoldOfInviteUser(String masterPhoneNum);
	public List<HttInviteUserHisVo> selectInviteUserByMasterPhoneNum(Map<String, String> map);
}
