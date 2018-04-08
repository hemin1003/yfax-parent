package com.yfax.spider.dao;

import com.yfax.spider.vo.SpiderInfoVo;

public interface SpiderListDao {
	public SpiderInfoVo selectSpiderInfo(SpiderInfoVo spiderInfoVo);
	public SpiderInfoVo selectLastestSpiderInfo(String date);
	public boolean insertSpiderInfo(SpiderInfoVo spiderInfoVo) throws Exception;
	public boolean updateSpiderInfo(SpiderInfoVo spiderInfoVo) throws Exception;
	public long selectDaysOfNewsNum(SpiderInfoVo spiderInfoVo);
	
}
