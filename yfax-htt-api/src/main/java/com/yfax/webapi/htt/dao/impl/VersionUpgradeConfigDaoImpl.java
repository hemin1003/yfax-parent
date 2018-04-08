package com.yfax.webapi.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.VersionUpgradeConfigDao;
import com.yfax.webapi.htt.vo.VersionUpgradeConfigVo;


@Component
public class VersionUpgradeConfigDaoImpl implements VersionUpgradeConfigDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;

	@Override
	public VersionUpgradeConfigVo selectVersionUpdateByVersionCodeAndChannel(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectVersionUpdateByVersionCodeAndChannel", map);
	}

}
