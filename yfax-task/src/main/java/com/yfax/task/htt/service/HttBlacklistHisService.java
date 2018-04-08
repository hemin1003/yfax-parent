package com.yfax.task.htt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttBlacklistHisDao;
import com.yfax.task.htt.vo.HttBlacklistHisVo;
import com.yfax.utils.UUID;

/**
 * 黑名单规则匹配记录历史
 * @author Minbo
 */
@Service
public class HttBlacklistHisService{
	
	protected static Logger logger = LoggerFactory.getLogger(HttBlacklistHisService.class);
	
	@Autowired
	private HttBlacklistHisDao dao;
	
	public HttBlacklistHisVo addBlacklistHis(String phoneNum, String strategy, String cTime, Integer accountStatus) {
		HttBlacklistHisVo blacklistHisVo = new HttBlacklistHisVo();
		blacklistHisVo.setId(UUID.getUUID());
		blacklistHisVo.setPhoneNum(phoneNum);
		blacklistHisVo.setSource(2);
		blacklistHisVo.setStrategy(strategy);
		blacklistHisVo.setAccountStatus(accountStatus);
		blacklistHisVo.setCreateDate(cTime);
		blacklistHisVo.setUpdateDate(cTime);
		return blacklistHisVo;
	}
	
	public boolean batchInsertBlacklistHis(List<HttBlacklistHisVo> list) {
		return this.dao.batchInsertBlacklistHis(list);
	}
}
