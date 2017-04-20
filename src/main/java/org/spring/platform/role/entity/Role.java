package org.spring.platform.role.entity;

import org.spring.platform.utils.IdEntity;

public class Role extends IdEntity{

	private String name;
	private String permission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPermission() {
		return permission;
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
