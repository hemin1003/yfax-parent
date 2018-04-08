package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class AppUserMultiVo implements Serializable{
	
	private static final long serialVersionUID = 4577997978188778371L;
	
	private String systemUserId;		//虚拟统一用户主键
	
	private String wechatOpenId;		//微信openid
	private String wechatNickname;	//微信昵称
	private String wechatHeadImgurl;	//微信头像
	private String wechatUnionId;	//微信unionid
	private String wechatPwd;		//微信密码
	private String wechatName;		//微信实名制姓名
	private String wechatPhoneNum;	//微信手机号码
	
	private String telPhoneNum;		//话费手机号码
	
	private String alipayAccount;	//支付宝账户名
	private String alipayName;		//支付宝姓名
	
	private String ownPhoneNum;		//绑定登录手机号码
	private String ownUserPwd;		//绑定登录密码

	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	public String getWechatPhoneNum() {
		return wechatPhoneNum;
	}
	public void setWechatPhoneNum(String wechatPhoneNum) {
		this.wechatPhoneNum = wechatPhoneNum;
	}
	public String getTelPhoneNum() {
		return telPhoneNum;
	}
	public void setTelPhoneNum(String telPhoneNum) {
		this.telPhoneNum = telPhoneNum;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getAlipayName() {
		return alipayName;
	}
	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	public String getWechatPwd() {
		return wechatPwd;
	}
	public void setWechatPwd(String wechatPwd) {
		this.wechatPwd = wechatPwd;
	}
	public String getSystemUserId() {
		return systemUserId;
	}
	public void setSystemUserId(String systemUserId) {
		this.systemUserId = systemUserId;
	}
	public String getWechatOpenId() {
		return wechatOpenId;
	}
	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}
	public String getWechatNickname() {
		return wechatNickname;
	}
	public void setWechatNickname(String wechatNickname) {
		this.wechatNickname = wechatNickname;
	}
	public String getWechatHeadImgurl() {
		return wechatHeadImgurl;
	}
	public void setWechatHeadImgurl(String wechatHeadImgurl) {
		this.wechatHeadImgurl = wechatHeadImgurl;
	}
	public String getWechatUnionId() {
		return wechatUnionId;
	}
	public void setWechatUnionId(String wechatUnionId) {
		this.wechatUnionId = wechatUnionId;
	}
	public String getOwnPhoneNum() {
		return ownPhoneNum;
	}
	public void setOwnPhoneNum(String ownPhoneNum) {
		this.ownPhoneNum = ownPhoneNum;
	}
	public String getOwnUserPwd() {
		return ownUserPwd;
	}
	public void setOwnUserPwd(String ownUserPwd) {
		this.ownUserPwd = ownUserPwd;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
