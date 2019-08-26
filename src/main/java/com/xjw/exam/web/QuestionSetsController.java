package com.xjw.exam.web;

import com.xjw.exam.entity.Question;
import com.xjw.exam.entity.QuestionSets;
import com.xjw.exam.entity.Student;
import com.xjw.exam.entity.Teacher;
import com.xjw.exam.service.QuestionService;
import com.xjw.exam.service.QuestionSetsService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/paperQuestionSets")
//@CrossOrigin  局部跨域注解 - if you need part of @Intefaces, you can disable it.
public class QuestionSetsController {

    @Autowired
    private QuestionSetsService questionSetsService;
    @Autowired
    private QuestionService questionService;

    // TODO:所有问卷显示并分页
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
    public JSONResult addQuestionSet(HttpServletRequest request,QuestionSets questionSet){
        // 运用会话session获取用户名 - author
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
        questionSet.setAuthor(teacher.getName());

        JSONResult result = questionSetsService.insert(questionSet);
        return result;
    }

    @RequestMapping(value = "examing", method = RequestMethod.POST)
    public Map<String, Object> examing(HttpServletRequest request, Integer id){
        QuestionSets paper = questionSetsService.get(id);
        HttpSession session = request.getSession();
        session.setAttribute("paper", paper);
        Student student = (Student)session.getAttribute("user");
        return questionService.questionStram(paper, student);
    }

    @RequestMapping(value = "removeQuestionSet", method = RequestMethod.GET)
    public JSONResult removeQuestionSet(String id){
        return questionSetsService.deleteStudnet(id);
    }
}