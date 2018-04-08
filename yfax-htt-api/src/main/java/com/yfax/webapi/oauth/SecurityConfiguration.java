package com.yfax.webapi.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import com.yfax.webapi.GlobalUtils;
import com.yfax.webapi.oauth.service.CfdbUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    //自定义UserDetailsService注入
    @Autowired
    private CfdbUserDetailsService userDetailsService;
    
    //配置匹配用户时密码规则
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return null;
//    }
    
    //配置全局设置
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //设置UserDetailsService以及密码规则
        auth.userDetailsService(userDetailsService);
    }
    
    	//排除/hello路径拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hello"
        		, GlobalUtils.PROJECT_HTT + "/doRegister"
        		, GlobalUtils.PROJECT_HTT + "/doRegisterNew"
        		, GlobalUtils.PROJECT_HTT + "/queryInitConfig"
        		, GlobalUtils.PROJECT_HTT + "/faq"
        		, GlobalUtils.PROJECT_HTT + "/tuia"
        		, GlobalUtils.PROJECT_HTT + "/invite"
        		, GlobalUtils.PROJECT_HTT + "/register"
        		, GlobalUtils.PROJECT_HTT + "/download"
        		, GlobalUtils.PROJECT_HTT + "/doLogin"
        		, GlobalUtils.PROJECT_HTT + "/doSms"
        		, GlobalUtils.PROJECT_HTT + "/doSmsNew"
        		, GlobalUtils.PROJECT_HTT + "/doDownloadUrlTy"
        		, GlobalUtils.PROJECT_HTT + "/doTransferUrlTy"
        		, GlobalUtils.PROJECT_HTT + "/doResetPwd"
        		, GlobalUtils.PROJECT_HTT + "/doRedirectUrl"
        		, GlobalUtils.PROJECT_HTT + "/queryRank"
        		, GlobalUtils.PROJECT_HTT + "/queryRankGold"
        		, GlobalUtils.PROJECT_HTT + "/queryAdvList"
        		, GlobalUtils.PROJECT_HTT + "/doWechatLogin"
        		, GlobalUtils.PROJECT_HTT + "/queryDomainConfig"
        		, GlobalUtils.PROJECT_HTT + "/queryVersionConfig"
        		, GlobalUtils.PROJECT_HTT + "/doDdzPoinsCallback"
        		, GlobalUtils.PROJECT_HTT + "/doArticleAward"
        		, GlobalUtils.PROJECT_HTT + "/doDownloadUrlXb"
        		);
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    //开启全局方法拦截
    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    public static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
        @Override
        protected MethodSecurityExpressionHandler createExpressionHandler() {
            return new OAuth2MethodSecurityExpressionHandler();
        }
    }
}