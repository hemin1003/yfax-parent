package com.yfax.webapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器定义
 * @author Minbo.He
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//定义过滤拦截的url名称，例如/api/htt/**，表示htt下所有的名称请求
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns(GlobalUtils.PROJECT_HTT + "/**");
	}
	
}
