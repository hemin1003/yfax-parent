package com.yfax.task.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttDeleteBlacklistTokenDao;

@Component
public class HttDeleteBlacklistTokenDaoImpl implements HttDeleteBlacklistTokenDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public boolean deleteBlacklistToken() {
		int i = this.sqlSessionTemplate.delete("deleteBlacklistToken");
		return i > 0 ? true : false;
	}

	@Override
	public boolean deleteHttOauthRefreshToken() {
		int i = this.sqlSessionTemplate.delete("deleteHttOauthRefreshToken");
		return i > 0 ? true : false;
	};

}
