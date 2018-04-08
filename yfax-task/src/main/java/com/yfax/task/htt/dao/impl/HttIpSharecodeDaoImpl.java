package com.yfax.task.htt.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttIpSharecodeDao;
import com.yfax.task.htt.vo.HttIpSharecodeVo;

@Component
public class HttIpSharecodeDaoImpl implements HttIpSharecodeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<HttIpSharecodeVo> selectHttIpSharecodeUnFinished() {
		return this.sqlSessionTemplate.selectList("selectHttIpSharecodeUnFinished");
	}

	@Override
	public Boolean batchUpdateUnFinished(List<HttIpSharecodeVo> list) {
		int i = this.sqlSessionTemplate.update("batchUpdateHttIpSharecodeUnFinished", list);
		return i > 0 ? true : false ;
	}

}
