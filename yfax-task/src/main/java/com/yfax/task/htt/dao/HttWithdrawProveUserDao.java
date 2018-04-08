package com.yfax.task.htt.dao;

import java.util.Map;

import com.yfax.task.htt.vo.HttLoginHisVo;
import com.yfax.task.htt.vo.HttWithdrawProveUserVo;

public interface HttWithdrawProveUserDao {
	public HttWithdrawProveUserVo selectHttWithdrawProveUserByPhoneNum(String phoneNum);
	public boolean insertHttWithdrawProveUser(HttWithdrawProveUserVo httWithdrawProveUserVo) throws Exception;
	public boolean updateHttWithdrawProveUser(HttWithdrawProveUserVo httWithdrawProveUserVo) throws Exception;
	public Long selectCountHttWithdrawProveUserOfAppUser(Map<String, Object> map);
	public String selectMasterPhoneNumOfAppUser(String phoneNum);
	public HttLoginHisVo selectLastestHttLoginHis(String phoneNum);
	public Map<String, String> selectWechatAndAlipayInfo(String phoneNum);
	public Long selectCountHttLoginHis(Map<String, Object> map);
}
