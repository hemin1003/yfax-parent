package com.yfax.task.htt.dao;

import java.util.Map;

import com.yfax.task.htt.vo.HttLoginHisVo;

public interface HttLoginHisDao {
	public HttLoginHisVo selectHttLoginHis(Map<String, String> map);
	public HttLoginHisVo selectHttLoginHisOfOutRoots(Map<String, String> map);
	public HttLoginHisVo selectHttLoginHisOfCheatApps(Map<String, String> map);
	public HttLoginHisVo selectHttLoginHisSuspectOfCheatApps(Map<String, Object> map);
}
