package com.xjw.exam.dao;

import com.xjw.exam.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * 学生DAO接口
 * @author SHXjw
 * @version 20190910
 */
@Repository
public interface StudentDao extends CurdDao<Student>{

    /**
     * 查询登录信息
     * @param student
     * @return
     */
    public Student checkStudentLogin(Student student);
}
