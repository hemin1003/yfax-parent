package com.yfax.webapi.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.LoginHisNewDao;
import com.yfax.webapi.htt.vo.LoginHisNewVo;


@Component
public class LoginHisNewDaoImpl implements LoginHisNewDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertLoginHisNew(LoginHisNewVo loginHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertLoginHisNew", loginHis);
		return i > 0 ? true : false;
	}

	@Override
	public LoginHisNewVo selectLoginHisNewDate(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectLoginHisNewDate", phoneNum);
	}

	@Override
	public boolean updateLoginHisNew(LoginHisNewVo loginHis) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateLoginHisNew", loginHis);
		return i > 0 ? true : false;
	}

	@Override
	public Long selectCountOfLoginHisNewImei(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectCountOfLoginHisNewImei", params);
	}

	@Override
	public Long selectCountOfLoginHisNewPhoneNum(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectCountOfLoginHisNewPhoneNum", params);
	}

}
