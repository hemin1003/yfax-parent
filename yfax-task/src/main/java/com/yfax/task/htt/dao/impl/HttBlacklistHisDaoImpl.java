package com.yfax.task.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.task.htt.dao.HttBlacklistHisDao;
import com.yfax.task.htt.vo.HttBlacklistHisVo;


@Component
public class HttBlacklistHisDaoImpl implements HttBlacklistHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertBlacklistHis(HttBlacklistHisVo blacklistHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertHttBlacklistHis", blacklistHis);
		return i > 0 ? true : false;
	}

	@Override
	public boolean batchInsertBlacklistHis(List<HttBlacklistHisVo> list) {
		int i = this.sqlSessionTemplate.insert("batchInsertBlacklistHis", list);
		return i > 0 ? true : false;
	}
}
