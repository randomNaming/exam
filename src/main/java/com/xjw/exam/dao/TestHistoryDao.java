package com.xjw.exam.dao;

import com.xjw.exam.entity.TestHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考试记录DAO接口
 * @author SHXjw
 * @version 20190910
 */
@Repository
public interface TestHistoryDao extends CurdDao<TestHistory> {

    public int count(@Param("stuId") String stuId, @Param("paperId") Integer paperId);

    public List<TestHistory> testRecord(@Param("stuId")String stuId, @Param("paperId")Integer paperId);

    public int deleteByQuestionId(String qId);
}
