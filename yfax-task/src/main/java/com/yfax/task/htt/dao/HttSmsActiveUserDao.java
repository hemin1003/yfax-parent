package com.yfax.task.htt.dao;

import java.util.List;


public interface HttSmsActiveUserDao {
	
	public List<String> selectInactiveRealPhoneNum(String sevenDayAgo);
	
}
