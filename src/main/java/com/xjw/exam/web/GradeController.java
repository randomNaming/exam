package com.xjw.exam.web;

import com.xjw.exam.entity.Grade;
import com.xjw.exam.service.GradeService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/stuGrade")
@CrossOrigin  // 跨域注解
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "searchStuScore", method = RequestMethod.GET)
    public JSONResult searchStuScore(HttpServletRequest request, HttpServletResponse response, Float score2){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String score1 = request.getParameter("score1");


        System.out.println("log...." + id);
        System.out.println("log...." + name);
        System.out.println("log...." + score1);
        System.out.println("log...." + score2);
        return JSONResult.error();
    }
}
