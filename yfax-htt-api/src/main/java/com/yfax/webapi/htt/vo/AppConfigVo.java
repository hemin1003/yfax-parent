package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class AppConfigVo implements Serializable{
	
	private static final long serialVersionUID = 3574886939087534243L;

//	private String id;			//主键
	
	private Integer goldLimit;			//每日阅读金币领取上限
	private String goldRange;			//阅读金币随机范围和概率值
	private Integer timeDuration;		//时段奖励间隔时长，单位小时
	private Integer timeGold;			//时段奖励金币值
	private Integer videoGoldLimit;		//每日视频金币领取上限
	private String videoGoldRange;		//视频金币随机范围和概率值
	
	private Object params;				//动态参数字段
	private String readRuleDesc;			//阅读收益说明
	
	private String androidUrl;			//安卓分享下载链接Url
	private String iphoneUrl;			//苹果分享下载链接Url
	private Integer readAwardLimit;		//阅读奖励次数限制-提示验证码
	private String tyDownloadUrl;		//天翼包下载地址
	
	private Integer readAwardRepeat;		//单片文章可获得奖励次数
	private Integer videoAwardRepeat;	//单个视频可获得奖励次数
	
	private String xbAndroidDownloadUrl;	//学吧课堂安卓包下载地址url
	private String xbIosDownloadUrl;		//学吧课堂苹果下载地址url
	
//	private String createDate;	//创建时间
//	private String createdBy;	//创建人
//	private String updateDate;	//更新时间
//	private String updatedBy;	//更新人

	public String getVideoGoldRange() {
		return videoGoldRange;
	}
	public String getXbAndroidDownloadUrl() {
		return xbAndroidDownloadUrl;
	}
	public void setXbAndroidDownloadUrl(String xbAndroidDownloadUrl) {
		this.xbAndroidDownloadUrl = xbAndroidDownloadUrl;
	}
	public String getXbIosDownloadUrl() {
		return xbIosDownloadUrl;
	}
	public void setXbIosDownloadUrl(String xbIosDownloadUrl) {
		this.xbIosDownloadUrl = xbIosDownloadUrl;
	}
	public Integer getReadAwardRepeat() {
		return readAwardRepeat;
	}
	public void setReadAwardRepeat(Integer readAwardRepeat) {
		this.readAwardRepeat = readAwardRepeat;
	}
	public Integer getVideoAwardRepeat() {
		return videoAwardRepeat;
	}
	public void setVideoAwardRepeat(Integer videoAwardRepeat) {
		this.videoAwardRepeat = videoAwardRepeat;
	}
	public String getTyDownloadUrl() {
		return tyDownloadUrl;
	}
	public void setTyDownloadUrl(String tyDownloadUrl) {
		this.tyDownloadUrl = tyDownloadUrl;
	}
	public Integer getReadAwardLimit() {
		return readAwardLimit;
	}
	public void setReadAwardLimit(Integer readAwardLimit) {
		this.readAwardLimit = readAwardLimit;
	}
	public String getAndroidUrl() {
		return androidUrl;
	}
	public void setAndroidUrl(String androidUrl) {
		this.androidUrl = androidUrl;
	}
	public String getIphoneUrl() {
		return iphoneUrl;
	}
	public void setIphoneUrl(String iphoneUrl) {
		this.iphoneUrl = iphoneUrl;
	}
	public void setVideoGoldRange(String videoGoldRange) {
		this.videoGoldRange = videoGoldRange;
	}
	public Integer getTimeGold() {
		return timeGold;
	}
	public void setTimeGold(Integer timeGold) {
		this.timeGold = timeGold;
	}
	public String getReadRuleDesc() {
		return readRuleDesc;
	}
	public void setReadRuleDesc(String readRuleDesc) {
		this.readRuleDesc = readRuleDesc;
	}
	public Object getParams() {
		return params;
	}
	public void setParams(Object params) {
		this.params = params;
	}
	public Integer getVideoGoldLimit() {
		return videoGoldLimit;
	}
	public void setVideoGoldLimit(Integer videoGoldLimit) {
		this.videoGoldLimit = videoGoldLimit;
	}
	public Integer getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(Integer timeDuration) {
		this.timeDuration = timeDuration;
	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
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
	public Integer getGoldLimit() {
		return goldLimit;
	}
	public void setGoldLimit(Integer goldLimit) {
		this.goldLimit = goldLimit;
	}
	public String getGoldRange() {
		return goldRange;
	}
	public void setGoldRange(String goldRange) {
		this.goldRange = goldRange;
	}
}
