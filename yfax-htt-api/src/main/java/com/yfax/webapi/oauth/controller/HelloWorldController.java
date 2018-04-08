package com.yfax.webapi.oauth.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yfax.utils.NetworkUtil;
import com.yfax.webapi.GlobalUtils;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
	
	protected static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(HttpServletRequest request) {
    		logger.info("NetworkUtil.getIpAddress, 获取访问者IP=" + NetworkUtil.getIpAddress(request));
        return "Hello，Greetings from 开放Api平台（" + GlobalUtils.PROJECT_HTT + "）!!!";
    }

}
