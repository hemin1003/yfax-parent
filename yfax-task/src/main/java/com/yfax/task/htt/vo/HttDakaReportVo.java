package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttDakaReportVo implements Serializable {

	private static final long serialVersionUID = 3461505427564992113L;

	private String id; // 主键
	private String date;// 统计日期
	private String channel;// 渠道
	private String newUsers;// 新增注册
	private String allIncome;// 大咖昨日总收入
	private String readUserRate;// 阅读用户比例
	private String avgReadTime;// 人均阅读时长（分）
	private String inviteRate;// 邀请率

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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getNewUsers() {
		return newUsers;
	}
	public void setNewUsers(String newUsers) {
		this.newUsers = newUsers;
	}
	public String getAllIncome() {
		return allIncome;
	}
	public void setAllIncome(String allIncome) {
		this.allIncome = allIncome;
	}
	public String getReadUserRate() {
		return readUserRate;
	}
	public void setReadUserRate(String readUserRate) {
		this.readUserRate = readUserRate;
	}
	public String getAvgReadTime() {
		return avgReadTime;
	}
	public void setAvgReadTime(String avgReadTime) {
		this.avgReadTime = avgReadTime;
	}
	public String getInviteRate() {
		return inviteRate;
	}
	public void setInviteRate(String inviteRate) {
		this.inviteRate = inviteRate;
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