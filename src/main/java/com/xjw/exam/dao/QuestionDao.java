package com.xjw.exam.dao;

import com.xjw.exam.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考试问题DAO接口
 * @author SHXjw
 * @version 20190910
 */
@Repository
public interface QuestionDao extends CurdDao<Question> {

    /**
     * 获取问题集合
     * @param id
     */
    public List<Question> selectByIdSet(List<Integer> id);
}
