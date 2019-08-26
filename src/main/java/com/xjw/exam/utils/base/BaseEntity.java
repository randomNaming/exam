package com.xjw.exam.utils.base;

import java.io.Serializable;

/**
 * Entity支持类 - 为将来序列化准备
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体编号（唯一标识）
     */
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }

}
