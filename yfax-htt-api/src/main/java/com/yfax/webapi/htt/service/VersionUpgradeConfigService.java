package com.yfax.webapi.htt.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.webapi.htt.dao.VersionUpgradeConfigDao;
import com.yfax.webapi.htt.vo.VersionUpgradeConfigVo;

/**
 * 广告内容详情配置
 * @author Minbo
 */
@Service
public class VersionUpgradeConfigService{
	
	protected static Logger logger = LoggerFactory.getLogger(VersionUpgradeConfigService.class);
	
	@Autowired
	private VersionUpgradeConfigDao dao;
	
	public VersionUpgradeConfigVo selectVersionUpdateByVersionCodeAndChannel(Map<String, Object> map) {
		return this.dao.selectVersionUpdateByVersionCodeAndChannel(map);
	}

}
