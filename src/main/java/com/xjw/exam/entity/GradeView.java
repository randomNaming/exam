package com.xjw.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 学生成绩
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
public class GradeView {
    private String stuId;  // 学号

    private String stuName; // 学生姓名

    // 上次考试时间
    // timezone根据时区来计算，默认GMT+8
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date lastTime;

    private Integer total; // 总分

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
