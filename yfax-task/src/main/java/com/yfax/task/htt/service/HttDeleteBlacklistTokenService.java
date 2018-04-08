package com.yfax.task.htt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttDeleteBlacklistTokenDao;

/**
 * 定时清除黑名单token
 * 
 * @author Minbo
 */
@Service
public class HttDeleteBlacklistTokenService {

	protected static Logger logger = LoggerFactory.getLogger(HttDeleteBlacklistTokenService.class);

	@Autowired
	private HttDeleteBlacklistTokenDao httDeleteBlacklistTokenDao;

	public boolean deleteBlacklistToken() {
		return httDeleteBlacklistTokenDao.deleteBlacklistToken();
	}
	
	/**
	 * 清除refresh token数据，无用数据
	 * @return
	 */
	public boolean deleteHttOauthRefreshToken() {
		return httDeleteBlacklistTokenDao.deleteHttOauthRefreshToken();
	}

}