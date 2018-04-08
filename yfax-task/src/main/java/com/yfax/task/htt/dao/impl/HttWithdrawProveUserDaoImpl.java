package com.yfax.task.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttWithdrawProveUserDao;
import com.yfax.task.htt.vo.HttLoginHisVo;
import com.yfax.task.htt.vo.HttWithdrawProveUserVo;

@Component
public class HttWithdrawProveUserDaoImpl implements HttWithdrawProveUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;


	@Override
	public boolean insertHttWithdrawProveUser(HttWithdrawProveUserVo httWithdrawProveUserVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertHttWithdrawProveUser", httWithdrawProveUserVo);
		return i > 0 ? true : false;
	}


	@Override
	public Long selectCountHttWithdrawProveUserOfAppUser(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectCountHttWithdrawProveUserOfAppUser", map);
	}


	@Override
	public String selectMasterPhoneNumOfAppUser(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectMasterPhoneNumOfAppUser", phoneNum);
	}


	@Override
	public HttLoginHisVo selectLastestHttLoginHis(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectLastestHttLoginHis", phoneNum);
	}


	@Override
	public Map<String, String> selectWechatAndAlipayInfo(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectWechatAndAlipayInfo", phoneNum);
	}


	@Override
	public Long selectCountHttLoginHis(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectCountHttLoginHis", map);
	}


	@Override
	public HttWithdrawProveUserVo selectHttWithdrawProveUserByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectHttWithdrawProveUserByPhoneNum", phoneNum);
	}


	@Override
	public boolean updateHttWithdrawProveUser(HttWithdrawProveUserVo httWithdrawProveUserVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateHttWithdrawProveUser", httWithdrawProveUserVo);
		return i > 0 ? true : false;
	}

}
