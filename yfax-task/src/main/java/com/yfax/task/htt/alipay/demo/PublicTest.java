/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.yfax.task.htt.alipay.demo;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

/*import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.json.JSONWriter;
import com.alipay.api.request.AlipayMobilePublicAccountAddRequest;
import com.alipay.api.request.AlipayMobilePublicMenuGetRequest;
import com.alipay.api.response.AlipayMobilePublicAccountAddResponse;
import com.alipay.api.response.AlipayMobilePublicMenuGetResponse;*/

/**
 * 
 * @author wb-tongxl
 * @version $Id: PublicTest.java, v 0.1 2013-10-10 04:53:30 wb-tongxl Exp $
 */
public class PublicTest {
    /**
      * 
      * @param args
      * @throws Exception 
      */

    public static void main(String[] args) throws Exception {
        // 公众号菜单查询
        //menuGet();

        // 公众号通知消息签名验证
        //checkSign();

        // 公众号账号绑定
        //accountAdd();

        // 公众号验签&解密
        checkSignAndDecrypt();

        // 公众号加密&加签
        encryptAndSign();
    }

    /*
    public static void accountAdd() throws AlipayApiException {
    AlipayMobilePublicAccountAddRequest request = new AlipayMobilePublicAccountAddRequest();
    // 组装请求对象
    BindAccountBean bean = new BindAccountBean();
    bean.setAgreementId("20130829000000338233");
    bean.setDisplayName("尾号3190");
    bean.setAppId("2013082200024933");
    bean.setRealName("乔蒂");
    bean.setBindAccountNo("18618343190");
    bean.setFromUserId("2088102124069339");

    JSONWriter json = new JSONWriter();
    String bingUserStr = json.write(bean);

    request.setBizContent(bingUserStr);

    // 执行调用,转换为返回对象类型
    AlipayClient client = getAlipayClient();
    AlipayMobilePublicAccountAddResponse rsp = client.execute(request);
    System.out.println(rsp.getBody());

    }

    *//**
      * 
      * @throws AlipayApiException
      */
    /*
    private static void checkSign() throws AlipayApiException {
    Map<String, String> params = new HashMap<String, String>();

    params
        .put(
            "biz_context",
            "<XML><AppId><![CDATA[2013082200024893]]></AppId><FromUserId><![CDATA[2088102122485786]]></FromUserId><CreateTime>1377228401913</CreateTime><MsgType><![CDATA[click]]></MsgType><EventType><![CDATA[event]]></EventType><ActionParam><![CDATA[authentication]]></ActionParam><AgreementId><![CDATA[201308220000000994]]></AgreementId><AccountNo><![CDATA[null]]></AccountNo><UserInfo><![CDATA[{\"logon_id\":\"15858179811\",\"user_name\":\"许旦辉\"}]]></UserInfo></XML>");
    params.put("charset", "GBK");
    params.put("service", "alipay.mobile.public.message.notify");
    params.put("sign_type", "RSA");
    params
        .put(
            "sign",
            "rlqgA8O+RzHBVYLyHmrbODVSANWPXf3pSrr82OCO/bm3upZiXSYrX5fZr6UBmG6BZRAydEyTIguEW6VRuAKjnaO/sOiR9BsSrOdXbD5Rhos/Xt7/mGUWbTOt/F+3W0/XLuDNmuYg1yIC/6hzkg44kgtdSTsQbOC9gWM7ayB4J4c=");

    boolean checkSign = AlipaySignature
        .rsaCheckV2(
            params,
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB",
            AlipayConstants.CHARSET_GBK);

    System.out.println(checkSign);
    }

    *//**
      * 
      * @throws AlipayApiException
      */
    /*
    public static void menuGet() throws AlipayApiException {
    AlipayClient client = getAlipayClient();

    AlipayMobilePublicMenuGetRequest request = new AlipayMobilePublicMenuGetRequest();

    AlipayMobilePublicMenuGetResponse rsp = client.execute(request);

    System.out.println(rsp.getBody());
    }

    *//**
      * 
      * @return
      * @throws AlipayApiException 
      */
    /*
    private static AlipayClient getAlipayClient() {
    AlipayClient client = new DefaultAlipayClient(
    // 线上地址 
        //  "https://openapi.alipay.com/gateway.do",
        // 沙箱地址
        "http://openapi.alipaydev.com/gateway.do",
        // 公众号ID
        "2013082200024933",
        // 公众号RSA私钥
        "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMiAec6fsssguUoRN3oEVEnQaqBLZjeafXAxCbKH3MTJaXPmnXOtqFFqFtcB8J9KqyFI1+o6YBDNIdFWMKqOwDDWPKqtdo90oGav3QMikjGYjIpe/gYYCQ/In/oVMVj326GmKrSpp0P+5LNCx59ajRpO8//rnOLd6h/tNxnfahanAgMBAAECgYEAusouMFfJGsIWvLEDbPIhkE7RNxpnVP/hQqb8sM0v2EkHrAk5wG4VNBvQwWe2QsAuY6jYNgdCPgTNL5fLaOnqkyy8IobrddtT/t3vDX96NNjHP4xfhnMbpGjkKZuljWKduK2FAh83eegrSH48TuWS87LjeZNHhr5x4C0KHeBTYekCQQD5cyrFuKua6GNG0dTj5gA67R9jcmtcDWgSsuIXS0lzUeGxZC4y/y/76l6S7jBYuGkz/x2mJaZ/b3MxxcGQ01YNAkEAzcRGLTXgTMg33UOR13oqXiV9cQbraHR/aPmS8kZxkJNYows3K3umNVjLhFGusstmLIY2pIpPNUOho1YYatPGgwJBANq8vnj64p/Hv6ZOQZxGB1WksK2Hm9TwfJ5I9jDu982Ds6DV9B0L4IvKjHvTGdnye234+4rB4SpGFIFEo+PXLdECQBiOPMW2cT8YgboxDx2E4bt8g9zSM5Oym2Xeqs+o4nKbcu96LipNRkeFgjwXN1708QuNNMYsD0nO+WIxqxZMkZsCQHtS+Jj/LCnQZgLKxXZAllxqSTlBln2YnBgk6HqHLp8Eknx2rUXhoxE1vD9tNmom6PiaZlQyukrQkp5GOMWDMkU=");
    return client;
    }*/

