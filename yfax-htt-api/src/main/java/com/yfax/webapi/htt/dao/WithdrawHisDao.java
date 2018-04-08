package com.yfax.webapi.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.webapi.htt.vo.WithdrawHisVo;

public interface WithdrawHisDao {
	public List<WithdrawHisVo> selectWithdrawHis(String phoneId);
	public boolean insertWithdrawHis(WithdrawHisVo withdrawHisVo) throws Exception;
	public long selectCountByWithdrawHisType(Map<String, Object> map);
	public long selectWithdrawHisCountByPhoneNumAndTime(Map<String, Object> map);
}
