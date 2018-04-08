package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.AppUserMultiVo;

public interface AppUserMultiDao {
	public boolean insertMultiUser(AppUserMultiVo appUserMultiVo);
	public boolean updateMultiUser(AppUserMultiVo appUserMultiVo);
	public AppUserMultiVo selectByWechatOpenId(String wechatOpenId);
	public AppUserMultiVo selectByPhoneNum(String phoneNum);
	public AppUserMultiVo selectBySystemUserId(String systemUserId);
}
