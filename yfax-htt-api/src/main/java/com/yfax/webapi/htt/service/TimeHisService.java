package com.yfax.webapi.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.UUID;
import com.yfax.webapi.htt.dao.TimeHisDao;
import com.yfax.webapi.htt.vo.TimeHisVo;

/**
 * 记录用户时段记录历史
 * @author Minbo
 */
@Service
public class TimeHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(TimeHisService.class);
	
	@Autowired
	private TimeHisDao dao;
	
	public TimeHisVo selectTimeHisDate(String phoneNum) {
		return this.dao.selectTimeHisDate(phoneNum);
	}
	
	public boolean addTimeHis(String phoneNum, String cTime){
		try {
			TimeHisVo timeHisVo = new TimeHisVo();
			timeHisVo.setId(UUID.getUUID());
			timeHisVo.setPhoneNum(phoneNum);
			timeHisVo.setIsUsed(0);	//未使用
			timeHisVo.setCreateDate(cTime);
			timeHisVo.setUpdateDate(cTime);
			return this.dao.insertTimeHis(timeHisVo);
		} catch (Exception e) {
			logger.error("记录用户时段记录异常(初始化)：" + e.getMessage(), e);
			return false;
		}
	}
	
	public boolean addTimeHis(TimeHisVo timeHisVo){
		try {
			return this.dao.insertTimeHis(timeHisVo);
		} catch (Exception e) {
			logger.error("记录用户时段记录异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public boolean modifyTimeHis(TimeHisVo timeHisVo){
		try {
			return this.dao.updateTimeHis(timeHisVo);
		} catch (Exception e) {
			logger.error("更新用户时段记录异常：" + e.getMessage(), e);
			return false;
		}
	}

}
