package org.spring.platform.user.entity;

import java.util.Date;

import org.spring.common.annotation.Column;
import org.spring.common.annotation.Table;
import org.spring.platform.utils.IdEntity;

@Table(tableName = "t_p_user", comment = "用户表")
public class User extends IdEntity {

	@Column(length = 50, comment = "真实姓名", notNull = true)
	private String realName;

	@Column(length = 50, comment = "登录名", notNull = true)
	private String loginName;

	@Column(length = 50, comment = "登录密码", notNull = true)
	private String password;

	@Column(length = 30, comment = "用户邮箱", notNull = false)
	private String email;

	@Column(length = 30, comment = "用户电话", notNull = false)
	private String phone;

	@Column(length = 1, comment = "是否锁定 默认 false: 0 未锁定 true : 1 锁定", notNull = true)
	private Boolean locked = Boolean.FALSE;
	
	@Column(comment = "出生日期", notNull = false)
	private Date borthday;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getBorthday() {
		return borthday;
	}
	
	public void setBorthday(Date borthday) {
		this.borthday = borthday;
	}
}
