package com.yfax.webapi.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.UserSmsDao;
import com.yfax.webapi.htt.vo.UserSmsVo;

@Component
public class UserSmsDaoImpl implements UserSmsDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	@Transactional
	public boolean insertUserSms(UserSmsVo userSms) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertUserSms", userSms);
		return i > 0 ? true : false;
	}

	@Override
	public UserSmsVo selectUserSms(Map<Object, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectUserSms", map);
	}

	@Override
	public Long selectUserSmsByPhoneNum(Map<Object, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectUserSmsByPhoneNum", map);
	}

	@Override
	public UserSmsVo selectLastestUserSms(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectLastestUserSms", phoneNum);
	}

	@Override
	public UserSmsVo selectUserSmsByMsgCode(String msgCode) {
		return this.sqlSessionTemplate.selectOne("selectUserSmsByMsgCode", msgCode);
	}

}
