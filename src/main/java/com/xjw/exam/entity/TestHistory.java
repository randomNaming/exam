package com.xjw.exam.entity;

import java.util.Date;

/**
 * 考试记录
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class TestHistory {
    // 学生学号
    private String stuId;
    //
    private Integer qSet;
    // 问题编号
    private Integer qId;
    // 学生选择
    private String stuChoise;
    // 学生分数
    private Integer stuScore;
    // 考试时长
    private Date testTime;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Integer getqSet() {
        return qSet;
    }

    public void setqSet(Integer qSet) {
        this.qSet = qSet;
    }

    public Integer getqId() {
        return qId;
    }

    public void setqId(Integer qId) {
        this.qId = qId;
    }

    public String getStuChoise() {
        return stuChoise;
    }

    public void setStuChoise(String stuChoise) {
        this.stuChoise = stuChoise;
    }

    public Integer getStuScore() {
        return stuScore;
    }

    public void setStuScore(Integer stuScore) {
        this.stuScore = stuScore;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }
}
