package com.yfax.task.htt.alipay.demo;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;

public class Point extends AlipayObject {

    /** serialVersionUID */
    private static final long serialVersionUID = 4767604747954841921L;

    @ApiField("point_value")
    private Integer           pointValue;

    @ApiField("location")
    private Location          location;

    public String toString() {
        return "{pointValue:" + pointValue + ",location:" + location + "}";
    }

    public Integer getPointValue() {
        return pointValue;
    }

    public void setPointValue(Integer pointValue) {
        this.pointValue = pointValue;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
