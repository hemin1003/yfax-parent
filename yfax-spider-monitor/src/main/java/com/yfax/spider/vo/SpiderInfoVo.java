package com.yfax.spider.vo;

import java.io.Serializable;

public class SpiderInfoVo implements Serializable{
	
	private static final long serialVersionUID = 3461505427564992113L;
	
	private String id;			//主键
	private String domain;		//域名值
	private String domainName;	//域名名称
	private String date;			//日期
	private Integer newsNum;		//新闻数量
	private String createDate;	//创建时间
	private String createdBy;	//创建人
	private String updateDate;	//更新时间
	private String updatedBy;	//更新人
	
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getNewsNum() {
		return newsNum;
	}
	public void setNewsNum(Integer newsNum) {
		this.newsNum = newsNum;
	}
	@Override
	public String toString() {
		return "SpiderInfoVo [id=" + id + ", domain=" + domain + ", domainName=" + domainName + ", date=" + date
				+ ", newsNum=" + newsNum + ", createDate=" + createDate + ", createdBy=" + createdBy + ", updateDate="
				+ updateDate + ", updatedBy=" + updatedBy + "]";
	}
	
}
