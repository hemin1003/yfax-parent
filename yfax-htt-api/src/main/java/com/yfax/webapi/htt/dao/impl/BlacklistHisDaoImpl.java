package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.BlacklistHisDao;
import com.yfax.webapi.htt.vo.BlacklistHisVo;


@Component
public class BlacklistHisDaoImpl implements BlacklistHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertBlacklistHis(BlacklistHisVo blacklistHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertBlacklistHis", blacklistHis);
		return i > 0 ? true : false;
	}

}
