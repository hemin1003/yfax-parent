package com.yfax.webapi.htt.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.webapi.htt.dao.UserSmsDao;
import com.yfax.webapi.htt.vo.UserSmsVo;

/**
 * 记录短信验证码发送历史
 * 
 * @author Minbo
 */
@Service
public class UserSmsService {

	protected static Logger logger = LoggerFactory.getLogger(UserSmsService.class);

	@Autowired
	private UserSmsDao dao;

	public boolean addUserSms(UserSmsVo userSms) {
		try {
			return this.dao.insertUserSms(userSms);
		} catch (Exception e) {
			logger.error("记录短信验证码发送历史异常：" + e.getMessage(), e);
			return false;
		}
	}

	public UserSmsVo getUserSms(String phoneNum, String msgCode) {
		Map<Object, Object> map = new HashMap<>();
		map.put("phoneNum", phoneNum);
		map.put("msgCode", msgCode);
		return this.dao.selectUserSms(map);
	}
	
	public Long getUserSmsByPhoneNum(String phoneNum) {
		Map<Object, Object> map = new HashMap<>();
		map.put("phoneNum", phoneNum);
		map.put("currentTime", DateUtil.getCurrentDate());
		return this.dao.selectUserSmsByPhoneNum(map);
	}

	public UserSmsVo getLastestUserSms(String phoneNum) {
		return this.dao.selectLastestUserSms(phoneNum);
	}
	
	public UserSmsVo getUserSmsByMsgCode(String msgCode) {
		return this.dao.selectUserSmsByMsgCode(msgCode);
	}
}
