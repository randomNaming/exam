package com.xjw.exam.service;

import com.xjw.exam.entity.Student;

import java.util.List;

public interface StudentService {

    /**
     * 列出学生登录表
     *
     * @return
     */
    List<Student> getStudentList();

    /**
     * 根据学生id查询学生登录信息
     * @param id
     * @return
     */
    Student getStudentById(int id);

    /**
     * 插入学生登录信息
     *
     * @param student
     * @return
     */
    boolean addStudent(Student student);

    /**
     * 更新学生登录信息
     *
     * @param student
     * @return
     */
    boolean modifyStudent(Student student);

    /**
     * 删除学生登录信息
     *
     * @param id
     * @return
     */
    boolean deleteStudnet(int id);
}
