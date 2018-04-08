package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class AppShareCodeVo implements Serializable{
	
	private static final long serialVersionUID = 2528937513486319515L;
	
	private String phoneNum;		//手机号码
	private String shareCode;	//邀请码
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
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
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getShareCode() {
		return shareCode;
	}
	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}
	@Override
	public String toString() {
		return "AppShareCodeVo [phoneNum=" + phoneNum + ", shareCode=" + shareCode + ", createDate=" + createDate
				+ ", createdBy=" + createdBy + ", updateDate=" + updateDate + ", updatedBy=" + updatedBy + "]";
	}
}
