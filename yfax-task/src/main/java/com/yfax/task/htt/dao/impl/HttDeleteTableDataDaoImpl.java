package com.yfax.task.htt.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yfax.task.htt.dao.HttDeleteTableDataDao;

@Component
public class HttDeleteTableDataDaoImpl implements HttDeleteTableDataDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public boolean deleteHttTraceHis(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.delete("deleteHttTraceHis", map);
		return i > 0 ? true : false;
	}

	@Override
	public boolean insertHttReadHis(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.insert("insertHttReadHis", map);
		return i > 0 ? true : false;
	}

	@Override
	public boolean deleteHttReadHis(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.delete("deleteHttReadHis", map);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean insertHttReadInfoHis(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.insert("insertHttReadInfoHis", map);
		return i > 0 ? true : false;
	}

	@Override
	public boolean deleteHttReadInfoHis(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.delete("deleteHttReadInfoHis", map);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean insertHttUserTask(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.insert("insertHttUserTask", map);
		return i > 0 ? true : false;
	}

	@Override
	public boolean deleteHttUserTask(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.delete("deleteHttUserTask", map);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean insertHttVideoInfoHis(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.insert("insertHttVideoInfoHis", map);
		return i > 0 ? true : false;
	}

	@Override
	public boolean deleteHttVideoInfoHis(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.delete("deleteHttVideoInfoHis", map);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean insertHttAwardHisOfTransfer(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.insert("insertHttAwardHisOfTransfer", map);
		return i > 0 ? true : false;
	}

	@Override
	public boolean deleteHttAwardHisOfTransfer(Map<String, Object> map) {
		int i = this.sqlSessionTemplate.delete("deleteHttAwardHisOfTransfer", map);
		return i > 0 ? true : false;
	}
}
