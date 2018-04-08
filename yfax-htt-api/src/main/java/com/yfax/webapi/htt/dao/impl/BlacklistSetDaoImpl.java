package com.yfax.webapi.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.BlacklistSetDao;
import com.yfax.webapi.htt.vo.BlacklistSetVo;


@Component
public class BlacklistSetDaoImpl implements BlacklistSetDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public List<BlacklistSetVo> selectBlacklistSet() {
		return this.sqlSessionTemplate.selectList("selectBlacklistSet");
	}

}
