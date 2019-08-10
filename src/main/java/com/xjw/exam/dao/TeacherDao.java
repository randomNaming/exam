package com.xjw.exam.dao;

import com.xjw.exam.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教师DAO接口
 * @author SHXjw
 * @version 20190910
 */
@Repository
public interface TeacherDao extends CurdDao<Teacher>{

    /**
     * 查询教师信息
     * @param teacher
     * @return
     */
    Teacher checkTeacherLogin(Teacher teacher);
}
