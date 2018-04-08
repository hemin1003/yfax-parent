package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.BigUserVo;

public interface BigUserDao {
	public BigUserVo selectBigUserByCode(String code);
}
