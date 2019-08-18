package com.xjw.exam.dao;

import java.util.List;

/**
 * 基本的增删改查实现
 * @author SHXjw
 * @version 20190910
 * @param <T>
 */
public interface CurdDao<T> extends BaseDao{

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    // public T get(int id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity);

    /**
     * 查询所有数据列表
     * @return
     */
    public List<T> findAllList();

    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 删除数据
     * @param id
     * @return
     */
    public int delete(String id);
}
