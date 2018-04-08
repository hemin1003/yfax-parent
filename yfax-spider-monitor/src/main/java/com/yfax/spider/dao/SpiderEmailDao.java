package com.yfax.spider.dao;

import java.util.List;

import com.yfax.spider.vo.SpiderEmailVo;

public interface SpiderEmailDao {
	public List<SpiderEmailVo> selectSpiderEmail(SpiderEmailVo spiderEmailVo);
}
