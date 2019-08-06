package com.xjw.exam.dao;

import com.xjw.exam.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
    Student queryStudentById(String id);

    Student checkStudentLogin(Student student);

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
    int deleteStudnet(String id);
}
