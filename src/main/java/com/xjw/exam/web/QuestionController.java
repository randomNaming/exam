package com.xjw.exam.web;

import com.xjw.exam.entity.Question;
import com.xjw.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/questions")
@CrossOrigin  // 跨域注解
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "findAllList", method = RequestMethod.GET)
    public List<Question> findAllList(){
        return questionService.findAllList();
    }
}
