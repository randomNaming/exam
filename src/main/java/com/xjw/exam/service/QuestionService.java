package com.xjw.exam.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjw.exam.dao.QuestionDao;
import com.xjw.exam.dao.QuestionSetsDao;
import com.xjw.exam.entity.Question;
import com.xjw.exam.entity.QuestionSets;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            String find = m.group(1).toString();
            // 加入集合
            questionIdSet.add(Integer.valueOf(find));
        }
//        for (Integer s : questionIdSet) {
//            System.out.println("--> " + s +"<---");
//        }
        // 默认分页大小
        PageHelper.startPage(1,10);
        List<Question> list = questionDao.selectByIdSet(questionIdSet);
        PageInfo<Question> page = new PageInfo<>(list);

        return page;
    }
}
