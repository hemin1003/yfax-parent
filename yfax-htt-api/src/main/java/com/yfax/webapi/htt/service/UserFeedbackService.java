package com.yfax.webapi.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.UserFeedbackDao;
import com.yfax.webapi.htt.vo.UserFeedbackVo;

/**
 * 用户反馈
 * @author Minbo
 */
@Service
public class UserFeedbackService{
	
	protected static Logger logger = LoggerFactory.getLogger(UserFeedbackService.class);
	
	@Autowired
	private UserFeedbackDao userFeedbackDao;
	
	public boolean addUserFeedback(UserFeedbackVo userFeedbackVo){
		try {
			return this.userFeedbackDao.insertUserFeedback(userFeedbackVo);
		} catch (Exception e) {
			logger.error("新增用户反馈记录异常：" + e.getMessage(), e);
			return false;
		}
	}

}
