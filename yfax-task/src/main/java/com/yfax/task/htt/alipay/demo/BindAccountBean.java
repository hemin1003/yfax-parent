/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.yfax.task.htt.alipay.demo;

/**
 * 
 * @author chuanqun.zhu
 * @version $Id: BindAccountBean.java, v 0.1 2013-9-9 下午05:27:23 chuanqun.zhu Exp $
 */
public class BindAccountBean {

    private String agreementId;

    private String displayName;

    private String appId;

    private String realName;

    private String bindAccountNo;

    private String fromUserId;

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBindAccountNo() {
        return bindAccountNo;
    }

    public void setBindAccountNo(String bindAccountNo) {
        this.bindAccountNo = bindAccountNo;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

}
