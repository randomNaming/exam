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
@RequestMapping("/superadmin")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "listStudent", method = RequestMethod.GET)
    private JSONResult listStudent(){
        return studentService.findList();
    }

    @RequestMapping(value = "getStudentById", method = RequestMethod.GET)
    private Map<String, Object> getStudentById(String id){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Student student = studentService.getStudentById(id);
        modelMap.put("student", student);
        return modelMap;
    }

    @RequestMapping(value = "checkLogin", method = RequestMethod.POST)
    private Map<String, Object> checkLogin(Student student){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.checkStudentLogin(student));
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
