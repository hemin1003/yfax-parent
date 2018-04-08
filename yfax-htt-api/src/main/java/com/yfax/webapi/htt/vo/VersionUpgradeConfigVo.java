package com.yfax.webapi.htt.vo;

import java.io.Serializable;

public class VersionUpgradeConfigVo implements Serializable {

	private static final long serialVersionUID = 5875750673354485490L;

	private String versionName;// 版本名
	private String apkurl;// 下载链接
	private Integer updateType;// 更新类型
	private String description;// 更新描述
	

	public String getApkurl() {
		return apkurl;
	}

	public void setApkurl(String apkurl) {
		this.apkurl = apkurl;
	}

	public Integer getUpdateType() {
		return updateType;
	}

	public void setUpdateType(Integer updateType) {
		this.updateType = updateType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@Override
	public String toString() {
		return "VersionUpgradeConfigVo [apkurl=" + apkurl + ", updateType=" + updateType + ", description="
				+ description + "]";
	}
}
