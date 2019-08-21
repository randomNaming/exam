package com.xjw.exam.web;

import com.xjw.exam.service.GradeViewService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 学生成绩 - 控制网关
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-18
 */
@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/stuGrade")
// @CrossOrigin  局部跨域注解
public class GradeController {

    @Autowired
    private GradeViewService gradeViewService;

    @RequestMapping(value = "searchStuScore", method = RequestMethod.GET)
    public JSONResult searchStuScore(HttpServletRequest request, HttpServletResponse response,Float LeftScoresRange, Float RightScoresRange){

        String id = request.getParameter("id");
        String name = request.getParameter("name");


        return JSONResult.error();
    }

}
