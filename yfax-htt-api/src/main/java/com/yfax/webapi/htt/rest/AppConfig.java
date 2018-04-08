package com.yfax.webapi.htt.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Minbo.He
 * 配置接口
 */
@Controller
@RequestMapping("/api/htt")
public class AppConfig {
	protected static Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	/**
	 * APP的faq，常见问题页面
	 */
	@RequestMapping("/faq")
    public String pageFaq(Map<String, Object> model) {
        return "faq";
    }
	
	/**
	 * APP的invite，邀请规则页面
	 */
	@RequestMapping("/invite")
    public String pageInvite(Map<String, Object> model) {
        return "invite";
    }
	
	/**
	 * APP的register，注册协议页面
	 */
	@RequestMapping("/register")
    public String pageRegister(Map<String, Object> model) {
        return "register";
    }
	
	/**
	 * APP的download，下载APP页面
	 */
	@RequestMapping("/download")
    public String pageDownload(String url, Model model) {
		model.addAttribute("url", url);
        return "download";
    }
	
	/**
	 * 推啊广告页面
	 */
	@RequestMapping("/tuia")
    public String pageTuia(Map<String, Object> model) {
        return "tuia";
    }
}
