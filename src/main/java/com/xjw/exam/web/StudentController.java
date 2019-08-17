package com.xjw.exam.web;

import com.xjw.exam.entity.Student;
import com.xjw.exam.service.StudentService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/student")
//@CrossOrigin  局部跨域注解 - if you need part of @Intefaces, you can disable it.
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public JSONResult getCookies(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            Student student = (Student)session.getAttribute("user");
            return new JSONResult(200,"ok!",student);
        }else{

            System.out.println("TestCookie()===================" + (Student)session.getAttribute("user"));
            return JSONResult.errorMsg("getCookie" + request.getSession());
        }
    }

    /**
     * 所有学生列表
     */
    @RequestMapping(value = "listStudent", method = RequestMethod.GET)
    public JSONResult listStudent(){
        return studentService.findList();
    }

    /**
     * 根据id获取学生幸喜
     * @param id 学生编号
     * @return 学生信息
     */
    @RequestMapping(value = "getStudentById", method = RequestMethod.GET)
    public Map<String, Object> getStudentById(String id){
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
    public Map<String, Object> checkById(String id){
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
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, Student student){
        System.out.println("id = " + student.getId());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();

        Student user = studentService.checkStudentLogin(student);
        if (user != null){
            session.setAttribute("user", user);
            modelMap.put("msg","登錄成功！");
            modelMap.put("success", true);
            System.out.println("session ---> " + session.getId());
        }else{
            modelMap.put("msg","账号或密码错误！");
            modelMap.put("success", false);
        }
        System.out.println(modelMap);
        return modelMap;
    }

    /**
     * 添加学生
     * @param student
     */
    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    public Map<String, Object> addStudent(Student student){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.addStudent(student));
        return modelMap;
    }

    /**
     * 修改学生数据
     * @param student
     */
    @RequestMapping(value = "modifyStudent", method = RequestMethod.POST)
    public Map<String, Object> modifyStudent(Student student){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.modifyStudent(student));
        return modelMap;
    }

    /**
     * 删除学生
     * @param id
     */
    @RequestMapping(value = "removeStudent", method = RequestMethod.GET)
    public Map<String, Object> removeStudent(String id){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", studentService.deleteStudnet(id));
        return modelMap;
    }
}
