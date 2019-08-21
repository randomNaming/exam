package com.xjw.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 考试记录
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class TestHistory {
    // 学生学号
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stuId;
    //
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer qSetId;
    // 问题编号
    private Integer qId;
    // 学生选择
    private String stuChoise;
    // 学生分数
    private Integer stuScore;
    // 考试时长
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date testTime;
    // 試題
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Question question;

    public TestHistory(){}

    public TestHistory(String stuId, Integer qSetId, Integer qId, String stuChoise, Date testTime){
        this.stuId = stuId;
        this.qSetId = qSetId;
        this.qId = qId;
        this.stuChoise = stuChoise;
        this.testTime = testTime;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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

    public Integer getqSetId() {
        return qSetId;
    }

    public void setqSetId(Integer qSetId) {
        this.qSetId = qSetId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
