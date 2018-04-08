package com.yfax.task.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttUserInfoDao;
import com.yfax.task.htt.vo.HttUserInfoVo;

@Component
public class HttUserInfoDaoImpl implements HttUserInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public boolean insertHttUserInfo(HttUserInfoVo httUserInfoVo) {
		int i = this.sqlSessionTemplate.insert("insertHttUserInfo", httUserInfoVo);
		return i > 0 ? true : false;
	}

	@Override
	public HttUserInfoVo selectHttUserInfo(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectHttUserInfo", params);
	}

	@Override
	public boolean updateHttUserInfo(HttUserInfoVo httUserInfoVo) {
		int i = this.sqlSessionTemplate.update("updateHttUserInfoById", httUserInfoVo);
		return i > 0 ? true : false;
	}

}
