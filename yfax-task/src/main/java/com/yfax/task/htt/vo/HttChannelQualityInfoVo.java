package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttChannelQualityInfoVo implements Serializable {

	private static final long serialVersionUID = 3461505427564992113L;

	private String id; // 主键
	private String registerDate;// 注册日期
	private String channel;// 渠道
	private String newUsers;// 新增注册
	private String totalReadingTimes;// 阅读文章总数
	private String avgReadingTimes;// 人均阅读文章数
	private String avgReadingMinute;// 人均阅读时长（分）
	private String realNewUsers;// 真新增注册
	private String readRatio;// 阅读比例
	private String totalGoldMoreThanOneRatio;// 累计奖励大于1元用户比例

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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
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

	public String getTotalReadingTimes() {
		return totalReadingTimes;
	}

	public void setTotalReadingTimes(String totalReadingTimes) {
		this.totalReadingTimes = totalReadingTimes;
	}

	public String getAvgReadingTimes() {
		return avgReadingTimes;
	}

	public void setAvgReadingTimes(String avgReadingTimes) {
		this.avgReadingTimes = avgReadingTimes;
	}

	public String getAvgReadingMinute() {
		return avgReadingMinute;
	}

	public void setAvgReadingMinute(String avgReadingMinute) {
		this.avgReadingMinute = avgReadingMinute;
	}

	public String getRealNewUsers() {
		return realNewUsers;
	}

	public void setRealNewUsers(String realNewUsers) {
		this.realNewUsers = realNewUsers;
	}

	public String getReadRatio() {
		return readRatio;
	}

	public void setReadRatio(String readRatio) {
		this.readRatio = readRatio;
	}

	public String getTotalGoldMoreThanOneRatio() {
		return totalGoldMoreThanOneRatio;
	}

	public void setTotalGoldMoreThanOneRatio(String totalGoldMoreThanOneRatio) {
		this.totalGoldMoreThanOneRatio = totalGoldMoreThanOneRatio;
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