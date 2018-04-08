package com.yfax.task.htt.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.common.sms.SmsService;
import com.yfax.task.htt.dao.HttSmsActiveUserDao;
import com.yfax.utils.DateUtil;
import com.yfax.utils.JsonResult;
import com.yfax.utils.ResultCode;

/**
 * 用户管理服务
 * 
 * @author Minbo
 */
@Service
public class HttSmsActiveUserService {

	protected static Logger logger = LoggerFactory.getLogger(HttSmsActiveUserService.class);

	@Autowired
	private HttSmsActiveUserDao httSmsActiveUserDao;
	
	public List<String> selectInactiveRealPhoneNum(){
		String sevenDayAgo = DateUtil.getCurrentDate(-7);
		return this.httSmsActiveUserDao.selectInactiveRealPhoneNum(sevenDayAgo);
	}
	
	public JsonResult sendSmsToInactiveUser(String phoneNum) {
		// 发送短信
		HashMap<String, Object> result = null;
		if ("000000".equals(result.get("statusCode"))) {
			return new JsonResult(ResultCode.SUCCESS, result);
		} else {
			return new JsonResult(ResultCode.SUCCESS_FAIL, result);
		}
	}
	
}