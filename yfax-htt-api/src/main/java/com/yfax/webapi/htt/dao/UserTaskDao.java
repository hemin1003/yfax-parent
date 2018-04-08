package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.UserTaskVo;

public interface UserTaskDao {
	public UserTaskVo selectUserTask(UserTaskVo userTask);
	public boolean insertUserTask(UserTaskVo userTask) throws Exception;
	public boolean updateUserTask(UserTaskVo userTask) throws Exception;
	public UserTaskVo selectNewUserTask(UserTaskVo userTask);
	public UserTaskVo selectDailyUserTask(UserTaskVo userTask);
}
