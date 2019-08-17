package com.xjw.exam.web;

import com.xjw.exam.entity.Question;
import com.xjw.exam.entity.QuestionSets;
import com.xjw.exam.service.QuestionService;
import com.xjw.exam.service.QuestionSetsService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/paperQuestionSets")
//@CrossOrigin  局部跨域注解 - if you need part of @Intefaces, you can disable it.
public class QuestionSetsController {

    @Autowired
    private QuestionSetsService questionSetsService;
    @Autowired
    private QuestionService questionService;

    public List<Question> findPaper(HttpServletResponse response, HttpServletRequest request){
        // 获取试卷id
        String paperId = request.getParameter("id");
        List<Question> questionList = null;
        return questionList;
    }

    /**
     * 查询所有试卷
     * @return
     */
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