package com.xjw.exam.entity;

import java.util.Date;

/**
 * 成绩结果
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class Stugrade {

    // 学生编号
    private String stuId;

    // 试卷属性编号
    private Integer setId;

    // 正确题数
    private Integer rightCount;
    // 错误题数
    private Integer wrongCount;
    // 总分
    private Integer total;
    // 考试开始时间
    private Date startTime;
    // 考试结束时间
    private Date finishTime;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getRightCount() {
        return rightCount;
    }

    public void setRightCount(Integer rightCount) {
        this.rightCount = rightCount;
    }

    public Integer getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(Integer wrongCount) {
        this.wrongCount = wrongCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
