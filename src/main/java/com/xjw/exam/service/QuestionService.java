package com.xjw.exam.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjw.exam.dao.GradeViewDao;
import com.xjw.exam.dao.QuestionDao;
import com.xjw.exam.dao.QuestionSetsDao;
import com.xjw.exam.entity.*;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 考试题目 service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionSetsDao questionSetsDao;
    @Autowired
    private GradeViewDao gradeDao;
    @Autowired
    private TestHistoryService testHistoryService;

    /*
     * 所有考试题目
     */
    public List<Question> findAllList() {
        return questionDao.findAllList();
    }

    /**
     * 对考试题目分页
     * @param pageNum
     * @param pageSize
     * @return 分页信息
     */
    public PageInfo<Question> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> list = questionDao.findAllList();
        PageInfo<Question> page = new PageInfo<>(list);
        return page;
    }

    /*
     * 添加题目
     */
    public JSONResult insert(Question question) {
        question.setCreateTime(new Date());
        int addResult = questionDao.insert(question);
        if(addResult > 0){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("添加信息失敗！");
        }
    }

    /**
     * 根据问卷编号获取相应题目集合
     * @param paperId
     * @return
     */
    public PageInfo<Question> selectByIdSet(Integer paperId) {
        QuestionSets paper = questionSetsDao.get(paperId);
        // 获取考卷中的所有题集Q1Q2Q3...
        String tempStr =  paper.getInclude();

        // 正则表达 - 获取题目ID集合
        Pattern regx= Pattern.compile("(\\d+)");
        Matcher m=regx.matcher(tempStr);
        List<Integer> questionIdSet = new ArrayList<>();
        while (m.find()) {
            String find = m.group(1);
            // 加入集合
            questionIdSet.add(Integer.valueOf(find));
        }
        // 默认分页大小
        PageHelper.startPage(1,10);
        List<Question> list = questionDao.selectByIdSet(questionIdSet);
        PageInfo<Question> page = new PageInfo<>(list);

        return page;
    }

    /**
     * 考試中
     * @param paper
     * @param student
     * @return
     */
    public Map<String, Object> questionStram(QuestionSets paper, Student student) {
        Map<String, Object> examing = new HashMap<>();

        /*获取考卷中的所有题集Q1Q2Q3...*/
        String tempStr =  paper.getInclude();
        // 正则表达 - 获取题目ID集合
        Pattern regx= Pattern.compile("(\\d+)");
        Matcher m=regx.matcher(tempStr);
        List<Integer> questionIdSet = new ArrayList<>();
        while (m.find()) {
            String find = m.group(1);
            // 加入集合
            questionIdSet.add(Integer.valueOf(find));
        }
        // 試題數量
        int total = questionIdSet.size();
        examing.put("total", total);

        /*
         * 等待考試的試題
         */
        List<Question> examProcess = questionDao.getExamQuestion(questionIdSet, paper.getId(), student.getId());
        // 當前試題的剩餘數量
        int count = examProcess.size();
        // 考試進度 0-未做完題目 1-完成所有題目
        int process ;
        if (count > 0){
            process = 0;
        }else {
            process = 1;
        }

        examing.put("process", process);
        examing.put("questions", examProcess);
        examing.put("residualItem", count);

        int hasDoneTotal = testHistoryService.hasDoneTotal(student.getId(), paper.getId());
        examing.put("hasDone", hasDoneTotal);
        // 獲取當前分數
        float score = 0;
        GradeView stuGrade = gradeDao.get(student.getId());
        if(stuGrade != null){
            score = stuGrade.getTotal();
        }

        examing.put("score", score);

        return examing;
    }


    public JSONResult judge(HttpServletRequest request, Question question) {
        HttpSession session = request.getSession();
        QuestionSets paper = (QuestionSets) session.getAttribute("paper");
        Student student = (Student)session.getAttribute("user");

        Question judgeQuestion = questionDao.getByElement(question);
        TestHistory testHistory = new TestHistory(student.getId(),paper.getId(),
                question.getId(),question.getAnswer(),new Date());
        Map<String,Object> judgeReturn = new HashMap<>();
        if (judgeQuestion != null){
            testHistory.setStuScore(judgeQuestion.getScore());
            judgeReturn.put("score", judgeQuestion.getScore());
            // 回答正確
            judgeReturn.put("correct", true);
        }else{
            testHistory.setStuScore(0);
            // 回答錯圖
            judgeReturn.put("score", 0);
            judgeReturn.put("correct", false);
        }

        judgeReturn.put("answer",(questionDao.get(question.getId())).getAnswer());
        testHistoryService.insert(testHistory);
        return new JSONResult(200,"處理成功！",judgeReturn);
    }

    public boolean delete(String id) {
        int result = questionDao.delete(id);
        if( result > 0){
            return true;
        }else{
            throw new RuntimeException("删除題目信息失败！");
        }
    }
}
