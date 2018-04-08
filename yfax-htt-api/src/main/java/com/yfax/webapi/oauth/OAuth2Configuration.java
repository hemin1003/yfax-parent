package com.yfax.webapi.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.yfax.webapi.GlobalUtils;

import javax.sql.DataSource;

@Configuration
public class OAuth2Configuration {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Autowired
        private CustomLogoutSuccessHandler customLogoutSuccessHandler;

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .logout()
                    .logoutUrl("/oauth/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/hello/"
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
                    		).permitAll()
                    .antMatchers("/secure/**", 
                    		GlobalUtils.PROJECT_HTT + "/**").authenticated();

        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements EnvironmentAware {

        private static final String ENV_OAUTH = "authentication.oauth.";
        private static final String PROP_CLIENTID = "clientid";
        private static final String PROP_SECRET = "secret";
        private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";

        private RelaxedPropertyResolver propertyResolver;

        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
            
        }

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient(propertyResolver.getProperty(PROP_CLIENTID))
                    .scopes("read", "write")
                    .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_USER.name())
                    .authorizedGrantTypes("password", "refresh_token")
                    .secret(propertyResolver.getProperty(PROP_SECRET))
                    .accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1296000));
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
        }

    }

}
