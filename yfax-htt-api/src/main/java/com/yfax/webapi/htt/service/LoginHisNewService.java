package com.yfax.webapi.htt.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfax.utils.DateUtil;
import com.yfax.utils.UUID;
import com.yfax.webapi.htt.dao.LoginHisNewDao;
import com.yfax.webapi.htt.dao.LoginHisNewInstalledListDao;
import com.yfax.webapi.htt.vo.LoginHisNewInstalledListVo;
import com.yfax.webapi.htt.vo.LoginHisNewVo;

/**
 * 记录用户登录历史
 * 
 * @author Minbo
 */
@Service
public class LoginHisNewService {

	protected static Logger logger = LoggerFactory.getLogger(LoginHisNewService.class);

	@Autowired
	private LoginHisNewDao dao;
	@Autowired
	private LoginHisNewInstalledListDao loginHisNewInstalledListDao;

	public LoginHisNewVo selectLoginHisNewDate(String phoneNum) {
		return this.dao.selectLoginHisNewDate(phoneNum);
	}

	public boolean addLoginHisNew(LoginHisNewVo loginHisVo) {
		try {
			LoginHisNewInstalledListVo loginHisNewInstalledListVo = new LoginHisNewInstalledListVo();
			loginHisNewInstalledListVo.setId(UUID.getUUID());
			loginHisNewInstalledListVo.setPid(loginHisVo.getId());
			loginHisNewInstalledListVo.setInstalledList(loginHisVo.getInstalledList());
			loginHisVo.setCreateDate(DateUtil.getCurrentLongDateTime());
			loginHisVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
			boolean flag = this.loginHisNewInstalledListDao.insertLoginHisNewInstalledList(loginHisNewInstalledListVo);
			if (!flag) {
				logger.error("记录用户登录安装列表异常phoneNum=" + loginHisVo.getPhoneNum(), 
						new RuntimeException("安装列表记录异常"));
			}
			return this.dao.insertLoginHisNew(loginHisVo);
		} catch (Exception e) {
			logger.error("记录用户登录历史异常：" + e.getMessage(), e);
			return false;
		}
	}

	public boolean modifyLoginHisNew(LoginHisNewVo loginHisVo) {
		try {
			return this.dao.updateLoginHisNew(loginHisVo);
		} catch (Exception e) {
			logger.error("更新用户登录历史异常：" + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 查询imei当日登录设备的账户数
	 * 
	 * @param params
	 * @return
	 */
	public Long queryCountOfLoginHisNewImei(Map<String, Object> params) {
		return this.dao.selectCountOfLoginHisNewImei(params);
	}

	/**
	 * 查询imei当日账户登录的设备数
	 * 
	 * @param params
	 * @return
	 */
	public Long queryCountOfLoginHisNewPhoneNum(Map<String, Object> params) {
		return this.dao.selectCountOfLoginHisNewPhoneNum(params);
	}

}
