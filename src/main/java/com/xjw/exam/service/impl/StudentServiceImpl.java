package com.xjw.exam.service.impl;

import com.xjw.exam.dao.StudentDao;
import com.xjw.exam.entity.Student;
import com.xjw.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getStudentList() {
        return studentDao.queryStudent();
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.queryStudentById(id);
    }

    @Transactional
    @Override
    public boolean addStudent(Student student) {
        if(student.getName()!=null && !"".equals(student.getName())){
            try {
                int effectedNum = studentDao.insertStudent(student);
                if(effectedNum > 0){
                    return true;
                }else{
                    throw new RuntimeException("插入学生注册信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("插入学生注册信息失败:" + e.getMessage());
            }
        }else {
            throw new RuntimeException("学生姓名不能为空！");
        }
    }

    @Override
    public boolean modifyStudent(Student student) {
        if(student.getId()!=null && student.getId() > 0){
            try {
                int effectedNum = studentDao.updateStudent(student);
                if(effectedNum > 0){
                    return true;
                }else{
                    throw new RuntimeException("更新学生注册信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("更新学生注册信息失败:" + e.getMessage());
            }
        }else {
            throw new RuntimeException("学生信息不能为空！");
        }
    }

    @Override
    public boolean deleteStudnet(int id) {
        if(id > 0){
            try {
                int effectedNum = studentDao.deleteStudnet(id);
                if(effectedNum > 0){
                    return true;
                }else{
                    throw new RuntimeException("删除学生注册信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("删除学生注册信息失败:" + e.getMessage());
            }
        }else {
            throw new RuntimeException("id不能为空！");
        }
    }
}
