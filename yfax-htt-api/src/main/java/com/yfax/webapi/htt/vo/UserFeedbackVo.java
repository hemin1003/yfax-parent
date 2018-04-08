package com.yfax.webapi.htt.vo;

import java.io.Serializable;

/**
 * 用户反馈信息
 * @author Minbo
 */
public class UserFeedbackVo implements Serializable {

	private static final long serialVersionUID = 5986150948674599735L;
	
	private String id;			//主键
	private String phoneNum;		//手机号码
	private String info;			//反馈信息
	private String createDate;	//创建时间
	private String updateDate;	//更新时间
	private String createdBy;	//创建人
	private String updatedBy;	//更新人
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
