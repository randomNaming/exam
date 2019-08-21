package com.xjw.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 考试问题
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class Question {

    // 问题编号
    private Integer id;

    // 题目内容
    private String content;

    // 题目答案
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String answer;

    // 题目分数
    private Integer score;

    // 教师编号
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherId;

    // 教师姓名
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherName;

    // 创建时间
    // timezone根据时区来计算，默认GMT+8
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;

    private String choice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


}
