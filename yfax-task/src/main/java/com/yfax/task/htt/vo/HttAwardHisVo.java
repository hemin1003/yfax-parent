package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttAwardHisVo implements Serializable{

	private static final long serialVersionUID = -1444027981654018006L;
	
	private String id;			//主键
	private String phoneNum;		//手机号码
	private Integer awardType;	//奖励类型
	private String awardName;	//奖励类型名
	private String gold;			//金币值
	private Integer isCal;		//是否统计结算过
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	public Integer getIsCal() {
		return isCal;
	}
	public void setIsCal(Integer isCal) {
		this.isCal = isCal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Integer getAwardType() {
		return awardType;
	}
	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
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
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	
}
