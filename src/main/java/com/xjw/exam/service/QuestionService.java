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

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionSetsDao questionSetsDao;

    public List<Question> findAllList() {
        return questionDao.findAllList();
    }

    public PageInfo<Question> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> list = questionDao.findAllList();
        PageInfo<Question> page = new PageInfo<Question>(list);
        return page;
    }

    public JSONResult insert(Question question) {
        question.setCreateTime(new Date());
        int addResult = questionDao.insert(question);
        if(addResult > 0){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("添加信息失敗！");
        }
    }

    public PageInfo<Question> selectByIdSet(String paperId) {
        QuestionSets paper = questionSetsDao.get(paperId);
        // 获取考卷中的所有题集Q1Q2Q3...
        String tempStr =  paper.getInclude();
        String spiltRules = "/\\d+/g";
        String[] QuestionIdArray = tempStr.split(spiltRules);

        for (String s : QuestionIdArray) {

            System.out.println("--> " + s);

        }
        // 默认分页大小
        PageHelper.startPage(1,10);

        List<Question> list = questionDao.selectByIdSet(QuestionIdArray);
        PageInfo<Question> page = new PageInfo<Question>(list);

        return page;
    }
}
