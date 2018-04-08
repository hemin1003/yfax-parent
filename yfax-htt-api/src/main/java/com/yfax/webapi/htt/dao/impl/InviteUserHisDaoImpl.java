package com.yfax.webapi.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.InviteUserHisDao;
import com.yfax.webapi.htt.vo.InviteUserHisVo;


@Component
public class InviteUserHisDaoImpl implements InviteUserHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertInviteUserHis(InviteUserHisVo inviteUserHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertInviteUserHis", inviteUserHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public Long selectCountByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectCountByPhoneNum", phoneNum);
	}

	@Override
	public InviteUserHisVo selectInviteUserByStudentPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectInviteUserByStudentPhoneNum", phoneNum);
	}

	@Override
	public InviteUserHisVo selectInviteUserByMasAndStuPhoneNum(Map<String, String> map) {
		return this.sqlSessionTemplate.selectOne("selectInviteUserByMasAndStuPhoneNum", map);
	}

	@Override
	@Transactional
	public boolean updateInviteUserHis(InviteUserHisVo inviteUserHisVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateInviteUserHis", inviteUserHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public Integer holidayActivitySelectInviteUserCount(Map<String, String> map) {
		return this.sqlSessionTemplate.selectOne("holidayActivitySelectInviteUserCount", map);
	}
	
}
