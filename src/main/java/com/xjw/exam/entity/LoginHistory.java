package com.xjw.exam.entity;

import java.util.Date;

public class LoginHistory {

    // 学号
    private String stuid;

    // 权限
    private String access;

    // 登陆时间
    private Date loginTime;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
