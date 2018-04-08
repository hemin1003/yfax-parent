package com.yfax.task.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttAwardHisDao;
import com.yfax.task.htt.vo.HttAwardHisVo;

@Component
public class HttAwardHisDaoImpl implements HttAwardHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public boolean insertHttAwardHis(HttAwardHisVo httAwardHisVo) {
		int i = this.sqlSessionTemplate.insert("insertHttAwardHis", httAwardHisVo);
		return i > 0 ? true : false;
	}

}
