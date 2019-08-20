package com.xjw.exam.entity;

import java.util.Date;

/**
 * 登录记录
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class LoginHistory {

    // 賬號
    private String userId;

    // 权限
    private String access;

    // 登陆时间
    private Date loginTime;

    public LoginHistory(){

    }

    public LoginHistory(String userId, String level){
        this.userId = userId;
        this.access = level;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
