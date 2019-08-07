package com.xjw.exam.web;

import com.xjw.exam.entity.Student;
import com.xjw.exam.service.StudentService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 所有学生列表
     * @return
     */
    @RequestMapping(value = "listStudent", method = RequestMethod.GET)
    private JSONResult listStudent(){
        return studentService.findList();
    }

    /**
     * 根据id获取学生幸喜
     * @param id 学生编号
     * @return 学生信息
     */
    @RequestMapping(value = "getStudentById", method = RequestMethod.GET)
    private Map<String, Object> getStudentById(String id){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Student student = studentService.getStudentById(id);
        modelMap.put("student", student);
        return modelMap;
    }

    /**
     * 学号查重
     * @param id 学生编号
     * @return success 0-重复 1-新用户
     */
    @RequestMapping(value = "checkById", method = RequestMethod.GET)
    private Map<String, Object> checkById(String id){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Student student = studentService.getStudentById(id);
        if (student != null){
            modelMap.put("success", 0);
            // modelMap.put("student", student);
        }else{
            modelMap.put("success", 1);
            modelMap.put("student", student);
        }
        return modelMap;
    }

    /**
     * 检查登录
     * @param student
     * @return
     */
    @RequestMapping(value = "checkLogin", method = RequestMethod.POST)
    private Map<String, Object> checkLogin(Student student){
        System.out.println(student.getPassword());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = studentService.checkStudentLogin(student);
        System.out.println(modelMap);
        return modelMap;
    }

    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    private Map<String, Object> addStudent(Student student){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.addStudent(student));
        return modelMap;
    }

    @RequestMapping(value = "modifyStudent", method = RequestMethod.POST)
    private Map<String, Object> modifyStudent(Student student){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.modifyStudent(student));
        return modelMap;
    }

    @RequestMapping(value = "removeStudent", method = RequestMethod.GET)
    private Map<String, Object> removeStudent(String id){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.deleteStudnet(id));
        return modelMap;
    }
}
