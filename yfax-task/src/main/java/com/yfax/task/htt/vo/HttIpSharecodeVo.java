package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttIpSharecodeVo implements Serializable {

	private static final long serialVersionUID = 3461505427564992113L;

	private String id;
	private String sourceIp;
	private String shareCode;
	private Integer isUsed;
	private String tyToken;
	private String tyTaskCode;
	private String tyResult;
	private Integer tyIsFinished;
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public String getTyToken() {
		return tyToken;
	}

	public void setTyToken(String tyToken) {
		this.tyToken = tyToken;
	}

	public String getTyTaskCode() {
		return tyTaskCode;
	}

	public void setTyTaskCode(String tyTaskCode) {
		this.tyTaskCode = tyTaskCode;
	}

	public String getTyResult() {
		return tyResult;
	}

	public void setTyResult(String tyResult) {
		this.tyResult = tyResult;
	}

	public Integer getTyIsFinished() {
		return tyIsFinished;
	}

	public void setTyIsFinished(Integer tyIsFinished) {
		this.tyIsFinished = tyIsFinished;
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