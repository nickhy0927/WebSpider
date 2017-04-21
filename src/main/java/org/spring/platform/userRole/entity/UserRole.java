package org.spring.platform.userRole.entity;

import org.spring.common.annotation.Column;
import org.spring.common.annotation.Table;
import org.spring.platform.utils.IdEntity;

@Table(tableName = "t_p_role_menu", comment = "角色菜单")
public class UserRole extends IdEntity {

	@Column(comment = "角色ID", length = 50, notNull = true)
	private String roleId;

	@Column(comment = "菜单ID", length = 50, notNull = true)
	private String menuId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
