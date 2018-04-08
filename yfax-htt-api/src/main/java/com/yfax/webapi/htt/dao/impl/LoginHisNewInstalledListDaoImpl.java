package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.LoginHisNewInstalledListDao;
import com.yfax.webapi.htt.vo.LoginHisNewInstalledListVo;

@Component
public class LoginHisNewInstalledListDaoImpl implements LoginHisNewInstalledListDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	@Transactional
	public boolean insertLoginHisNewInstalledList(LoginHisNewInstalledListVo loginHisNewInstalledListVo)
			throws Exception {
		int i = this.sqlSessionTemplate.insert("insertLoginHisNewInstalledList", loginHisNewInstalledListVo);
		return i > 0 ? true : false;
	}

}
