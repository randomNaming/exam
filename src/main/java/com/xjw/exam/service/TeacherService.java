package com.xjw.exam.service;

import com.xjw.exam.dao.TeacherDao;
import com.xjw.exam.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherDao teacherDao;

    public Teacher getTeacherById(String id) {
        return teacherDao.get(id);
    }

    public Map<String, Object> checkTeacherLogin(Teacher teacher) {
        Map<String, Object> result = new HashMap<String, Object>();
        Teacher checkTeacher = teacherDao.checkTeacherLogin(teacher);
        if(checkTeacher != null){
            result.put("success", true);
            result.put("msg", "验证通过！");
        }else{
            result.put("success", false);
            result.put("msg", "用户名或密码错误！");
        }
        return result;
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
