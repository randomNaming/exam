package com.xjw.exam.service;

import com.xjw.exam.dao.TeacherDao;
import com.xjw.exam.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教师信息 service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class TeacherService {
    
    @Autowired
    private TeacherDao teacherDao;

    public Teacher getTeacherById(String id) {
        return teacherDao.get(id);
    }

    public Teacher checkTeacherLogin(Teacher teacher) {
        return teacherDao.checkTeacherLogin(teacher);
    }

    @Transactional
    public boolean addTeacher(Teacher teacher) {
        if(teacher.getName()!=null && !"".equals(teacher.getName())){
            try {
                int effectedNum = teacherDao.insert(teacher);
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
}
