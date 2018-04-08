package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.InviteUserAwardDao;
import com.yfax.webapi.htt.vo.InviteUserAwardVo;


@Component
public class InviteUserAwardDaoImpl implements InviteUserAwardDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertInviteUserAward(InviteUserAwardVo inviteUserAwardVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertInviteUserAward", inviteUserAwardVo);
		return i > 0 ? true : false;
	}

	@Override
	public List<InviteUserAwardVo> selectInviteUserAward(InviteUserAwardVo inviteUserAwardVo) {
		return this.sqlSessionTemplate.selectList("selectInviteUserAward", inviteUserAwardVo);
	}

	@Override
	public boolean updateInviteUserAward(InviteUserAwardVo inviteUserAwardVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateInviteUserAward", inviteUserAwardVo);
		return i > 0 ? true : false;
	}

	@Override
	public InviteUserAwardVo selectIsExistInviteUserAward(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectIsExistInviteUserAward", params);
	}

}
