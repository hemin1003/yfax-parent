package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.DomainConfigVo;

public interface DomainConfigDao {
	public DomainConfigVo selectDomainConfig(Integer flag);
}
