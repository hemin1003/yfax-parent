package com.yfax.task.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yfax.task.htt.dao.HttChannelQualityInfoDao;
import com.yfax.task.htt.vo.HttChannelQualityInfoVo;

@Component
public class HttChannelQualityInfoDaoImpl implements HttChannelQualityInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public List<HttChannelQualityInfoVo> selectChannelQualityInfoByDate(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList("selectChannelQualityInfoByDate", params);
	}

	@Override
	public boolean batchInsertChannelQualityInfo(List<HttChannelQualityInfoVo> list) {
		int i = this.sqlSessionTemplate.insert("batchInsertChannelQualityInfo", list);
		return i > 0 ? true : false;
	}

}
