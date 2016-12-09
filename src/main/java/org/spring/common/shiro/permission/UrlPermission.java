package org.spring.common.shiro.permission;

import org.apache.shiro.authz.Permission;

public class UrlPermission implements Permission{

	@Override
	public boolean implies(Permission p) {
		return false;
	}


}
