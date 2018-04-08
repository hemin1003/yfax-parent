package com.yfax.webapi.htt.vo;

import java.io.Serializable;
import java.util.Map;

public class UserTaskVo implements Serializable{

	private static final long serialVersionUID = -3921951168284933180L;
	
	private String id;			//主键
	private String phoneNum;		//手机号码
	
	private Integer taskType;	//任务类型
	private String taskId;		//任务ID
	private String taskTitle;	//任务标题
	private String taskGold;		//任务金币
	private String taskDesc;		//任务描述
	private Integer taskTime;	//任务累积时长
	private Integer status;		//任务状态
	private Integer isDeleted;	//是否过时
	
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	private Integer paramDailyCount;	//每日任务次数上限
	private Integer paramInterval;	//任务间隔时间
	
	private String currentTime;
	
	private Integer version;			//记录版本号-并发解决方案
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getParamDailyCount() {
		return paramDailyCount;
	}
	public void setParamDailyCount(Integer paramDailyCount) {
		this.paramDailyCount = paramDailyCount;
	}
	public Integer getParamInterval() {
		return paramInterval;
	}
	public void setParamInterval(Integer paramInterval) {
		this.paramInterval = paramInterval;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskGold() {
		return taskGold;
	}
	public void setTaskGold(String taskGold) {
		this.taskGold = taskGold;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public Integer getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(Integer taskTime) {
		this.taskTime = taskTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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