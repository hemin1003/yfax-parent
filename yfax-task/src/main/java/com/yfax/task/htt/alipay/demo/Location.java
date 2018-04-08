package com.yfax.task.htt.alipay.demo;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class Location extends AlipayObject {

    /** serialVersionUID */
    private static final long serialVersionUID = 464181093539634040L;

    @ApiField("x")
    private Integer           x;

    public String toString() {
        return "{" + "x:" + x + "}";
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }
}
