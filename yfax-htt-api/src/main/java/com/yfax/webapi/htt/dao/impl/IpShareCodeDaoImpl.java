package com.yfax.webapi.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yfax.webapi.htt.dao.IpShareCodeDao;
import com.yfax.webapi.htt.vo.IpShareCodeVo;


@Component
public class IpShareCodeDaoImpl implements IpShareCodeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	@Transactional
	public boolean insertIpShareCode(IpShareCodeVo ipShareCodeVo) throws Exception {
		int i = this.sqlSessionTemplate.insert("insertIpShareCode", ipShareCodeVo);
		return i > 0 ? true : false;
	}

	@Override
	public IpShareCodeVo selectIpShareCodeByIp(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectIpShareCodeByIp", map);
	}

	@Override
	public IpShareCodeVo selectIpShareCodeIsFromIp(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("selectIpShareCodeIsFromIp", map);
	}

	@Override
	@Transactional
	public boolean updateIpShareCode(IpShareCodeVo ipShareCodeVo) throws Exception {
		int i = this.sqlSessionTemplate.update("updateIpShareCode", ipShareCodeVo);
		return i > 0 ? true : false;
	}

}
