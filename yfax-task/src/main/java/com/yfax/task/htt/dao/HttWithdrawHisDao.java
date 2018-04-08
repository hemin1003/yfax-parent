package com.yfax.task.htt.dao;

import java.util.List;
import java.util.Map;

import com.yfax.task.htt.vo.HttWithdrawHisVo;

public interface HttWithdrawHisDao {
	public List<HttWithdrawHisVo> selectHttWithdrawHis();
	public boolean updateHttWithdrawHis(HttWithdrawHisVo httWithdrawHisVo) throws Exception;
	public List<HttWithdrawHisVo> selectHttWithdrawHisByPhoneNum(String phoneNum);
	public boolean updateHttWithdrawHisForBlackList(Map<String, Object> map);
	public List<HttWithdrawHisVo> selectHttWithdrawHisByProve(Map<String, Object> map);
	public List<HttWithdrawHisVo> selectHttWithdrawHisForAlipay();
}
