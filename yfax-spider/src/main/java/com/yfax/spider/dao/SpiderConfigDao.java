package com.yfax.spider.dao;

import com.yfax.spider.vo.SpiderConfigVo;

public interface SpiderConfigDao {
	public SpiderConfigVo selectSpiderConfig(String tag);
}
