package com.yfax.task.htt.dao;

import java.util.List;

import com.yfax.task.htt.vo.HttBlacklistHisVo;

public interface HttBlacklistHisDao {
	public boolean insertBlacklistHis(HttBlacklistHisVo blacklistHisVo) throws Exception;
	public boolean batchInsertBlacklistHis(List<HttBlacklistHisVo> list);
}
