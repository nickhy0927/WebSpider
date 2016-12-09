package org.spring.common.shiro.permission;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

public class UrlPermissionResolver extends PathMatchingFilter{

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		return super.onPreHandle(request, response, mappedValue);
	}
	
	@Override
	protected boolean pathsMatch(String path, ServletRequest request) {
		return super.pathsMatch(path, request);
	}
	
}
