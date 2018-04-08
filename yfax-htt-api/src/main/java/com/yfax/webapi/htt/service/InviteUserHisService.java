package com.yfax.webapi.htt.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.InviteUserHisDao;
import com.yfax.webapi.htt.vo.InviteUserHisVo;

/**
 * 邀请徒弟明细记录
 * @author Minbo
 */
@Service
public class InviteUserHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(InviteUserHisService.class);
	
	@Autowired
	private InviteUserHisDao dao;
	
	/**
	 * 根据徒弟手机，找到师傅信息
	 * @param phoneNum
	 * @return
	 */
	public InviteUserHisVo selectInviteUserByStudentPhoneNum(String phoneNum) {
		return this.dao.selectInviteUserByStudentPhoneNum(phoneNum);
	}
	
	/**
	 * 看是否已经有师徒绑定关系了
	 * @param map
	 * @return
	 */
	public InviteUserHisVo selectInviteUserByMasAndStuPhoneNum(Map<String, String> map) {
		return this.dao.selectInviteUserByMasAndStuPhoneNum(map);
	}
	
	public boolean addInviteUserHis(InviteUserHisVo inviteUserHisVo) {
		try {
			return this.dao.insertInviteUserHis(inviteUserHisVo);
		} catch (Exception e) {
			logger.error("记录邀请徒弟明细记录异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	public boolean modifyInviteUserHis(InviteUserHisVo inviteUserHisVo) {
		try {
			return this.dao.updateInviteUserHis(inviteUserHisVo);
		} catch (Exception e) {
			logger.error("更新邀请徒弟明细记录异常：" + e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * 记录徒弟各金币贡献
	 * @param masterPhoneNum
	 * @param studentPhoneNum
	 * @param gold
	 */
	public void processStudengGold(String masterPhoneNum, String studentPhoneNum, int gold) {
		Map<String, String> mapParams = new HashMap<>();
		mapParams.put("masterPhoneNum", masterPhoneNum);
		mapParams.put("studentPhoneNum", studentPhoneNum);
		InviteUserHisVo inviteUserHisVo = this.selectInviteUserByMasAndStuPhoneNum(mapParams);
		if(inviteUserHisVo != null) {
			long sumOfInviteGold = inviteUserHisVo.getStudentsGold() + gold;
			inviteUserHisVo.setStudentsGold(sumOfInviteGold);
			boolean flagOfInvite = this.modifyInviteUserHis(inviteUserHisVo);
			logger.info("masterPhoneNum" + masterPhoneNum +  ", studentPhoneNum=" + studentPhoneNum 
					+ ", 记录徒弟各金币贡献标识flagOfInvite=" + flagOfInvite);
		}
	}
	
	/**
	 * 寒假活动-查询活动期间该用户邀请徒弟总数
	 * @param masterPhoneNum
	 * @return
	 */
	public Integer holidayActivitySelectInviteUserCount(String masterPhoneNum) {
		//活动起始时间
		String startTime = "2018-01-19 18:00:00";
		String endTime = "2018-02-02 18:00:00";
		Map<String, String> mapParams = new HashMap<>();
		mapParams.put("masterPhoneNum", masterPhoneNum);
		mapParams.put("startTime", startTime);
		mapParams.put("endTime", endTime);
		return this.dao.holidayActivitySelectInviteUserCount(mapParams);
	}
}