package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttHolidayActivityInviteUserRankingVo implements Serializable {

	private static final long serialVersionUID = 5875750673354485490L;

	private String id;
	private String date;// 统计日期
	private String phoneNum;// 唯一id
	private String userName;// 用户昵称
	private String realNewUsers;// 邀请有效徒弟数
	private Integer ranking;// 用户排名
	private String createDate;// 创建时间
	private String createdBy;// 创建人
	private String updateDate;// 更新时间
	private String updatedBy;// 更新人

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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRealNewUsers() {
		return realNewUsers;
	}

	public void setRealNewUsers(String realNewUsers) {
		this.realNewUsers = realNewUsers;
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

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
