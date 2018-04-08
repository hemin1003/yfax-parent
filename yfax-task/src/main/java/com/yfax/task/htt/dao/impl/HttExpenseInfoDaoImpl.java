package com.yfax.task.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttExpenseInfoDao;
import com.yfax.task.htt.vo.HttExpenseInfoVo;

@Component
public class HttExpenseInfoDaoImpl implements HttExpenseInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public boolean insertHttExpenseInfo(HttExpenseInfoVo httExpenseInfoVo) {
		int i = this.sqlSessionTemplate.insert("insertHttExpenseInfo", httExpenseInfoVo);
		return i > 0 ? true : false;
	}

	@Override
	public HttExpenseInfoVo selectHttExpenseInfo(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectOne("selectHttExpenseInfo", params);
	}

	@Override
	public boolean updateHttExpenseInfo(HttExpenseInfoVo httExpenseInfoVo) {
		int i = this.sqlSessionTemplate.update("updateHttExpenseInfoById", httExpenseInfoVo);
		return i > 0 ? true : false;
	}

}
