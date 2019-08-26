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
//@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 根据id获取教师幸喜
     * @param id 教师编号
     * @return 教师信息
     */
    @RequestMapping(value = "getTeacherById", method = RequestMethod.GET)
    public Map<String, Object> getTeacherById(String id){
        Map<String, Object> modelMap = new HashMap<>();
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
    public Map<String, Object> checkById(String id){
        Map<String, Object> modelMap = new HashMap<>();
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher != null){
            modelMap.put("success", 0);
        }else{
            modelMap.put("success", 1);
            modelMap.put("teacher", teacher);
        }
        return modelMap;
    }

    /**
     * 添加教师
     * @param teacher
     */
    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    public Map<String, Object> addTeacher(Teacher teacher){
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success", teacherService.addTeacher(teacher));
        return modelMap;
    }
}
