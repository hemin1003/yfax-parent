package com.yfax.webapi.htt.dao;

import java.util.Map;

import com.yfax.webapi.htt.vo.VersionUpgradeConfigVo;

public interface VersionUpgradeConfigDao {
	
	//根据版本号与渠道号查询版本记录
	public VersionUpgradeConfigVo selectVersionUpdateByVersionCodeAndChannel(Map<String,Object> map);
	
}
