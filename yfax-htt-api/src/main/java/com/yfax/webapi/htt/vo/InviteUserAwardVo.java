package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class InviteUserAwardVo implements Serializable{
	
	private static final long serialVersionUID = 4755663221270118079L;
	
	private String id;			//主键
	private String masterPhoneNum;	//师傅手机号码
	private String studentPhoneNum;	//徒弟手机号码
	private Integer time;			//次数
	private String descStr;				//描述
	private String gold;				//贡献给师傅金币
	private Integer isAward;			//是否已奖励金币
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getDescStr() {
		return descStr;
	}
	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public Integer getIsAward() {
		return isAward;
	}
	public void setIsAward(Integer isAward) {
		this.isAward = isAward;
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
	public String getMasterPhoneNum() {
		return masterPhoneNum;
	}
	public void setMasterPhoneNum(String masterPhoneNum) {
		this.masterPhoneNum = masterPhoneNum;
	}
	public String getStudentPhoneNum() {
		return studentPhoneNum;
	}
	public void setStudentPhoneNum(String studentPhoneNum) {
		this.studentPhoneNum = studentPhoneNum;
	}
	
	@Override
	public String toString() {
		return "InviteUserAwardVo [id=" + id + ", masterPhoneNum=" + masterPhoneNum + ", studentPhoneNum="
				+ studentPhoneNum + ", time=" + time + ", descStr=" + descStr + ", gold=" + gold + ", isAward="
				+ isAward + ", createDate=" + createDate + ", createdBy=" + createdBy + ", updateDate=" + updateDate
				+ ", updatedBy=" + updatedBy + "]";
	}
}
