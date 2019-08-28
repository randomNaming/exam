package com.xjw.exam.dao;

import com.xjw.exam.entity.QuestionSets;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 试题属性DAO接口
 * @author SHXjw
 * @version 20190810
 */
@Repository
public interface QuestionSetsDao extends CurdDao<QuestionSets> {

    public List<Integer> tempByUpdateInclude(String qId);

    public int updateInclude(@Param("qId") String qId, @Param("id")List<Integer> id);
}
