package com.yfax.task.htt.dao;

import java.util.Map;

public interface HttDeleteTableDataDao {
	
	public boolean deleteHttTraceHis(Map<String, Object> map);
	
	public boolean insertHttReadHis(Map<String, Object> map);
	public boolean deleteHttReadHis(Map<String, Object> map);
	
	public boolean insertHttReadInfoHis(Map<String, Object> map);
	public boolean deleteHttReadInfoHis(Map<String, Object> map);
	
	public boolean insertHttUserTask(Map<String, Object> map);
	public boolean deleteHttUserTask(Map<String, Object> map);
	
	public boolean insertHttVideoInfoHis(Map<String, Object> map);
	public boolean deleteHttVideoInfoHis(Map<String, Object> map);
	
	public boolean insertHttAwardHisOfTransfer(Map<String, Object> map);
	public boolean deleteHttAwardHisOfTransfer(Map<String, Object> map);
}
