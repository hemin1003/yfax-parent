package com.yfax.task.htt.dao;

import java.util.List;

import com.yfax.task.htt.vo.HttWithdrawProveUserLinkVo;

public interface HttWithdrawProveUserLinkDao {
	public boolean insertHttWithdrawProveUserLink(HttWithdrawProveUserLinkVo httWithdrawProveUserLinkVo)
			throws Exception;

	public boolean batchInsertHttWithdrawProveUserLink(List<HttWithdrawProveUserLinkVo> list) throws Exception;
	
	public boolean deleteByPid(String pid) throws Exception;
}
