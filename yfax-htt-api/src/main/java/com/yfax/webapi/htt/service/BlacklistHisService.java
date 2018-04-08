package com.yfax.webapi.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.UUID;
import com.yfax.webapi.htt.dao.BlacklistHisDao;
import com.yfax.webapi.htt.vo.BlacklistHisVo;

/**
 * 黑名单规则匹配记录历史
 * @author Minbo
 */
@Service
public class BlacklistHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(BlacklistHisService.class);
	
	@Autowired
	private BlacklistHisDao dao;
	
	public boolean addBlacklistHis(String phoneNum, String strategy, String cTime) {
		try {
			BlacklistHisVo blacklistHisVo = new BlacklistHisVo();
			blacklistHisVo.setId(UUID.getUUID());
			blacklistHisVo.setPhoneNum(phoneNum);
			blacklistHisVo.setSource(1);
			blacklistHisVo.setStrategy(strategy);
			blacklistHisVo.setAccountStatus(2);
			blacklistHisVo.setCreateDate(cTime);
			blacklistHisVo.setUpdateDate(cTime);
			return this.dao.insertBlacklistHis(blacklistHisVo);
		} catch (Exception e) {
			logger.error("记录黑名单规则异常：" + e.getMessage(), e);
			return false;
		}
	}
}
