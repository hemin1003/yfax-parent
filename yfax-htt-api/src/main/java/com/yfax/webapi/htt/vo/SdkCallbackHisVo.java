package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class SdkCallbackHisVo implements Serializable{
	
	private static final long serialVersionUID = 7291644169223338908L;
	
	private Integer orderid;		//订单号
	private Integer cid;			//渠道编号
	private String cuid;			//合作方用户标示，即用户ID
	private String devid;		//用户设备码
	private String adname;		//任务名称
	private String time;			//时间戳
	private String timeDate;		//时间戳解析的时间
	private Integer points;		//积分
	private Integer atype;		//积分类型
	private String sign;			//对方接口签名
	private String sip;			//对方服务器IP
	private String createDate;
	private String updateDate;
	private String mySign;		//本系统签名
	private String sdkType;		//第三方平台类型标识
	private Integer result;		//验证结果
	
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getSip() {
		return sip;
	}
	public void setSip(String sip) {
		this.sip = sip;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(String timeDate) {
		this.timeDate = timeDate;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getAtype() {
		return atype;
	}
	public void setAtype(Integer atype) {
		this.atype = atype;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
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
	public String getMySign() {
		return mySign;
	}
	public void setMySign(String mySign) {
		this.mySign = mySign;
	}
	public String getSdkType() {
		return sdkType;
	}
	public void setSdkType(String sdkType) {
		this.sdkType = sdkType;
	}
}
