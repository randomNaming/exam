package com.xjw.exam.dao;

import com.xjw.exam.entity.GradeView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeViewDao {
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public GradeView get(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public GradeView getByElement(GradeView entity);

    /**
     * 查询所有数据列表
     * @return
     */
    public List<GradeView> findAllList();

    public List<GradeView> search(@Param("elements") GradeView elements, @Param("leftScoresRange") Integer leftScoresRange,
                                  @Param("rightScoresRange") Integer rightScoresRange);
}
