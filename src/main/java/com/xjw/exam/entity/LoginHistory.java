package com.xjw.exam.entity;

import java.util.Date;

/**
 * 登录记录
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class LoginHistory {

    // 学号
    private String stuId;

    // 权限
    private String access;

    // 登陆时间
    private Date loginTime;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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
