package com.xjw.exam.web;

import com.xjw.exam.entity.LoginHistory;
import com.xjw.exam.entity.Student;
import com.xjw.exam.entity.Teacher;
import com.xjw.exam.entity.User;
import com.xjw.exam.service.LoginHistoryService;
import com.xjw.exam.service.StudentService;
import com.xjw.exam.service.TeacherService;
import com.xjw.exam.utils.JSONResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户访问网关
 *
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-17
 */
@RestController
public class UserAccessController {

    /**
     * 学生权限
     */
    public final static String LEVEL_STUDENT = "0";

    /**
     * 教师权限
     */
    public final static String LEVEL_TEACHER = "1";

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private LoginHistoryService loginHistoryService;

    private Logger accessLogger = Logger.getLogger(UserAccessController.class);

    /**
     * 登录会话创建
     *
     * @describe 将请求的用户信息与数据库
     *           校对后创建会话
     * @param request
     * @param response
     */
    @PostMapping(value = "/login")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response){


        Map<String, Object> logIn = new HashMap<>(16);

        String level = request.getParameter("level");
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        accessLogger.info("session.id = " + session.getId());

        if (LEVEL_STUDENT.equals(level)){

            Student studentLogin = new Student(id, password);

            Student student = studentService.checkStudentLogin(studentLogin);
            if(student != null){
                // 可能後期會用到loginTime & level
                session.setAttribute("user", student);

                // 后期加入JSONResult
                logIn.put("msg","登錄成功！");
                logIn.put("success", true);
            }else{
                logIn.put("msg","账号或密码错误！");
                logIn.put("success", false);
            }
        }else if (LEVEL_TEACHER.equals(level)){
            Teacher teacherLogin = new Teacher(id, password);

            Teacher teacher = teacherService.checkTeacherLogin(teacherLogin);
            if(teacher != null){
                session.setAttribute("user", teacher);

                // 后期加入JSONResult
                logIn.put("msg","登錄成功！");
                logIn.put("success", true);
            }

        }else{
            logIn.put("msg","请选择用户身份！");
            logIn.put("success", false);
        }

        /*
         * 刷新登錄記錄
         */
        LoginHistory loginRecord = new LoginHistory(id, level);
        int isNewRecord = loginHistoryService.count(loginRecord);
        accessLogger.info("isNewRecord = " + isNewRecord);
        if(isNewRecord != 0){
            loginHistoryService.update(loginRecord);
        }else {
            loginHistoryService.insert(loginRecord);
        }
        return logIn;
    }

    /**
     * 用户登出会话销毁
     *
     * @describe 将当前用户的会话销毁
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/logout")
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response){


        Map<String, Object> logOut = new HashMap<>(16);


        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1000*60*60);
        accessLogger.info("logout === id: " + session.getId());
        String sessionKey = "user";
        if (session.getAttribute(sessionKey)!=null) {
            session.removeAttribute(sessionKey);

            logOut.put("msg", "用户已退出");
            logOut.put("success", true);
        }else{
            logOut.put("msg", "用户不存在");
            logOut.put("success", false);
        }

        return logOut;
    }

    /**
     * 根据当前会话获取用户信息
     * @param request
     * @return 用户信息
     */
    @GetMapping(value = "/getUserInfo")
    public JSONResult getUserInfo(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        return new JSONResult(200,"成功获取用户信息",user);
    }
}
