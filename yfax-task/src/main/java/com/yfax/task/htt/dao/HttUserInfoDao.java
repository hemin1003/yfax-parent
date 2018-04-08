package com.yfax.task.htt.dao;

import java.util.Map;

import com.yfax.task.htt.vo.HttUserInfoVo;

public interface HttUserInfoDao {
	
	public HttUserInfoVo selectHttUserInfo(Map<String, Object> params);
	public boolean insertHttUserInfo(HttUserInfoVo httUserInfoVo);
	public boolean updateHttUserInfo(HttUserInfoVo httUserInfoVo);
	
}
