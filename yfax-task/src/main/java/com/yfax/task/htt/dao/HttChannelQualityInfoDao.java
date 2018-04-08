package com.yfax.task.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.task.htt.vo.HttChannelQualityInfoVo;


public interface HttChannelQualityInfoDao {
	
	public List<HttChannelQualityInfoVo> selectChannelQualityInfoByDate(Map<String, Object> params);
	
	public boolean batchInsertChannelQualityInfo(List<HttChannelQualityInfoVo> list);
	
}
