package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class BigUserVo implements Serializable{
	
	private static final long serialVersionUID = 3574886939087534243L;

	private String phoneNum; // 手机号码
	private String sysUserId; // 后台系统绑定的登录账户名
	private String inviteCode; // 邀请码
	private String inviteUrl; // 专属邀请链接

	private String downloadUrl; // 下载链接

	private String createDate; // 创建时间
	private String createdBy; // 创建人
	private String updateDate; // 更新时间
	private String updatedBy; // 更新人

	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public String getInviteUrl() {
		return inviteUrl;
	}
	public void setInviteUrl(String inviteUrl) {
		this.inviteUrl = inviteUrl;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Override
	public String toString() {
		return "BigUserVo [phoneNum=" + phoneNum + ", sysUserId="
				+ sysUserId + ", inviteCode=" + inviteCode + ", inviteUrl=" + inviteUrl + ", createDate=" + createDate
				+ ", createdBy=" + createdBy + ", updateDate=" + updateDate + ", updatedBy=" + updatedBy + "]";
	}
}
