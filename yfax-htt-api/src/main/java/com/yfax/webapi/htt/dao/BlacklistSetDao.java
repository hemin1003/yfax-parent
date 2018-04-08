package com.yfax.webapi.htt.dao;

import java.util.List;

import com.yfax.webapi.htt.vo.BlacklistSetVo;

public interface BlacklistSetDao {
	public List<BlacklistSetVo> selectBlacklistSet();
}
