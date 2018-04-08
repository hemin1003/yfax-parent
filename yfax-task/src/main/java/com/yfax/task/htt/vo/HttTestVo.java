package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttTestVo implements Serializable {

	private static final long serialVersionUID = -5349993237162943212L;

	private String phoneNum;		//手机号码
	private String aid;
	private String bid;
	private String time;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "HttTestVo [aid=" + aid + ", bid=" + bid + "]";
	}
}