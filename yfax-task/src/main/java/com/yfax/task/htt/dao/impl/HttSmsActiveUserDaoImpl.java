package com.yfax.task.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttSmsActiveUserDao;

@Component
public class HttSmsActiveUserDaoImpl implements HttSmsActiveUserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<String> selectInactiveRealPhoneNum(String sevenDayAgo) {
		return this.sqlSessionTemplate.selectList("selectInactiveRealPhoneNum", sevenDayAgo);
	};

}
