package com.xjw.exam.entity;

import java.util.Date;

public class TestHistory {

    private String stuId;

    private Integer qSet;

    private Integer qId;

    private String stuChoise;

    private Integer stuScore;

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
