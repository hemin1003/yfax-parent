package com.yfax.webapi.htt.vo;

import java.io.Serializable;

/**
 * 兑换提现记录
 * @author Minbo
 */
public class WithdrawHisVo implements Serializable {

	private static final long serialVersionUID = 6344076178525221726L;
	
	private String id;
	private String phoneNum;
	private Integer withdrawType;
	private String withdrawName;
	private String name;
	private String account;
	private String gold;
	private String income;
	private String withdrawTime;
	private String status;
	private Integer statusType;
	private String createDate;
	private String updateDate;
	private String ownPhoneNum;
	
	public String getOwnPhoneNum() {
		return ownPhoneNum;
	}
	public void setOwnPhoneNum(String ownPhoneNum) {
		this.ownPhoneNum = ownPhoneNum;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
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
	public Integer getWithdrawType() {
		return withdrawType;
	}
	public void setWithdrawType(Integer withdrawType) {
		this.withdrawType = withdrawType;
	}
	public String getWithdrawName() {
		return withdrawName;
	}
	public void setWithdrawName(String withdrawName) {
		this.withdrawName = withdrawName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getWithdrawTime() {
		return withdrawTime;
	}
	public void setWithdrawTime(String withdrawTime) {
		this.withdrawTime = withdrawTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getStatusType() {
		return statusType;
	}
	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}