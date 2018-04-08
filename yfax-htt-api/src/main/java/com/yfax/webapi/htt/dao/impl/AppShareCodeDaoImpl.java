package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.AppShareCodeDao;
import com.yfax.webapi.htt.vo.AppShareCodeVo;

@Component
public class AppShareCodeDaoImpl implements AppShareCodeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertAppShareCode(AppShareCodeVo awardHisVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertAppShareCode", awardHisVo);
		return i > 0 ? true : false;
	}

	@Override
	public AppShareCodeVo selectAppShareCodeByPhoneNum(String phoneNum) {
		return this.sqlSessionTemplate.selectOne("selectAppShareCodeByPhoneNum", phoneNum);
	}

	@Override
	public AppShareCodeVo selectAppShareCodeByShareCode(String shareCode) {
		return this.sqlSessionTemplate.selectOne("selectAppShareCodeByShareCode", shareCode);
	}

}
