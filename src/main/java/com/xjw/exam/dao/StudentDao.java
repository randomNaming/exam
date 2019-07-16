package com.xjw.exam.dao;

import com.xjw.exam.entity.Student;

import java.util.List;

public interface StudentDao {

    /**
     * 列出学生登录表
     *
     * @return
     */
    List<Student> queryStudent();

    /**
     * 根据学生id查询学生登录信息
     * @param id
     * @return
     */
    Student queryStudentById(int id);

    /**
     * 插入学生登录信息
     *
     * @param student
     * @return
     */
    int insertStudent(Student student);

    /**
     * 更新学生登录信息
     *
     * @param student
     * @return
     */
    int updateStudent(Student student);

    /**
     * 删除学生登录信息
     *
     * @param id
     * @return
     */
    int deleteStudnet(int id);
}
