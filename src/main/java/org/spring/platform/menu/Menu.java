package org.spring.platform.menu;

import org.spring.common.annotation.Column;
import org.spring.common.annotation.Table;
import org.spring.platform.utils.IdEntity;

@Table(tableName = "t_p_menu", comment = "菜单表")
public class Menu extends IdEntity {

	@Column(comment = "菜单名称", length = 50, notNull = true)
	private String name;

	@Column(comment = "菜单描述", length = 50, notNull = false)
	private String description;

	@Column(comment = "菜单地址", length = 200, notNull = true)
	private String url;

	@Column(comment = "菜单图标", length = 50, notNull = false)
	private String icon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