    private static void checkSignAndDecrypt() throws AlipayApiException {
        // 参数构建
        String biz_content = "<XML><AppId><![CDATA[2013082200024893]]></AppId><FromUserId><![CDATA[2088102122485786]]></FromUserId><CreateTime>1377228401913</CreateTime><MsgType><![CDATA[click]]></MsgType><EventType><![CDATA[event]]></EventType><ActionParam><![CDATA[authentication]]></ActionParam><AgreementId><![CDATA[201308220000000994]]></AgreementId><AccountNo><![CDATA[null]]></AccountNo><UserInfo><![CDATA[{\"logon_id\":\"15858179811\",\"user_name\":\"许旦辉\"}]]></UserInfo></XML>";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMiAec6fsssguUoRN3oEVEnQaqBLZjeafXAxCbKH3MTJaXPmnXOtqFFqFtcB8J9KqyFI1+o6YBDNIdFWMKqOwDDWPKqtdo90oGav3QMikjGYjIpe/gYYCQ/In/oVMVj326GmKrSpp0P+5LNCx59ajRpO8//rnOLd6h/tNxnfahanAgMBAAECgYEAusouMFfJGsIWvLEDbPIhkE7RNxpnVP/hQqb8sM0v2EkHrAk5wG4VNBvQwWe2QsAuY6jYNgdCPgTNL5fLaOnqkyy8IobrddtT/t3vDX96NNjHP4xfhnMbpGjkKZuljWKduK2FAh83eegrSH48TuWS87LjeZNHhr5x4C0KHeBTYekCQQD5cyrFuKua6GNG0dTj5gA67R9jcmtcDWgSsuIXS0lzUeGxZC4y/y/76l6S7jBYuGkz/x2mJaZ/b3MxxcGQ01YNAkEAzcRGLTXgTMg33UOR13oqXiV9cQbraHR/aPmS8kZxkJNYows3K3umNVjLhFGusstmLIY2pIpPNUOho1YYatPGgwJBANq8vnj64p/Hv6ZOQZxGB1WksK2Hm9TwfJ5I9jDu982Ds6DV9B0L4IvKjHvTGdnye234+4rB4SpGFIFEo+PXLdECQBiOPMW2cT8YgboxDx2E4bt8g9zSM5Oym2Xeqs+o4nKbcu96LipNRkeFgjwXN1708QuNNMYsD0nO+WIxqxZMkZsCQHtS+Jj/LCnQZgLKxXZAllxqSTlBln2YnBgk6HqHLp8Eknx2rUXhoxE1vD9tNmom6PiaZlQyukrQkp5GOMWDMkU=";
        Map<String, String> params = new HashMap<String, String>();
        params.put("biz_content", AlipaySignature.rsaEncrypt(biz_content, publicKey, "UTF-8"));
        params.put("charset", "UTF-8");
        params.put("service", "alipay.mobile.public.message.notify");
        params.put("sign_type", "RSA");
        params.put("sign", AlipaySignature.rsaSign(params, privateKey, "UTF-8"));

        // 验签&解密
        String resultContent = AlipaySignature.checkSignAndDecrypt(params, publicKey, privateKey,
            true, true);

        System.out.println(resultContent);
    }

