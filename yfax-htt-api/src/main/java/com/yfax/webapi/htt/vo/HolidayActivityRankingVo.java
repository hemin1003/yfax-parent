package com.yfax.webapi.htt.vo;

import java.io.Serializable;

/**
 * 寒假活动-用户邀请排名
 * 
 * @author Minbo
 */
public class HolidayActivityRankingVo implements Serializable {

	private static final long serialVersionUID = -8368198743598377549L;

	// private String id;
	private String date;// 统计日期
	private String phoneNum;// 唯一id
	private String realNewUsers;// 邀请有效徒弟数
	private Integer ranking;// 用户当前排名
	// private String createDate;// 创建时间
	// private String createdBy;// 创建人
	// private String updateDate;// 更新时间
	// private String updatedBy;// 更新人

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

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

}
