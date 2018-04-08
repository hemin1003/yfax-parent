package com.yfax.task.htt.vo;

import java.io.Serializable;

/**
 * 提现审核用户信息表
 * @author Minbo
 */
public class HttWithdrawProveUserVo implements Serializable {

	private static final long serialVersionUID = 6344076178525221721L;

	private String id;
	private String phoneNum;			//账号
	private String userPwd;			//密码md5
	private String userPwdCount;		//同密码md5账号数
	private String channel;			//渠道
	private String deviceName;		//设备名称
	private String deviceNameCount;	//同设备名账号数
	private String wechatNickname;	//微信名称
	private String registerIp;		//注册IP
	private String registerIpCount;	//同注册IP账号数
	private String ownPhoneNum;		//手机号
	private String ip;				//内网IP
	private String ipCount;			//同内网IP账号数
	private String alipayAccount;	//支付宝账号
	private String coordinate;		//经纬度
	private String coordinateCount;	//同经纬度账号数
	private String imei;				//imei
	private String location;			//地理位置
	private String locationCount;	//同地理位置账号数
	private String registerDate;		//注册时间
	private String wifiMac;			//wifi名称（wifimac）
	private String wifiMacCount;		//同wifimac账号数
	private Integer accountStatus;	//账号状态
	private String installedListMdCode;			//安装列表md5
	private String installedListMdCodeCount;		//同安装列表账号数
	private Integer isCheatedApp;				//是否安装作弊器app
	private String masterPhoneNum;				//师傅账号
	private Integer students;					//徒弟数
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;
	private String project;			//项目标识
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
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
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserPwdCount() {
		return userPwdCount;
	}
	public void setUserPwdCount(String userPwdCount) {
		this.userPwdCount = userPwdCount;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceNameCount() {
		return deviceNameCount;
	}
	public void setDeviceNameCount(String deviceNameCount) {
		this.deviceNameCount = deviceNameCount;
	}
	public String getWechatNickname() {
		return wechatNickname;
	}
	public void setWechatNickname(String wechatNickname) {
		this.wechatNickname = wechatNickname;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getRegisterIpCount() {
		return registerIpCount;
	}
	public void setRegisterIpCount(String registerIpCount) {
		this.registerIpCount = registerIpCount;
	}
	public String getOwnPhoneNum() {
		return ownPhoneNum;
	}
	public void setOwnPhoneNum(String ownPhoneNum) {
		this.ownPhoneNum = ownPhoneNum;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIpCount() {
		return ipCount;
	}
	public void setIpCount(String ipCount) {
		this.ipCount = ipCount;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getCoordinateCount() {
		return coordinateCount;
	}
	public void setCoordinateCount(String coordinateCount) {
		this.coordinateCount = coordinateCount;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationCount() {
		return locationCount;
	}
	public void setLocationCount(String locationCount) {
		this.locationCount = locationCount;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	public String getWifiMacCount() {
		return wifiMacCount;
	}
	public void setWifiMacCount(String wifiMacCount) {
		this.wifiMacCount = wifiMacCount;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getInstalledListMdCode() {
		return installedListMdCode;
	}
	public void setInstalledListMdCode(String installedListMdCode) {
		this.installedListMdCode = installedListMdCode;
	}
	public String getInstalledListMdCodeCount() {
		return installedListMdCodeCount;
	}
	public void setInstalledListMdCodeCount(String installedListMdCodeCount) {
		this.installedListMdCodeCount = installedListMdCodeCount;
	}
	public Integer getIsCheatedApp() {
		return isCheatedApp;
	}
	public void setIsCheatedApp(Integer isCheatedApp) {
		this.isCheatedApp = isCheatedApp;
	}
	public String getMasterPhoneNum() {
		return masterPhoneNum;
	}
	public void setMasterPhoneNum(String masterPhoneNum) {
		this.masterPhoneNum = masterPhoneNum;
	}
	public Integer getStudents() {
		return students;
	}
	public void setStudents(Integer students) {
		this.students = students;
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
}