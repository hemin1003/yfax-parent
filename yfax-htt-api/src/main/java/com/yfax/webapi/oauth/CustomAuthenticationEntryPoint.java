package com.yfax.webapi.oauth;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.yfax.utils.NetworkUtil;

/**
 * 自定义401错误码内容
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae)
			throws IOException, ServletException {

		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String ip = NetworkUtil.getIpAddress(request);
		String queryString = "";
		// 去掉最后一个空格
		Map<String, String[]> params = request.getParameterMap();
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		queryString = queryString.equals("") ? null : queryString.substring(0, queryString.length() - 1);
		if (uri != null && uri.indexOf("doLoginHis") > 0) {
			queryString = "params set to null";
		}
		log.warn("Pre-authenticated entry point called. Rejecting access. authenticationExceptionMsg="
				+ ae.getMessage() + ", " + String.format("请求参数, url: %s, method: %s, uri: %s, params: %s, ip: %s", url,
						method, uri, queryString, ip) + "，自定义错误码异常，请求接口有问题 For Bad Request/Access Denied");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Access Denied");

	}
}