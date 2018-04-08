package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class IncomeSetVo implements Serializable{
	
	private static final long serialVersionUID = 4577997978188778374L;
	
	private String id;			//主键
	private String income;		//提现金额
	private Integer status;		//记录状态，1=已下线，2=已上线
	private Integer saleFlag;	//销售标识，1=下线；2=上线；3=缺货
	private Integer maxNum;		//每日最大数量
	private Integer goodType;	//商品类别
	private String goodName;		//商品类别名称
	private String gold;			//兑换金币值
	
//	private String createDate;	//创建时间
//	private String createdBy;	//创建人
//	private String updateDate;	//更新时间
//	private String updatedBy;	//更新人
	
	//动态字段
	private Long leftNum;		//每日剩余数量
	
	public Long getLeftNum() {
		return leftNum;
	}
	public void setLeftNum(Long leftNum) {
		this.leftNum = leftNum;
	}
	public Integer getGoodType() {
		return goodType;
	}
	public void setGoodType(Integer goodType) {
		this.goodType = goodType;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	public Integer getSaleFlag() {
		return saleFlag;
	}
	public void setSaleFlag(Integer saleFlag) {
		this.saleFlag = saleFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public String getCreateDate() {
//		return createDate;
//	}
//	public void setCreateDate(String createDate) {
//		this.createDate = createDate;
//	}
//	public String getUpdateDate() {
//		return updateDate;
//	}
//	public void setUpdateDate(String updateDate) {
//		this.updateDate = updateDate;
//	}
//	public String getCreatedBy() {
//		return createdBy;
//	}
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//	public String getUpdatedBy() {
//		return updatedBy;
//	}
//	public void setUpdatedBy(String updatedBy) {
//		this.updatedBy = updatedBy;
//	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
