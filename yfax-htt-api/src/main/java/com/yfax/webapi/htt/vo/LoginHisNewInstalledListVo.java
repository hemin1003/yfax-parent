package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class LoginHisNewInstalledListVo implements Serializable {

	private static final long serialVersionUID = -5349993237162943212L;

	private String id; // 主键
	private String pid; // 父id
	private String installedList; // 安装列表
	private String createDate; // 创建时间
	private String createdBy; // 创建人
	private String updateDate; // 更新时间
	private String updatedBy; // 更新人
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getInstalledList() {
		return installedList;
	}
	public void setInstalledList(String installedList) {
		this.installedList = installedList;
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
}