package com.xjw.exam.web;

import com.github.pagehelper.PageInfo;
import com.xjw.exam.entity.Question;
import com.xjw.exam.entity.Teacher;
import com.xjw.exam.service.QuestionService;
import com.xjw.exam.service.QuestionSetsService;
import com.xjw.exam.service.TestHistoryService;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 考试题目 Controller层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@RestController     // RestController = @Controller + @ResponBody
@RequestMapping("/teacher/questions")
//@CrossOrigin  跨域注解
public class QuestionController {

    @Autowired
    private QuestionSetsService questionSetsService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TestHistoryService testHistoryService;

    /**
     * 获取所有考试问题集 - 列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "findAllList", method = RequestMethod.GET)
    public List<Question> findAllList(HttpServletRequest request, HttpServletResponse response){
        return questionService.findAllList();
    }

    /**
     * 获取所有考试问题 - 分页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    public PageInfo<Question> findPage(HttpServletRequest request, HttpServletResponse response){
        PageInfo<Question> findpage;
        String strNum = request.getParameter("pageNum");
        String strSize = request.getParameter("pageSize");
        // System.out.println("num=" + strNum + "size: " + strSize);
        if(strNum != null && strSize != null) {
            int pageNum = Integer.parseInt(strNum);
            int pageSize = Integer.parseInt(strSize);
            findpage = questionService.findByPage(pageNum, pageSize);
        }else{
            // 默认分页大小为 第1页/显示10条数据
             findpage = questionService.findByPage(1, 10);
        }
        return findpage;
    }

    /**
     * 根据试卷编号查询对应题集
     * @param request
     * @param paperId
     * @return 题目列表
     */
    @RequestMapping(value = "findPaperList", method = RequestMethod.GET)
    public PageInfo<Question> findPaperList(HttpServletRequest request, HttpServletResponse response, Integer paperId) {
        if (paperId == -1) {
            // 获取所有考试问卷
            return findPage(request, response);
        } else {
            return questionService.selectByIdSet(paperId);
        }
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

        return questionService.insert(question);
    }

    @RequestMapping(value = "judge", method = RequestMethod.POST)
    public JSONResult judge(HttpServletRequest request, Question question){
        if (question.getAnswer() != null){
            return questionService.judge(request, question);
        }else{
            return JSONResult.errorMsg("請選擇答案回答問題");
        }
    }

    @RequestMapping(value = "delete" , method = RequestMethod.POST)
    public JSONResult delete(String id){
        boolean res1 = questionService.delete(id);
        boolean res2 = testHistoryService.deleteByQuestion(id);
        boolean res3 = questionSetsService.updateInclude(id);

        if(res1 && res2 && res3){
            return new JSONResult("刪除成功！");
        }else{
            return JSONResult.error();
        }
    }
}
