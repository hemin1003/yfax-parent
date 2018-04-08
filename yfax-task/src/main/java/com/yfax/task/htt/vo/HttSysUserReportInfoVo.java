package com.yfax.task.htt.vo;

import java.io.Serializable;

public class HttSysUserReportInfoVo implements Serializable{

	private static final long serialVersionUID = -845970271170355877L;
	
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
