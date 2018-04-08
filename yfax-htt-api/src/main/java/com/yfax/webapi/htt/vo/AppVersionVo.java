package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class AppVersionVo implements Serializable{

	private static final long serialVersionUID = -7113688093376362272L;
	
	private String configName;	//名称
	private Object configData;	//渠道详细配置
	private Object sourceData;	//新闻源对应配置
	private String channelRange;	//渠道流量分配随机范围和概率值
	
	public String getConfigName() {
		return configName;
	}
	public String getChannelRange() {
		return channelRange;
	}
	public void setChannelRange(String channelRange) {
		this.channelRange = channelRange;
	}
	public Object getSourceData() {
		return sourceData;
	}
	public void setSourceData(Object sourceData) {
		this.sourceData = sourceData;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public Object getConfigData() {
		return configData;
	}
	public void setConfigData(Object configData) {
		this.configData = configData;
	}
}
