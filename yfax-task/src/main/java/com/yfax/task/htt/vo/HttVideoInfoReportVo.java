package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttVideoInfoReportVo implements Serializable {

	private static final long serialVersionUID = 3461505427564992113L;

	private String id; // 主键
	private String satisticDate;// 日期
	private String activeUserNum;// 活跃用户
	private String videoUserNum;// 看视频用户数
	private String videoRate;// 看视频率
	private String totalVideoNum;// 看视频次数
	private String avgVideoNum;// 人均看视频次数
	private String totalVideoGold;// 看视频奖励
	private String avgVideoGold;// 人均看视频奖励
	private String avgVideoGoldTimes;// 次均看视频奖励

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

	public String getVideoUserNum() {
		return videoUserNum;
	}

	public void setVideoUserNum(String videoUserNum) {
		this.videoUserNum = videoUserNum;
	}

	public String getVideoRate() {
		return videoRate;
	}

	public void setVideoRate(String videoRate) {
		this.videoRate = videoRate;
	}

	public String getTotalVideoNum() {
		return totalVideoNum;
	}

	public void setTotalVideoNum(String totalVideoNum) {
		this.totalVideoNum = totalVideoNum;
	}

	public String getAvgVideoNum() {
		return avgVideoNum;
	}

	public void setAvgVideoNum(String avgVideoNum) {
		this.avgVideoNum = avgVideoNum;
	}

	public String getTotalVideoGold() {
		return totalVideoGold;
	}

	public void setTotalVideoGold(String totalVideoGold) {
		this.totalVideoGold = totalVideoGold;
	}

	public String getAvgVideoGold() {
		return avgVideoGold;
	}

	public void setAvgVideoGold(String avgVideoGold) {
		this.avgVideoGold = avgVideoGold;
	}

	public String getAvgVideoGoldTimes() {
		return avgVideoGoldTimes;
	}

	public void setAvgVideoGoldTimes(String avgVideoGoldTimes) {
		this.avgVideoGoldTimes = avgVideoGoldTimes;
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