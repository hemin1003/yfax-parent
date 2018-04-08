package com.yfax.task.htt.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.task.htt.dao.HttDeleteTableDataDao;

/**
 * 数据表清除数据服务
 * 
 * @author Minbo
 */
@Service
public class HttDeleteTableDataService {

	protected static Logger logger = LoggerFactory.getLogger(HttDeleteTableDataService.class);

	@Autowired
	private HttDeleteTableDataDao dao;

	public boolean deleteHttTraceHis(Map<String, Object> map) {
		return this.dao.deleteHttTraceHis(map);
	}
	
	public boolean insertHttReadHis(Map<String, Object> map) {
		return this.dao.insertHttReadHis(map);
	}
	
	public boolean deleteHttReadHis(Map<String, Object> map) {
		return this.dao.deleteHttReadHis(map);
	}
	
	public boolean insertHttReadInfoHis(Map<String, Object> map) {
		return this.dao.insertHttReadInfoHis(map);
	}
	
	public boolean deleteHttReadInfoHis(Map<String, Object> map) {
		return this.dao.deleteHttReadInfoHis(map);
	}
	
	public boolean insertHttUserTask(Map<String, Object> map) {
		return this.dao.insertHttUserTask(map);
	}
	
	public boolean deleteHttUserTask(Map<String, Object> map) {
		return this.dao.deleteHttUserTask(map);
	}
	
	public boolean insertHttVideoInfoHis(Map<String, Object> map) {
		return this.dao.insertHttVideoInfoHis(map);
	}
	
	public boolean deleteHttVideoInfoHis(Map<String, Object> map) {
		return this.dao.deleteHttVideoInfoHis(map);
	}
	
	public boolean insertHttAwardHisOfTransfer(Map<String, Object> map) {
		return this.dao.insertHttAwardHisOfTransfer(map);
	}
	
	public boolean deleteHttAwardHisOfTransfer(Map<String, Object> map) {
		return this.dao.deleteHttAwardHisOfTransfer(map);
	}
}