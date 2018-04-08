package com.yfax.task.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yfax.task.htt.dao.HttLoginHisDao;
import com.yfax.task.htt.vo.HttLoginHisVo;

@Component
public class HttLoginHisDaoImpl implements HttLoginHisDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public HttLoginHisVo selectHttLoginHis(Map<String, String> map) {
		return this.sqlSessionTemplate.selectOne("selectHttLoginHis", map);
	}

	@Override
	public HttLoginHisVo selectHttLoginHisOfCheatApps(Map<String, String> map) {
		return this.sqlSessionTemplate.selectOne("selectHttLoginHisOfCheatApps", map);
	}

	@Override
	public HttLoginHisVo selectHttLoginHisOfOutRoots(Map<String, String> map) {
		return this.sqlSessionTemplate.selectOne("selectHttLoginHisOfOutRoots", map);
	}

	@Override
	public HttLoginHisVo selectHttLoginHisSuspectOfCheatApps(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectHttLoginHisSuspectOfCheatApps", map);
	};

}
