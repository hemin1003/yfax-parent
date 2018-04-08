package com.yfax.webapi.htt.vo;

import java.io.Serializable;

/**
 * 广告内容详情配置表
 * 
 * @author Minbo
 */
public class NoticeConfigVo implements Serializable {

	private static final long serialVersionUID = -8368198743598377549L;

	private String title;// 标题
	private String description;// 描述
	private Object action;// 动态参数
	private String titleMapUrl;// 标题图Url
	private String buttonCopywriting;// 按钮文案
	private String notificationType;// 通知类型
	private String tag;// 标签

	private String cDate;// 最新修改时间

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getAction() {
		return action;
	}

	public void setAction(Object action) {
		this.action = action;
	}

	public String getTitleMapUrl() {
		return titleMapUrl;
	}

	public void setTitleMapUrl(String titleMapUrl) {
		this.titleMapUrl = titleMapUrl;
	}

	public String getButtonCopywriting() {
		return buttonCopywriting;
	}

	public void setButtonCopywriting(String buttonCopywriting) {
		this.buttonCopywriting = buttonCopywriting;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
