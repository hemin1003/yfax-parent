package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttAppUserVo implements Serializable {
	
	private static final long serialVersionUID = 5875750673354485490L;
	
	private String phoneNum;		//手机号码
	private String userPwd;		//密码
	private String gold;			//金币余额
	private String balance;		//零钱余额
	private String userName;		//姓名
	private String address;		//住址
	private String wechat;		//微信号
	private String qq;			//QQ号
	private String email;		//邮箱地址
	private String registerDate;	//注册时间
	private String lastLoginDate;	//最后一次登录时间
	private String updateDate;
	private Integer blackList;	//是否黑名单用户
	private Integer shareCount;	//已分享次数
	private Integer firstShare;	//首次分享标识
	private Integer firstInvite;	//首次邀请成功标识
	private Integer firstRead;	//首次阅读标识
	private Integer students;	//徒弟数
	private Integer dailyCheckIn;	//今日签到标识
	private Integer continueCheckIn;	//连续签到标识
	private Integer dailyReadAward; //每日累积阅读时长奖励
	private Integer dailyVideoAward;//每日累积视频时长奖励标识
	private Integer timeAward;		//时段奖励
//	private Object tabArticle;		//文章标签数据
//	private Object tabVideo;			//视频标签数据
	private String inviteCode;		//邀请码
	private Integer isMaster;		//是否已有师傅标识
	private Integer isMasterFinished; //师傅分步奖励全部完成标识
	private Integer isInviteStep; 	//每次分步邀请奖励标识
	private Integer isFirstRead;		//新人任务-三分钟阅读任务标识
	private Integer isDailyRead;		//日常任务-五分钟阅读任务标识
	
	private String registerIp;		//注册IP
	private Integer accountStatus;	//账户状态
	private Integer isBindWechat;	//是否已绑定微信
	private Integer isBindPhoneNum;	//是否已绑定手机号码
	private Integer isBindAlipay;	//是否已绑定支付宝
	private Integer isBindTelPhoneNum; //是否绑定话费号码
	private String realPhoneNum;		//微信登录后绑定的手机号码
	private Integer isDaka;			//是否是大咖账户
	private String imei;				//注册imei码
	private String msgCode;			//注册短信码
	private String channel;			//APP来源渠道
	private String sourceAppId;		//新闻渠道appId
	private Integer source;			//新闻渠道标识
	private Long totalGold;			//总金币
	private Long studentsGold;		//徒弟提供的总收益金币
	private Integer isDailyVideo;	//日常任务-五分钟视频任务标识
	private String versionCode;		//版本号
	private String blackRuleDesc;	//拉黑匹配策略说明
	
	public Integer getIsDailyVideo() {
		return isDailyVideo;
	}
	public void setIsDailyVideo(Integer isDailyVideo) {
		this.isDailyVideo = isDailyVideo;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getBlackRuleDesc() {
		return blackRuleDesc;
	}
	public void setBlackRuleDesc(String blackRuleDesc) {
		this.blackRuleDesc = blackRuleDesc;
	}
	public Long getTotalGold() {
		return totalGold;
	}
	public void setTotalGold(Long totalGold) {
		this.totalGold = totalGold;
	}
	public Long getStudentsGold() {
		return studentsGold;
	}
	public void setStudentsGold(Long studentsGold) {
		this.studentsGold = studentsGold;
	}
	public Integer getIsDailyRead() {
		return isDailyRead;
	}
	public void setIsDailyRead(Integer isDailyRead) {
		this.isDailyRead = isDailyRead;
	}
	public Integer getTimeAward() {
		return timeAward;
	}
	public void setTimeAward(Integer timeAward) {
		this.timeAward = timeAward;
	}
//	public Object getTabArticle() {
//		return tabArticle;
//	}
//	public void setTabArticle(Object tabArticle) {
//		this.tabArticle = tabArticle;
//	}
//	public Object getTabVideo() {
//		return tabVideo;
//	}
//	public void setTabVideo(Object tabVideo) {
//		this.tabVideo = tabVideo;
//	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public Integer getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
	}
	public Integer getIsMasterFinished() {
		return isMasterFinished;
	}
	public void setIsMasterFinished(Integer isMasterFinished) {
		this.isMasterFinished = isMasterFinished;
	}
	public Integer getIsInviteStep() {
		return isInviteStep;
	}
	public void setIsInviteStep(Integer isInviteStep) {
		this.isInviteStep = isInviteStep;
	}
	public Integer getDailyVideoAward() {
		return dailyVideoAward;
	}
	public void setDailyVideoAward(Integer dailyVideoAward) {
		this.dailyVideoAward = dailyVideoAward;
	}
	public Integer getContinueCheckIn() {
		return continueCheckIn;
	}
	public void setContinueCheckIn(Integer continueCheckIn) {
		this.continueCheckIn = continueCheckIn;
	}
	public Integer getDailyReadAward() {
		return dailyReadAward;
	}
	public void setDailyReadAward(Integer dailyReadAward) {
		this.dailyReadAward = dailyReadAward;
	}
	public Integer getDailyCheckIn() {
		return dailyCheckIn;
	}
	public void setDailyCheckIn(Integer dailyCheckIn) {
		this.dailyCheckIn = dailyCheckIn;
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
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getBlackList() {
		return blackList;
	}
	public void setBlackList(Integer blackList) {
		this.blackList = blackList;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
	public Integer getFirstShare() {
		return firstShare;
	}
	public void setFirstShare(Integer firstShare) {
		this.firstShare = firstShare;
	}
	public Integer getFirstInvite() {
		return firstInvite;
	}
	public void setFirstInvite(Integer firstInvite) {
		this.firstInvite = firstInvite;
	}
	public Integer getFirstRead() {
		return firstRead;
	}
	public void setFirstRead(Integer firstRead) {
		this.firstRead = firstRead;
	}
	public Integer getStudents() {
		return students;
	}
	public void setStudents(Integer students) {
		this.students = students;
	}
	public Integer getIsFirstRead() {
		return isFirstRead;
	}
	public void setIsFirstRead(Integer isFirstRead) {
		this.isFirstRead = isFirstRead;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Integer getIsBindWechat() {
		return isBindWechat;
	}
	public void setIsBindWechat(Integer isBindWechat) {
		this.isBindWechat = isBindWechat;
	}
	public Integer getIsBindPhoneNum() {
		return isBindPhoneNum;
	}
	public void setIsBindPhoneNum(Integer isBindPhoneNum) {
		this.isBindPhoneNum = isBindPhoneNum;
	}
	public Integer getIsBindAlipay() {
		return isBindAlipay;
	}
	public void setIsBindAlipay(Integer isBindAlipay) {
		this.isBindAlipay = isBindAlipay;
	}
	public Integer getIsBindTelPhoneNum() {
		return isBindTelPhoneNum;
	}
	public void setIsBindTelPhoneNum(Integer isBindTelPhoneNum) {
		this.isBindTelPhoneNum = isBindTelPhoneNum;
	}
	public String getRealPhoneNum() {
		return realPhoneNum;
	}
	public void setRealPhoneNum(String realPhoneNum) {
		this.realPhoneNum = realPhoneNum;
	}
	public Integer getIsDaka() {
		return isDaka;
	}
	public void setIsDaka(Integer isDaka) {
		this.isDaka = isDaka;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSourceAppId() {
		return sourceAppId;
	}
	public void setSourceAppId(String sourceAppId) {
		this.sourceAppId = sourceAppId;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "AppUserVo [phoneNum=" + phoneNum + ", userPwd=" + userPwd + ", gold=" + gold + ", balance=" + balance
				+ ", userName=" + userName + ", address=" + address + ", wechat=" + wechat + ", qq=" + qq + ", email="
				+ email + ", registerDate=" + registerDate + ", lastLoginDate=" + lastLoginDate + ", updateDate="
				+ updateDate + ", blackList=" + blackList + ", shareCount=" + shareCount + ", firstShare=" + firstShare
				+ ", firstInvite=" + firstInvite + ", firstRead=" + firstRead + ", students=" + students
				+ ", dailyCheckIn=" + dailyCheckIn + "]";
	}
}
