package com.yfax.task.htt.vo;

import java.io.Serializable;

/**
 * 提现审核用户关联表
 * @author Minbo
 */
public class HttWithdrawProveUserLinkVo implements Serializable {

	private static final long serialVersionUID = 6344076178525221721L;

	private String id;
	private String pid;				//关联id
	private String phoneNum;			//账号
	private String wechatNickname;	//微信名称
	private String ownPhoneNum;		//手机号
	private String alipayAccount;	//支付宝账号
	private String imei;				//imei
	private String registerDate;		//注册时间
	private Integer accountStatus;	//账号状态
	private Integer students;		//徒弟数
	private String masterPhoneNum;	//师傅账号
	private Integer isCheatedApp;	//是否安装作弊器app
	private String deviceName;		//设备名称
	private String registerIp;		//注册IP
	private String ip;				//内网IP
	private String coordinate;		//经纬度
	private String location;			//地理位置
	private String wifi;			//wifi名称（wifimac）
	private String installedList;	//安装列表
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;
	private String project;			//项目标识
	private Integer dataFlag;		//数据标识，1=师傅数据，2=徒弟数据，3=兄弟数据
	
	public Integer getDataFlag() {
		return dataFlag;
	}
	public void setDataFlag(Integer dataFlag) {
		this.dataFlag = dataFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getWechatNickname() {
		return wechatNickname;
	}
	public void setWechatNickname(String wechatNickname) {
		this.wechatNickname = wechatNickname;
	}
	public String getOwnPhoneNum() {
		return ownPhoneNum;
	}
	public void setOwnPhoneNum(String ownPhoneNum) {
		this.ownPhoneNum = ownPhoneNum;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Integer getStudents() {
		return students;
	}
	public void setStudents(Integer students) {
		this.students = students;
	}
	public String getMasterPhoneNum() {
		return masterPhoneNum;
	}
	public void setMasterPhoneNum(String masterPhoneNum) {
		this.masterPhoneNum = masterPhoneNum;
	}
	public Integer getIsCheatedApp() {
		return isCheatedApp;
	}
	public void setIsCheatedApp(Integer isCheatedApp) {
		this.isCheatedApp = isCheatedApp;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	public String getInstalledList() {
		return installedList;
	}
	public void setInstalledList(String installedList) {
		this.installedList = installedList;
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
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
}