    private static void encryptAndSign() throws AlipayApiException {
        // 参数构建
        String bizContent = "<XML><ToUserId><![CDATA[2088102122494786]]></ToUserId><AppId><![CDATA[2013111100036093]]></AppId><AgreementId><![CDATA[20131111000001895078]]></AgreementId>"
                            + "<CreateTime>12334349884</CreateTime>"
                            + "<MsgType><![CDATA[image-text]]></MsgType>"
                            + "<ArticleCount>1</ArticleCount>"
                            + "<Articles>"
                            + "<Item>"
                            + "<Title><![CDATA[[回复测试加密解密]]></Title>"
                            + "<Desc><![CDATA[测试加密解密]]></Desc>"
                            + "<Url><![CDATA[http://m.taobao.com]]></Url>"
                            + "<ActionName><![CDATA[立即前往]]></ActionName>"
                            + "</Item>"
                            + "</Articles>" + "<Push><![CDATA[false]]></Push>" + "</XML>";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMiAec6fsssguUoRN3oEVEnQaqBLZjeafXAxCbKH3MTJaXPmnXOtqFFqFtcB8J9KqyFI1+o6YBDNIdFWMKqOwDDWPKqtdo90oGav3QMikjGYjIpe/gYYCQ/In/oVMVj326GmKrSpp0P+5LNCx59ajRpO8//rnOLd6h/tNxnfahanAgMBAAECgYEAusouMFfJGsIWvLEDbPIhkE7RNxpnVP/hQqb8sM0v2EkHrAk5wG4VNBvQwWe2QsAuY6jYNgdCPgTNL5fLaOnqkyy8IobrddtT/t3vDX96NNjHP4xfhnMbpGjkKZuljWKduK2FAh83eegrSH48TuWS87LjeZNHhr5x4C0KHeBTYekCQQD5cyrFuKua6GNG0dTj5gA67R9jcmtcDWgSsuIXS0lzUeGxZC4y/y/76l6S7jBYuGkz/x2mJaZ/b3MxxcGQ01YNAkEAzcRGLTXgTMg33UOR13oqXiV9cQbraHR/aPmS8kZxkJNYows3K3umNVjLhFGusstmLIY2pIpPNUOho1YYatPGgwJBANq8vnj64p/Hv6ZOQZxGB1WksK2Hm9TwfJ5I9jDu982Ds6DV9B0L4IvKjHvTGdnye234+4rB4SpGFIFEo+PXLdECQBiOPMW2cT8YgboxDx2E4bt8g9zSM5Oym2Xeqs+o4nKbcu96LipNRkeFgjwXN1708QuNNMYsD0nO+WIxqxZMkZsCQHtS+Jj/LCnQZgLKxXZAllxqSTlBln2YnBgk6HqHLp8Eknx2rUXhoxE1vD9tNmom6PiaZlQyukrQkp5GOMWDMkU=";
        String responseContent = AlipaySignature.encryptAndSign(bizContent, publicKey, privateKey,
            "UTF-8", true, true);
        System.out.println(responseContent);

    }
}
