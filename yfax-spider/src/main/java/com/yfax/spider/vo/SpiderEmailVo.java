package com.yfax.spider.vo;

import java.io.Serializable;

public class SpiderEmailVo implements Serializable{
	
	private static final long serialVersionUID = 3461505427564992113L;
	
	private String id;			//主键
	private String email;		//邮件地址
	private String emailName;	//邮件人名称
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailName() {
		return emailName;
	}
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	@Override
	public String toString() {
		return "SpiderEmailVo [id=" + id + ", email=" + email + ", emailName=" + emailName + ", createDate="
				+ createDate + ", createdBy=" + createdBy + ", updateDate=" + updateDate + ", updatedBy=" + updatedBy
				+ "]";
	}
}
