package com.xjw.exam.web;

import com.xjw.exam.entity.QuestionSets;
import com.xjw.exam.service.QuestionSetsService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/paperQuestionSets")
@CrossOrigin  // 跨域注解
public class QuestionSetsController {

    @Autowired
    private QuestionSetsService questionSetsService;

    @RequestMapping(value = "findAllList", method = RequestMethod.GET)
    public List<QuestionSets> findAllList(){
        return questionSetsService.findAllList();
    }

    @RequestMapping(value = "addQuestionSet", method = RequestMethod.POST)
    public JSONResult addQuestionSet(QuestionSets questionSet){
        // 运用会话session获取用户名; - author
        JSONResult result = questionSetsService.insert(questionSet);
        return result;
    }

    @RequestMapping(value = "removeQuestionSet", method = RequestMethod.GET)
    public JSONResult removeQuestionSet(String id){
        JSONResult result = questionSetsService.deleteStudnet(id);
        return result;
    }
}