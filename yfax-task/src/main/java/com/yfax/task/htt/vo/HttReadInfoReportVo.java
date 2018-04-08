package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttReadInfoReportVo implements Serializable {

	private static final long serialVersionUID = 3461505427564992113L;

	private String id; // 主键
	private String satisticDate;// 日期
	private String activeUserNum;// 活跃用户
	private String readUserNum;// 阅读用户
	private String readRate;// 阅读率
	private String totalReadNum;// 阅读次数
	private String avgReadNum;// 人均阅读次数
	private String totalReadGold;// 阅读奖励
	private String avgReadGold;// 人均阅读奖励
	private String avgReadGoldTimes;// 次均阅读奖励

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

	public String getSatisticDate() {
		return satisticDate;
	}

	public void setSatisticDate(String satisticDate) {
		this.satisticDate = satisticDate;
	}

	public String getActiveUserNum() {
		return activeUserNum;
	}

	public void setActiveUserNum(String activeUserNum) {
		this.activeUserNum = activeUserNum;
	}

	public String getReadUserNum() {
		return readUserNum;
	}

	public void setReadUserNum(String readUserNum) {
		this.readUserNum = readUserNum;
	}

	public String getReadRate() {
		return readRate;
	}

	public void setReadRate(String readRate) {
		this.readRate = readRate;
	}

	public String getTotalReadNum() {
		return totalReadNum;
	}

	public void setTotalReadNum(String totalReadNum) {
		this.totalReadNum = totalReadNum;
	}

	public String getAvgReadNum() {
		return avgReadNum;
	}

	public void setAvgReadNum(String avgReadNum) {
		this.avgReadNum = avgReadNum;
	}

	public String getTotalReadGold() {
		return totalReadGold;
	}

	public void setTotalReadGold(String totalReadGold) {
		this.totalReadGold = totalReadGold;
	}

	public String getAvgReadGold() {
		return avgReadGold;
	}

	public void setAvgReadGold(String avgReadGold) {
		this.avgReadGold = avgReadGold;
	}

	public String getAvgReadGoldTimes() {
		return avgReadGoldTimes;
	}

	public void setAvgReadGoldTimes(String avgReadGoldTimes) {
		this.avgReadGoldTimes = avgReadGoldTimes;
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