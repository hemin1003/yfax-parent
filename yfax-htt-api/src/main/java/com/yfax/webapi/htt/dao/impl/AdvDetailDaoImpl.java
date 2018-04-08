package com.yfax.webapi.htt.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.webapi.htt.dao.AdvDetailDao;
import com.yfax.webapi.htt.vo.AdvDetailVo;


@Component
public class AdvDetailDaoImpl implements AdvDetailDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;;
	
	@Override
	public List<AdvDetailVo> selectAdvDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("selectAdvDetail", map);
	}

}
