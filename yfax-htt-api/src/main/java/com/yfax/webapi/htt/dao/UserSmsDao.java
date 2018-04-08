package com.yfax.webapi.htt.dao;

import java.util.Map;

import com.yfax.webapi.htt.vo.UserSmsVo;

public interface UserSmsDao {
    public UserSmsVo selectLastestUserSms(String phoneNum);
    public UserSmsVo selectUserSms(Map<Object, Object> map);
    public boolean insertUserSms(UserSmsVo userSms) throws Exception;
    public Long selectUserSmsByPhoneNum(Map<Object, Object> map);
    public UserSmsVo selectUserSmsByMsgCode(String msgCode);
}
