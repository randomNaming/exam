package com.xjw.exam.service;


import com.xjw.exam.dao.QuestionDao;
import com.xjw.exam.entity.Question;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> findAllList() {
        return questionDao.findAllList();
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
}
