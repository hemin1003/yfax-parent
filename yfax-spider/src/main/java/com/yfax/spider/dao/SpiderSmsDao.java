package com.yfax.spider.dao;

import java.util.List;

import com.yfax.spider.vo.SpiderSmsVo;

public interface SpiderSmsDao {
	public List<SpiderSmsVo> selectSpiderSms(SpiderSmsVo spiderSmsVo);
}
