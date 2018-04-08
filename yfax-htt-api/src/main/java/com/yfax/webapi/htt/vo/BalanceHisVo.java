package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class BalanceHisVo implements Serializable{
	
	private static final long serialVersionUID = 7291644169223338908L;
	
	private String id;			//主键
	private String phoneNum;		//手机号码
	private Integer balanceType;	//兑换类型
	private String balanceName;	//兑换类型名
	private String gold;			//兑换金币值
	private String balance;		//获得零钱值
	private String rate;			//当前汇率值
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	public String getBalanceName() {
		return balanceName;
	}
	public void setBalanceName(String balanceName) {
		this.balanceName = balanceName;
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
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
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
	public Integer getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(Integer balanceType) {
		this.balanceType = balanceType;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
}
