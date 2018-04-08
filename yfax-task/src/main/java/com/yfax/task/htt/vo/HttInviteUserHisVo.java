package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttInviteUserHisVo implements Serializable{
	
	private static final long serialVersionUID = 4755663221270118077L;
	
	private String id;			//主键
	private String masterPhoneNum;	//师傅手机号码
	private String studentPhoneNum;	//徒弟手机号码
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	private String masterRegisterIp; 	//师傅注册IP
	private String studentRegisterIp; 	//徒弟注册IP
	private Integer registerType;		//注册类型(1=直接邀请码注册；2=登录后再邀请码绑定；3=大咖用户IP注册)
	private String masterImei;			//师傅IMEI码
	private String studentImei;			//徒弟IMEI码
	private String studentMsgCode;		//徒弟注册码
	private Long studentsGold;			//徒弟提供的总收益金币
	
	public Long getStudentsGold() {
		return studentsGold;
	}
	public void setStudentsGold(Long studentsGold) {
		this.studentsGold = studentsGold;
	}
	public String getStudentMsgCode() {
		return studentMsgCode;
	}
	public void setStudentMsgCode(String studentMsgCode) {
		this.studentMsgCode = studentMsgCode;
	}
	public String getMasterImei() {
		return masterImei;
	}
	public void setMasterImei(String masterImei) {
		this.masterImei = masterImei;
	}
	public String getStudentImei() {
		return studentImei;
	}
	public void setStudentImei(String studentImei) {
		this.studentImei = studentImei;
	}
	public Integer getRegisterType() {
		return registerType;
	}
	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}
	public String getMasterRegisterIp() {
		return masterRegisterIp;
	}
	public void setMasterRegisterIp(String masterRegisterIp) {
		this.masterRegisterIp = masterRegisterIp;
	}
	public String getStudentRegisterIp() {
		return studentRegisterIp;
	}
	public void setStudentRegisterIp(String studentRegisterIp) {
		this.studentRegisterIp = studentRegisterIp;
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
		return "HttInviteUserHisVo [id=" + id + ", masterPhoneNum=" + masterPhoneNum + ", studentPhoneNum="
				+ studentPhoneNum + ", createDate=" + createDate + ", createdBy=" + createdBy + ", updateDate="
				+ updateDate + ", updatedBy=" + updatedBy + ", masterRegisterIp=" + masterRegisterIp
				+ ", studentRegisterIp=" + studentRegisterIp + ", registerType=" + registerType + ", masterImei="
				+ masterImei + ", studentImei=" + studentImei + ", studentMsgCode=" + studentMsgCode + ", studentsGold="
				+ studentsGold + "]";
	}
}
