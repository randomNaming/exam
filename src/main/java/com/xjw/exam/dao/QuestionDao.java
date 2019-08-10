package com.xjw.exam.dao;

import com.xjw.exam.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * 考试问题DAO接口
 * @author SHXjw
 * @version 20190910
 */
@Repository
public interface QuestionDao extends CurdDao<Question> {
}
