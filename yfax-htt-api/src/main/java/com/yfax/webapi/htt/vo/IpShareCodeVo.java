package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class IpShareCodeVo implements Serializable{

	private static final long serialVersionUID = 4491083377669852616L;
	
	private String id;			//主键
	private String sourceIp;		//访问源ip
	private String shareCode;	//分享码
	private Integer isUsed;		//是否已使用
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	private String tyToken;		//天翼空间token信息
	private String tyTaskCode;	//天翼任务标识
	private String tyResult;		//天翼任务完成结果
	private Integer tyIsFinished; //天翼任务是否已完成
	private String xbInfo;		//学吧任务字段信息
	private String xbResult;		//学吧任务完成结果
	private Integer xbIsFinished;	//学吧任务是否已完成
	
	public String getXbInfo() {
		return xbInfo;
	}
	public void setXbInfo(String xbInfo) {
		this.xbInfo = xbInfo;
	}
	public String getXbResult() {
		return xbResult;
	}
	public void setXbResult(String xbResult) {
		this.xbResult = xbResult;
	}
	public Integer getXbIsFinished() {
		return xbIsFinished;
	}
	public void setXbIsFinished(Integer xbIsFinished) {
		this.xbIsFinished = xbIsFinished;
	}
	public Integer getTyIsFinished() {
		return tyIsFinished;
	}
	public void setTyIsFinished(Integer tyIsFinished) {
		this.tyIsFinished = tyIsFinished;
	}
	public String getTyResult() {
		return tyResult;
	}
	public void setTyResult(String tyResult) {
		this.tyResult = tyResult;
	}
	public String getTyTaskCode() {
		return tyTaskCode;
	}
	public void setTyTaskCode(String tyTaskCode) {
		this.tyTaskCode = tyTaskCode;
	}
	public String getTyToken() {
		return tyToken;
	}
	public void setTyToken(String tyToken) {
		this.tyToken = tyToken;
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
	@Override
	public String toString() {
		return "IpShareCodeVo [id=" + id + ", sourceIp=" + sourceIp + ", shareCode=" + shareCode + ", isUsed=" + isUsed
				+ ", createDate=" + createDate + ", createdBy=" + createdBy + ", updateDate=" + updateDate
				+ ", updatedBy=" + updatedBy + "]";
	}
}
