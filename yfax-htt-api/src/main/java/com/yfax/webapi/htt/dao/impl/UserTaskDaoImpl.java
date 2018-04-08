package com.yfax.webapi.htt.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.UserTaskDao;
import com.yfax.webapi.htt.vo.UserTaskVo;


@Component
public class UserTaskDaoImpl implements UserTaskDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertUserTask(UserTaskVo userTask) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertUserTask", userTask);
		return i > 0 ? true : false;
	}

	@Override
	public UserTaskVo selectUserTask(UserTaskVo userTask) {
		return this.sqlSessionTemplate.selectOne("selectUserTask", userTask);
	}

	@Override
	public boolean updateUserTask(UserTaskVo userTask) throws Exception {
		int i = this.sqlSessionTemplate.insert("updateUserTask", userTask);
		return i > 0 ? true : false;
	}

	@Override
	public UserTaskVo selectNewUserTask(UserTaskVo userTask) {
		return this.sqlSessionTemplate.selectOne("selectNewUserTask", userTask);
	}

	@Override
	public UserTaskVo selectDailyUserTask(UserTaskVo userTask) {
		return this.sqlSessionTemplate.selectOne("selectDailyUserTask", userTask);
	}

}
