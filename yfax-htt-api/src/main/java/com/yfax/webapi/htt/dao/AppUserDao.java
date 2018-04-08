package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.AppUserVo;

public interface AppUserDao {
	public AppUserVo selectByPhoneNumAndPwd(Map<String, Object> params);
	public AppUserVo selectByPhoneNum(String phoneNum);
	public boolean deleteByTokenId(String phoneNum);
	public boolean insertUser(AppUserVo appUserVo);
	public boolean updateUser(AppUserVo appUserVo);
	public List<AppUserVo> selectByRank();
	public Long selectByRankSum();
	public Long selectByTodaySum(Map<String, Object> params);
	public List<AppUserVo> selectByRankGold();
	public Long selectIsInviteCodeExist(String inviteCode);
	public AppUserVo selectByInviteCode(String inviteCode);
	public AppUserVo selectLoginByPhoneNum(String phoneNum);
	public AppUserVo selectByRealPhoneNum(String phoneNum);
	public Long selectByRestGold(String phoneNum);
	public Long selectAppUserOfMeiCount(String imei);
	public Long selectAppUserOfRegisterIp(Map<String, Object> params);
	public boolean updateUserLastLoginDate(AppUserVo appUserVo);
}
