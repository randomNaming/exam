package com.xjw.exam.web;

import com.github.pagehelper.PageInfo;
import com.xjw.exam.entity.Question;
import com.xjw.exam.service.QuestionService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/questions")
@CrossOrigin  // 跨域注解
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "findAllList", method = RequestMethod.GET)
    public Map<String, Object> findAllList(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        String strNum = request.getParameter("pageNum");
        String strSize = request.getParameter("pageSize");
        System.out.println("page == >" + strNum +" "  + strSize);
        if(strNum != null && strSize != null) {
            int pageNum = Integer.parseInt(strNum);
            int pageSize = Integer.parseInt(strSize);
            System.out.println("page == >" + pageNum +"  --- "  + pageSize);
            PageInfo<Question> findpage = questionService.findByPage(pageNum, pageSize);
            result.put("data",findpage);
        }else{
            System.out.println("else{} === ");
            List<Question> findList = questionService.findAllList();
            result.put("data",findList);
        }
        return result;
    }

    @RequestMapping(value = "addQuestion", method = RequestMethod.POST)
    public JSONResult addQuestion(Question question){
        JSONResult result = questionService.insert(question);
        return result;
    }
}
