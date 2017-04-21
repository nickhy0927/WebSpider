package org.spring.platform.utils;

import java.util.Date;

import org.spring.common.annotation.Column;
import org.spring.common.annotation.InheritedAnnotation;

/**
 * Created by yuanhuangd on 2017/4/20.
 */
@InheritedAnnotation
public class IdEntity {

	@Column(name = "id", length = 30, comment = "主键ID", idType = "native", notNull = true)
	private String id;

	@Column(name = "createTime", comment = "创建时间", notNull = true)
	private Date createTime = new Date();

	@Column(name = "updateTime", comment = "修改时间", notNull = false)
	private Date updateTime;

	@Column(name = "status", length = 1, comment = "是否有效 1 有效，0 无效", notNull = true)
	private Integer status = StatusCode.VALID;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public interface StatusCode {
		int INVALID = 0;
		int VALID = 1;
	}
}
