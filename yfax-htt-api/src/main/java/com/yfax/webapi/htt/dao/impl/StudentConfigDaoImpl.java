package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.StudentConfigDao;
import com.yfax.webapi.htt.vo.StudentConfigVo;


@Component
public class StudentConfigDaoImpl implements StudentConfigDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public StudentConfigVo selectStudentConfig(long awardCount) {
		return this.sqlSessionTemplate.selectOne("selectStudentConfig", awardCount);
	}

}
