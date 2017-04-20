package org.spring.platform.utils;

import java.util.Date;

/**
 * Created by yuanhuangd on 2017/4/20.
 */
public class IdEntity {
    private String id;
    private Date createTime = new Date();
    private Date updateTime;
    private int status = StatusCode.VALID;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public interface StatusCode {
        int INVALID = 0;
        int VALID = 1;
    }
}
