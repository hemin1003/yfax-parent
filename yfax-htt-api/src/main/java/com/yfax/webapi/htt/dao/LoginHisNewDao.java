package com.yfax.webapi.htt.dao;

import java.util.Map;

import com.yfax.webapi.htt.vo.LoginHisNewVo;

public interface LoginHisNewDao {
	public LoginHisNewVo selectLoginHisNewDate(String phoneNum);
	public boolean insertLoginHisNew(LoginHisNewVo loginHis) throws Exception;
	public boolean updateLoginHisNew(LoginHisNewVo loginHis) throws Exception;
	public Long selectCountOfLoginHisNewImei(Map<String, Object> params);
	public Long selectCountOfLoginHisNewPhoneNum(Map<String, Object> params);
}
