package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.InviteUserAwardVo;

public interface InviteUserAwardDao {
	public InviteUserAwardVo selectIsExistInviteUserAward(Map<String, Object> params);
	public boolean insertInviteUserAward(InviteUserAwardVo inviteUserAwardVo) throws Exception;
	public boolean updateInviteUserAward(InviteUserAwardVo inviteUserAwardVo) throws Exception;
	public List<InviteUserAwardVo> selectInviteUserAward(InviteUserAwardVo inviteUserAwardVo);
}
