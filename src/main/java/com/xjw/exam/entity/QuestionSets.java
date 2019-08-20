package com.xjw.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * 问题属性
 * @author SHXjw - S.H.Xjw@outlook.com
 * @version 20190810
 */
public class QuestionSets {

    // 编号
    private Integer id;

    // 试卷名称
    private String setName;

    // 包含试题
    private String include;

    // 创建时间
    // timezone根据时区来计算，默认GMT+8
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT-5")
    private Date createTime;

    // 作者
    private String author;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Question> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
