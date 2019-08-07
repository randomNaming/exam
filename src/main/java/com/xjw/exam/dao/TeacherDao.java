package com.xjw.exam.dao;

import com.xjw.exam.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {

    /**
     * 列出教师登录表
     *
     * @return
     */
    List<Teacher> queryTeacher();

    /**
     * 根据教师id查询教师登录信息
     * @param id
     * @return
     */
    Teacher queryTeacherById(String id);

    /**
     * 查询教师信息
     * @param teacher
     * @return
     */
    Teacher checkTeacherLogin(Teacher teacher);

    /**
     * 插入教师登录信息
     *
     * @param teacher
     * @return
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 更新教师登录信息
     *
     * @param teacher
     * @return
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 删除教师登录信息
     *
     * @param id
     * @return
     */
    public int deleteTeacher(String id);
}
