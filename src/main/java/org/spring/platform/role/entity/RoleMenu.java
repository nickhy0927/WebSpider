package org.spring.platform.role.entity;

import org.spring.common.annotation.Column;
import org.spring.common.annotation.Table;
import org.spring.platform.utils.IdEntity;

@Table(tableName = "t_p_user_role",comment = "用户角色")
public class RoleMenu extends IdEntity{
	
	@Column(comment = "用户ID", length = 50, notNull = true)
	private String userId;
	
	@Column(comment = "角色ID", length = 50, notNull = true)
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
