package com.xjw.exam.entity;

/**
 * 学生
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class Student extends User{

    public Student(){

    }

    /*
     * 构造函数
     * @describe 为验证登录做准备
     */
    public Student(String id, String password){
        this.id = id;
        this.password = password;
    }
}
