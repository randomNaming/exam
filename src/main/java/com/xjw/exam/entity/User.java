package com.xjw.exam.entity;

public abstract class User {
    // 编号
    private String id;

    // 姓名
    private String name;

    // 密码
    private String password;

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
}
