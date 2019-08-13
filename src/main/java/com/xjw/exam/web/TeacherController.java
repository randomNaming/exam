package com.xjw.exam.web;

import com.xjw.exam.entity.Teacher;
import com.xjw.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 根据id获取教师幸喜
     * @param id 教师编号
     * @return 教师信息
     */
    @RequestMapping(value = "getTeacherById", method = RequestMethod.GET)
    private Map<String, Object> getTeacherById(String id){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Teacher teacher = teacherService.getTeacherById(id);
        modelMap.put("teacher", teacher);
        return modelMap;
    }

    /**
     * 学号查重
     * @param id 教师编号
     * @return success 0-重复 1-新用户
     */
    @RequestMapping(value = "checkById", method = RequestMethod.GET)
    private Map<String, Object> checkById(String id){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher != null){
            modelMap.put("success", 0);
            // modelMap.put("teacher", teacher);
        }else{
            modelMap.put("success", 1);
            modelMap.put("teacher", teacher);
        }
        return modelMap;
    }

    /**
     * 检查登录
     * @param teacher
     * @return
     */
    @RequestMapping(value = "checkLogin", method = RequestMethod.POST)
    private Map<String, Object> checkLogin(Teacher teacher){
        System.out.println(teacher.getPassword());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap = teacherService.checkTeacherLogin(teacher);
        System.out.println(modelMap);
        return modelMap;
    }

    /**
     * 添加教师
     * @param teacher
     */
    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    private Map<String, Object> addTeacher(Teacher teacher){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", teacherService.addTeacher(teacher));
        return modelMap;
    }
}
