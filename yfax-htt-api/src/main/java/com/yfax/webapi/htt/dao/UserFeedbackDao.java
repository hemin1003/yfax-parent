package com.yfax.webapi.htt.dao;

import com.yfax.webapi.htt.vo.UserFeedbackVo;

public interface UserFeedbackDao {
	public boolean insertUserFeedback(UserFeedbackVo userFeedback) throws Exception;
}
