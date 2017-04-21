package org.spring.platform.role.entity;

import org.spring.common.annotation.Column;
import org.spring.common.annotation.Table;
import org.spring.platform.utils.IdEntity;

@Table(tableName = "t_p_role" ,comment = "角色表")
public class Role extends IdEntity{

	@Column(comment = "角色名称",length = 30,notNull = true)
	private String name;
	
	@Column(comment = "角色描述",length = 100,notNull = false)
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
