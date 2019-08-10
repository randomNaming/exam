package com.xjw.exam.service;

import com.xjw.exam.dao.StudentDao;
import com.xjw.exam.entity.Student;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * 查询所有学生列表
     * @return
     */
    public List<Student> getStudentList() {
        return studentDao.findAllList();
    }

    public JSONResult findList(){
        List<Student> list = getStudentList();
        if(list != null){
            return new JSONResult(200,"请求成功",list);
        }else{
            return JSONResult.error();
        }
    }

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    public Student getStudentById(String id) {
        return studentDao.get(id);
    }

    @Transactional
    public boolean addStudent(Student student) {
        if(student.getName()!=null && !"".equals(student.getName())){
            try {
                int effectedNum = studentDao.insert(student);
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

    /**
     * 更新学生注册信息
     * @param student
     * @return
     */
    public boolean modifyStudent(Student student) {
        if(student.getId()!=null ){
            try {
                int effectedNum = studentDao.update(student);
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

    /**
     * 删除学生注册信息
     * @param id
     * @return
     */
    public boolean deleteStudnet(String id) {
        if(id != null){
            try {
                int effectedNum = studentDao.delete(id);
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

    public Map<String, Object> checkStudentLogin(Student student) {
        Map<String, Object> result = new HashMap<String, Object>();
        Student checkStudent = studentDao.checkStudentLogin(student);
        if(checkStudent != null){
            result.put("success", true);
            result.put("msg", "验证通过！");
        }else{
            result.put("success", false);
            result.put("msg", "用户名或密码错误！");
        }
        return result;
    }
}
