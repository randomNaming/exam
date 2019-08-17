package com.xjw.exam.entity;

/**
 * 教师
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class Teacher extends User{

    public Teacher(){

    }

    /*
     * 构造函数
     * @describe 为验证登录做准备
     */
    public Teacher(String id, String password){
        this.setId(id);
        this.setPassword(password);
    }

}
