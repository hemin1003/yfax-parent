package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.utils.StrUtil;
import com.yfax.webapi.htt.dao.AppUserDao;
import com.yfax.webapi.htt.vo.AppUserVo;

@Component
public class AppUserDaoImpl implements AppUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public AppUserVo selectByPhoneNumAndPwd(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectByPhoneNumAndPwd", params);
	}

	@Override
	public AppUserVo selectByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectByPhoneNum", phoneNum);
	}

	@Override
	public boolean deleteByTokenId(String phoneNum) {
		String token_id = this.sqlSessionTemplate.selectOne("selectTokenIdByPhoneNum", phoneNum);
		if(!StrUtil.null2Str(token_id).equals("")) {
			int i = this.sqlSessionTemplate.delete("deleteByTokenId", token_id);
			return i > 0 ? true : false;
		}else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean insertUser(AppUserVo appUserVo) {
		int i = this.sqlSessionTemplate.delete("insertUser", appUserVo);
		return i > 0 ? true : false;
	}

	@Override
	@Transactional
	public boolean updateUser(AppUserVo appUserVo) {
		int i = this.sqlSessionTemplate.delete("updateUser", appUserVo);
		return i > 0 ? true : false;
	}

	@Override
	public List<AppUserVo> selectByRank() {
		return this.sqlSessionTemplate.selectList("selectByRank");
	}
	
	@Override
	public Long selectByRankSum() {
		return this.sqlSessionTemplate.selectOne("selectByRankSum");
	}

	@Override
	public Long selectByTodaySum(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectByTodaySum", params);
	}

	@Override
	public List<AppUserVo> selectByRankGold() {
		return this.sqlSessionTemplate.selectList("selectByRankGold");
	}

	@Override
	public Long selectIsInviteCodeExist(String inviteCode) {
		return this.sqlSessionTemplate.selectOne("selectIsInviteCodeExist", inviteCode);
	}

	@Override
	public AppUserVo selectByInviteCode(String inviteCode) {
		return this.sqlSessionTemplate.selectOne("selectByInviteCode", inviteCode);
	}


	@Override
	public AppUserVo selectLoginByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectLoginByPhoneNum", phoneNum);
	}

	@Override
	public AppUserVo selectByRealPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectByRealPhoneNum", phoneNum);
	}

	@Override
	public Long selectByRestGold(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectByRestGold", phoneNum);
	}
	
	@Override
	public Long selectAppUserOfMeiCount(String imei) {
		return this.sqlSessionTemplate.selectOne("selectAppUserOfMeiCount", imei);
	}

	@Override
	public Long selectAppUserOfRegisterIp(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectAppUserOfRegisterIp", params);
	}

	@Override
	public boolean updateUserLastLoginDate(AppUserVo appUserVo) {
		int i = this.sqlSessionTemplate.update("updateUserLastLoginDate", appUserVo);
		return i > 0 ? true : false;
	}

}
