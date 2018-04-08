package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class StudentConfigVo implements Serializable{
	
	private static final long serialVersionUID = -17403055952054346L;
	
	private String id;			//主键
	private Integer startValue;	//阅读次数开始值
	private Integer endValue;	//阅读次数终止值
	private Integer alpha;		//奖励阈值配置数
	private Integer status;		//记录状态，1=已下线，2=已上线
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	public Integer getStartValue() {
		return startValue;
	}
	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}
	public Integer getEndValue() {
		return endValue;
	}
	public void setEndValue(Integer endValue) {
		this.endValue = endValue;
	}
	public Integer getAlpha() {
		return alpha;
	}
	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
