package com.yfax.webapi.htt.dao;

import java.util.List;

import com.yfax.webapi.htt.vo.BalanceHisVo;

public interface BalanceHisDao {
	public boolean insertBalanceHis(BalanceHisVo balanceHisVo) throws Exception;
	public List<BalanceHisVo> selectBalanceHisByPhoneNum(String phoneNum);
	public long selectBalanceHisByType(BalanceHisVo balanceHisVo);
}
