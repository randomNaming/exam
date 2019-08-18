package com.xjw.exam.web;

import com.github.pagehelper.PageInfo;
import com.xjw.exam.entity.Question;
import com.xjw.exam.entity.Teacher;
import com.xjw.exam.service.QuestionService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/questions")
//@CrossOrigin  跨域注解
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取所有考试问题
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "findAllList", method = RequestMethod.GET)
    public PageInfo<Question> findAllList(HttpServletRequest request, HttpServletResponse response){
        PageInfo<Question> findpage;
        String strNum = request.getParameter("pageNum");
        String strSize = request.getParameter("pageSize");
        if(strNum != null && strSize != null) {
            int pageNum = Integer.parseInt(strNum);
            int pageSize = Integer.parseInt(strSize);
            System.out.println("page == >" + pageNum +"  --- "  + pageSize);
            findpage = questionService.findByPage(pageNum, pageSize);
        }else{
            // 默认分页大小为 第1页/显示10条数据
             findpage = questionService.findByPage(1, 10);
        }
        return findpage;
    }

    /**
     * 根据试卷编号查询对应题集
     * @param pageNum
     * @param pageSize
     * @param paperId
     * @return 题目列表
     */
    @RequestMapping(value = "findPaperList", method = RequestMethod.GET)
    public PageInfo<Question> findPaperList(String paperId){
        PageInfo<Question> QuestionSet = questionService.selectByIdSet(paperId);

        return QuestionSet;
    }

    /**
     * 教师权限用户添加试题
     * @param request
     * @param question
     * @return
     */
    @RequestMapping(value = "addQuestion", method = RequestMethod.POST)
    public JSONResult addQuestion(HttpServletRequest request, Question question){

        HttpSession teacherSession = request.getSession();
        question.setTeacherId(((Teacher)teacherSession.getAttribute("user")).getId());

        JSONResult result = questionService.insert(question);
        return result;
    }
}
