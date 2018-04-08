package com.yfax.utils;

public enum ResultCode {
	
	/** 成功 */
	SUCCESS("200", "成功"),
	
	/** 数据为空 */
	SUCCESS_NO_DATA("201", "数据为空"),
	
	/** 用户不存在 */
	SUCCESS_NO_USER("202", "用户不存在"),
	
	/** 今天的量已经跑光了 */
	SUCCESS_ALL_GONE("203", "今天的量已经跑光了"),
	
	/** 失败，请重试 */
	SUCCESS_FAIL("204", "失败，请重试"),
	
	/** 失败，余额不足 */
	SUCCESS_NOT_ENOUGH("205", "失败，余额不足"),
	
	/** 不支持此操作 */
	SUCCESS_NOT_RIGHT("206", "不支持此操作"),
	
	/** 同一任务只能做一次 */
	SUCCESS_JUST_ONE("207", "同一任务只能做一次"),
	
	/** 重复记录 */
	SUCCESS_DUPLICATE("208", "重复记录"),
	
	/** 账户已注册 */
	SUCCESS_EXIST("209", "账户已注册"),
	
	/** 已签到过了 */
	SUCCESS_CHECK_IN("210", "已签到过了"),
	
	/** 今日阅读领取金币值已达上限 */
	SUCCESS_DAILY_LIMIT("211", "今日阅读领取金币值已达上限"),
	
	/** 无效邀请码 */
	SUCCESS_INVALID_CODE("212", "无效邀请码"),
	
	/** 多余无用请求 */
	SUCCESS_UNUSER_REQ("213", "多余无用请求"),
	
	/** 这个视频的奖励已经发完，换个视频看看吧 */
	SUCCESS_VIDEO_DAILY_LIMIT("214", "这个视频的奖励已经发完，换个视频看看吧"),
	
	/** 这篇文章的奖励已经发完，换个文章看看吧 */
	SUCCESS_ARTICLE_DAILY_LIMIT("215", "这篇文章的奖励已经发完，换个文章看看吧"),
	
	/** 已绑定过，不能重复绑定 */
	SUCCESS_IS_BIND("216", "已绑定过，不能重复绑定"),
	
	/** 无效请求 */
	SUCCESS_NOT_VALID("217", "无效请求"),
	
	/** 系统繁忙，请稍后重试 */
	SUCCESS_IS_BUSY("218", "系统繁忙，请稍后重试"),
	
	/** 每日最多可申请提现3笔，请明天再来 */
	SUCCESS_WITHDRAW_LIMIT("219", "每日最多可申请提现3笔，请明天再来"),
	
	/** 该设备已登录过多个账号，请用原账号登录 */
	SUCCESS_REGISTER_LIMIT("220", "该设备已登录过多个账号，请用原账号登录"),
	
	/** 不能绑定带特殊符号图标的微信昵称 */
	SUCCESS_BERIGHT_WECHAT("221", "不能绑定带特殊符号图标的微信昵称"),
	
	/** 请验证图形码 */
	SUCCESS_IMG_CODE("222", "请验证图形码"),
	
	/** 该设备已注册，请用原账号登录 */
	SUCCESS_LOGIN_LIMIT("223", "该设备已注册，请用原账号登录"),
	
	/** 请到应用宝下载最新版本 */
	SUCCESS_VERSION_UPGRADE("224", "请到应用宝下载最新版本"),
	
	/** 该WIFI下注册账号已达上限，请切换网络使用 */
	SUCCESS_IP_LIMIT("225", "该WIFI下注册账号已达上限，请切换网络使用"),
	
	/** 请更新至最新版本 */
	SUCCESS_UPGRADE_NEW("226", "请更新至最新版本"),
	
	/** 请升级至最新版本 */
	SUCCESS_UPGRADE_NEW_TO("227", "请升级到最新版本"),
	
	/** 亲~还未到时段奖励领取时间哦 */
	SUCCESS_IS_NOTRIGHT_TIME("228", "亲~未到时间不能获得'时段奖励'哦"),
	
	/** 您的账号状态异常，请联系客服，以免影响您的收益 */
	SUCCESS_USER_SUSPICIOUS("229", "您的账号状态异常。如需帮助，请联系客服，以免影响您的收益"),
	
	/** 手机号码已被使用 */
	SUCCESS_IS_USED("209", "手机号码已被使用"),

	/** 没有登录 */
	NOT_LOGIN("400", "没有登录"),

	/** 发生异常 */
	EXCEPTION("401", "发生异常"),

	/** 系统错误 */
	SYS_ERROR("402", "系统错误"),
	
	/** 参数错误 */
	PARAMS_ERROR("403", "参数错误"),

	/** 不支持或已经废弃 */
	NOT_SUPPORTED("410", "不支持或已经废弃"),
	
	/** 无效IMEI值 */
	IMEI_ERROR("411", "无效IMEI值"),

	/** AuthCode错误 */
	INVALID_AUTHCODE("444", "无效的AuthCode"),

	/** 太频繁的调用 */
	TOO_FREQUENT("445", "请正常使用"),

	/** 未知的错误 */
	UNKNOWN_ERROR("499", "未知错误");

	private ResultCode(String val, String msg) {
		this.val = val;
		this.msg = msg;
	}

	public String val() {
		return val;
	}

	public String msg() {
		return msg;
	}

	private String val;
	private String msg;
	
}
