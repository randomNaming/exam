package com.xjw.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class User {
    // 编号
    protected String id;

    // 姓名
    protected String name;

    // 密码
    @JsonIgnore
    protected String password;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "id: " + this.id + " name: " + this.name;
    }
}
