package com.yfax.task.htt.alipay.demo;

import java.util.Date;
import java.util.List;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.Data;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;

public class TestResponse extends AlipayResponse {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @ApiField("test_response_id")
    private String            testResponseId;

    @ApiField("date")
    private Date              date;

    @ApiField("boolean")
    @ApiListField("list_boolean")
    private List<Boolean>     listBoolean;

    @ApiField("date")
    @ApiListField("list_date")
    private List<Date>        listDate;

    @ApiField("string")
    @ApiListField("list_string")
    private List<String>      listString;

    @ApiField("number")
    @ApiListField("list_number")
    private List<Long>        listNumber;

    @ApiField("string")
    @ApiListField("list_price")
    private List<String>      listPrice;

    @ApiField("user_info")
    private UserInfo          userInfo;

    @ApiField("data")
    @ApiListField("list_data")
    private List<Data>        listData;

    public String toString() {
        return "{" + //
               "testResponseId:" + testResponseId + //
               ",date:" + date + //
               ",listBoolean:" + listBoolean + //
               ",listData:" + listData + //
               ",listDate:" + listDate + //
               ",listString:" + listString + //
               ",listNumber:" + listNumber + //
               ",listPrice:" + listPrice + //
               ",userInfo:" + userInfo + //
               "}";
    }

    public List<Data> getListData() {
        return listData;
    }

    public void setListData(List<Data> listData) {
        this.listData = listData;
    }

    public String getTestResponseId() {
        return testResponseId;
    }

    public void setTestResponseId(String testResponseId) {
        this.testResponseId = testResponseId;
    }

    public List<Boolean> getListBoolean() {
        return listBoolean;
    }

    public void setListBoolean(List<Boolean> listBoolean) {
        this.listBoolean = listBoolean;
    }

    public List<Date> getListDate() {
        return listDate;
    }

    public void setListDate(List<Date> listDate) {
        this.listDate = listDate;
    }

    public List<String> getListString() {
        return listString;
    }

    public void setListString(List<String> listString) {
        this.listString = listString;
    }

    public List<Long> getListNumber() {
        return listNumber;
    }

    public void setListNumber(List<Long> listNumber) {
        this.listNumber = listNumber;
    }

    public List<String> getListPrice() {
        return listPrice;
    }

    public void setListPrice(List<String> listPrice) {
        this.listPrice = listPrice;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}