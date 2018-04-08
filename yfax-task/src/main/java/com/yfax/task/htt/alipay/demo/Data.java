package com.yfax.task.htt.alipay.demo;

import java.util.List;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;

public class Data extends AlipayObject {
    /** serialVersionUID */
    private static final long serialVersionUID = 8819409019742210991L;

    @ApiField("id")
    private Integer           id;

    @ApiField("user_info")
    private UserInfo          userInfo;

    @ApiField("user_info")
    @ApiListField("list_user_info")
    private List<UserInfo>    listUserInfo;

    @ApiField("point")
    @ApiListField("list_point")
    private List<Point>       listPoint;

    @ApiField("string")
    @ApiListField("list_id")
    private List<String>      listId;

    public String toString() {
        return "{id:" + id + ",userInfo:" + userInfo + ",listUserInfo:" + listUserInfo
               + ",listPoint:" + listPoint + ",listId:" + listId + "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<UserInfo> getListUserInfo() {
        return listUserInfo;
    }

    public void setListUserInfo(List<UserInfo> listUserInfo) {
        this.listUserInfo = listUserInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<String> getListId() {
        return listId;
    }

    public void setListId(List<String> listId) {
        this.listId = listId;
    }

    public List<Point> getListPoint() {
        return listPoint;
    }

    public void setListPoint(List<Point> listPoint) {
        this.listPoint = listPoint;
    }

}