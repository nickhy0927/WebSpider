package org.spring.common.shiro.form;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		String loginUrl = getLoginUrl();
		loginUrl += "?sessionid=" + UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		System.out.println("loginUrl = " + loginUrl);
		WebUtils.issueRedirect(request, response, loginUrl);
	}
}
