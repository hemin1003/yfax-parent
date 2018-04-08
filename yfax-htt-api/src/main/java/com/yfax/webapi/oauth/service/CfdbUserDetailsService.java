package com.yfax.webapi.oauth.service;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.yfax.utils.DateUtil;
import com.yfax.utils.StrUtil;
import com.yfax.webapi.htt.dao.AppUserDao;
import com.yfax.webapi.htt.dao.AppUserMultiDao;
import com.yfax.webapi.htt.vo.AppUserMultiVo;
import com.yfax.webapi.htt.vo.AppUserVo;

@Component("userDetailsService")
public class CfdbUserDetailsService implements UserDetailsService {

	protected static Logger logger = LoggerFactory.getLogger(CfdbUserDetailsService.class);

	@Autowired
	private AppUserDao appUserDao;
	@Autowired
	private AppUserMultiDao appUserMultiDao;

	@Override
	public UserDetails loadUserByUsername(String login) {
		boolean isWechat = false;
		String[] str = login.toLowerCase().split("@");
		if (str.length >= 2 && str[1].equals("w")) {
			isWechat = true;
		}
		String phoneNum = str[0];
		String userLoginName = "";
		String userUserPwd = "";
		if (isWechat) {
			AppUserMultiVo appUserMultiVo = this.appUserMultiDao.selectBySystemUserId(phoneNum);
			if (appUserMultiVo == null) {
				logger.warn("[微信]用户不存在，appUserMultiVo phoneNum=" + phoneNum + " was not found in the database");
				return new org.springframework.security.core.userdetails.User(phoneNum, null, null);
			}
			AppUserVo userFromDatabase = this.appUserDao.selectByPhoneNum(phoneNum);
			if (userFromDatabase != null) {
//				if (userFromDatabase.getBlackList() == 1) {
//					logger.warn("phoneNum=" + phoneNum + "，黑名单用户，禁止[微信]登陆。");
//					return new org.springframework.security.core.userdetails.User(appUserMultiVo.getSystemUserId(),
//							appUserMultiVo.getWechatPwd(), null);
//				}
				userFromDatabase.setLastLoginDate(DateUtil.getCurrentLongDateTime());
				// 更新最后一次登录时间
				this.appUserDao.updateUserLastLoginDate(userFromDatabase);
				userLoginName = appUserMultiVo.getSystemUserId();
				userUserPwd = appUserMultiVo.getWechatPwd();
				logger.info("微信账户登录。phoneNum=" + phoneNum);

				// 清除微信生成的token
				this.appUserDao.deleteByTokenId(phoneNum);
			}
			phoneNum = appUserMultiVo.getOwnPhoneNum();

		} else {
			// 验证登录名
			AppUserVo userFromDatabase = this.appUserDao.selectLoginByPhoneNum(phoneNum);
			if (userFromDatabase == null) {
				logger.warn("[手机号码]用户不存在，AppUserVo phoneNum=" + phoneNum + " was not found in the database");
				return new org.springframework.security.core.userdetails.User(phoneNum, null, null);
			}
//			if (userFromDatabase.getBlackList() == 1) {
//				logger.warn("phoneNum=" + phoneNum + "，黑名单用户，禁止[手机号码]登陆。");
//				return new org.springframework.security.core.userdetails.User(userFromDatabase.getPhoneNum(),
//						userFromDatabase.getUserPwd(), null);
//			}
			userFromDatabase.setLastLoginDate(DateUtil.getCurrentLongDateTime());
			// 更新最后一次登录时间
			this.appUserDao.updateUserLastLoginDate(userFromDatabase);
			userLoginName = userFromDatabase.getRealPhoneNum();
			if (StrUtil.null2Str(userLoginName).equals("")) {
				userLoginName = userFromDatabase.getPhoneNum();
			}
			userUserPwd = userFromDatabase.getUserPwd();
			logger.info("手机号码登录。phoneNum=" + phoneNum);

			AppUserMultiVo appUserMultiVo = this.appUserMultiDao.selectByPhoneNum(phoneNum);
			if (appUserMultiVo != null && !appUserMultiVo.getSystemUserId().equals(phoneNum)) {
				// 清除微信生成的token
				this.appUserDao.deleteByTokenId(appUserMultiVo.getSystemUserId());
			}
		}
		// 每次登陆都重新生产token，清除之前手机号码生成的token
		this.appUserDao.deleteByTokenId(phoneNum);
		// 获取用户的所有权限并且SpringSecurity需要的集合
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
		grantedAuthorities.add(grantedAuthority);
		// 返回一个SpringSecurity需要的用户对象
		return new org.springframework.security.core.userdetails.User(userLoginName, userUserPwd, grantedAuthorities);

	}
}
