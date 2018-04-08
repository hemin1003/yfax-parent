package com.yfax.task.htt.alipay.demo;

import java.util.List;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;

public class UserInfo extends AlipayObject {

    /** serialVersionUID */
    private static final long serialVersionUID = 4767604747954841921L;

    @ApiField("out_id")
    private Integer           outId;

    @ApiField("point")
    private Point             point;

    @ApiField("point")
    @ApiListField("list_point")
    private List<Point>       listPoint;

    public String toString() {
        return "{outId:" + outId + ",point:" + point + ",listPoint:" + listPoint + "}";
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public List<Point> getListPoint() {
        return listPoint;
    }

    public void setListPoint(List<Point> listPoint) {
        this.listPoint = listPoint;
    }

}
