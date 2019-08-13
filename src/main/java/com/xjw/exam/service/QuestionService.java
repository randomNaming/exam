package com.xjw.exam.service;


import com.xjw.exam.dao.QuestionDao;
import com.xjw.exam.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> findAllList() {
        return questionDao.findAllList();
    }
}
