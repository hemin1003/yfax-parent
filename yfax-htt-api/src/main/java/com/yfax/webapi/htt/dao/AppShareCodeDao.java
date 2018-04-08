package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.AppShareCodeVo;

public interface AppShareCodeDao {
	public boolean insertAppShareCode(AppShareCodeVo appShareCodeVo) throws Exception;
	public AppShareCodeVo selectAppShareCodeByPhoneNum(String phoneNum);
	public AppShareCodeVo selectAppShareCodeByShareCode(String shareCode);
}
