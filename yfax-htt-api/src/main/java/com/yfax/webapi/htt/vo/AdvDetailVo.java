package com.yfax.webapi.htt.vo;

import java.io.Serializable;

/**
 * 广告内容详情配置表
 * @author Minbo
 */
public class AdvDetailVo implements Serializable{

	private static final long serialVersionUID = -8368198743598377549L;

	private String id;			//主键
	
	private String advTitle;		//标题
	private String advImgs;		//配图url
	private String advUrl;		//跳转链接url
	
	private Integer showRate;	//出现概率
	private Integer orderNum;	//排序编号
	private Integer status;		//记录状态
	
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
	private String advFkid;		//广告渠道id
	private String tagName;		//新闻Tab标识
	
	private String advFlag;		//唯一标识
	private String advName;		//渠道名称
	
	public String getAdvFlag() {
		return advFlag;
	}
	public void setAdvFlag(String advFlag) {
		this.advFlag = advFlag;
	}
	public String getAdvName() {
		return advName;
	}
	public void setAdvName(String advName) {
		this.advName = advName;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getAdvFkid() {
		return advFkid;
	}
	public void setAdvFkid(String advFkid) {
		this.advFkid = advFkid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAdvTitle() {
		return advTitle;
	}
	public void setAdvTitle(String advTitle) {
		this.advTitle = advTitle;
	}
	public String getAdvImgs() {
		return advImgs;
	}
	public void setAdvImgs(String advImgs) {
		this.advImgs = advImgs;
	}
	public String getAdvUrl() {
		return advUrl;
	}
	public void setAdvUrl(String advUrl) {
		this.advUrl = advUrl;
	}
	public Integer getShowRate() {
		return showRate;
	}
	public void setShowRate(Integer showRate) {
		this.showRate = showRate;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
