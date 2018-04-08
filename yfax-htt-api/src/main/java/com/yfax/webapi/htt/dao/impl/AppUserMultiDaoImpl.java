package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.AppUserMultiDao;
import com.yfax.webapi.htt.vo.AppUserMultiVo;

@Component
public class AppUserMultiDaoImpl implements AppUserMultiDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertMultiUser(AppUserMultiVo appUserMultiVo) {
		int i = this.sqlSessionTemplate.delete("insertMultiUser", appUserMultiVo);
		return i > 0 ? true : false;
	}

	@Override
	@Transactional
	public boolean updateMultiUser(AppUserMultiVo appUserMultiVo) {
		int i = this.sqlSessionTemplate.delete("updateMultiUser", appUserMultiVo);
		return i > 0 ? true : false;
	}

	@Override
	public AppUserMultiVo selectByWechatOpenId(String wechatOpenId) {
		return this.sqlSessionTemplate.selectOne("selectAppUserMultiByWechatOpenId", wechatOpenId);
	}

	@Override
	public AppUserMultiVo selectByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectAppUserMultiByPhoneNum", phoneNum);
	}

	@Override
	public AppUserMultiVo selectBySystemUserId(String systemUserId) {
		return this.sqlSessionTemplate.selectOne("selectAppUserMultiBySystemUserId", systemUserId);
	}

}
