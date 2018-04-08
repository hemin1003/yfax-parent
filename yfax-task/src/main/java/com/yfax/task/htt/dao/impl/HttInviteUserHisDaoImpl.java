package com.yfax.task.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttInviteUserHisDao;
import com.yfax.task.htt.vo.HttInviteUserHisVo;


@Component
public class HttInviteUserHisDaoImpl implements HttInviteUserHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public boolean insertInviteUserHis(HttInviteUserHisVo inviteUserHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertInviteUserHis", inviteUserHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public Long selectCountByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectCountByPhoneNum", phoneNum);
	}

	@Override
	public HttInviteUserHisVo selectInviteUserByStudentPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectInviteUserByStudentPhoneNum", phoneNum);
	}

	@Override
	public HttInviteUserHisVo selectInviteUserByMasAndStuPhoneNum(Map<String, String> map) {
		return this.sqlSessionTemplate.selectOne("selectInviteUserByMasAndStuPhoneNum", map);
	}

	@Override
	public boolean updateInviteUserHis(HttInviteUserHisVo inviteUserHisVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateInviteUserHis", inviteUserHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public List<HttInviteUserHisVo> selectCalGoldOfInviteUser(String masterPhoneNum) {
		return this.sqlSessionTemplate.selectList("selectCalGoldOfInviteUser", masterPhoneNum);
	}

	@Override
	public List<HttInviteUserHisVo> selectInviteUserByMasterPhoneNum(Map<String, String> map) {
		return this.sqlSessionTemplate.selectList("selectInviteUserByMasterPhoneNum", map);
	}
	
}
