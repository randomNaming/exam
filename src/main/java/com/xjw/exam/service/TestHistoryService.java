package com.xjw.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjw.exam.dao.QuestionDao;
import com.xjw.exam.dao.TestHistoryDao;
import com.xjw.exam.entity.Question;
import com.xjw.exam.entity.Student;
import com.xjw.exam.entity.TestHistory;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试历史记录 service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class TestHistoryService {

    @Autowired
    private TestHistoryDao testHistoryDao;
    @Autowired
    private QuestionDao questionDao;

    public int hasDoneTotal(String stuId, Integer paperId) {
        return testHistoryDao.count(stuId, paperId);
    }

    public JSONResult insert(TestHistory testHistory) {
        int result = testHistoryDao.insert(testHistory);
        if(result > 0){
            return new JSONResult("添加成功");
        }else{
            return JSONResult.error();
        }
    }

    public PageInfo<TestHistory> testLog(int pageNum, int pageSize, Student student, Integer paperId) {
        PageHelper.startPage(pageNum,pageSize);
        List<TestHistory> list = testHistoryDao.testRecord(student.getId(),paperId);
        PageInfo<TestHistory> page = new PageInfo<>(list);
        for (TestHistory test:list){
            Question question = questionDao.get(test.getqId());
            test.setQuestion(question);
        }
        return page;

    }

    public boolean deleteByQuestion(String id) {
        int result = testHistoryDao.deleteByQuestionId(id);
        if(result > 0 ){
            return true;
        }else {
            throw new RuntimeException("刪除考試記錄下的對應題目失敗！");
        }
    }
}
