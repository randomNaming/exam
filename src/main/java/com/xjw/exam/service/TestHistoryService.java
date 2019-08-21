package com.xjw.exam.service;

import com.xjw.exam.dao.TestHistoryDao;
import com.xjw.exam.entity.TestHistory;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 考试历史记录 service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class TestHistoryService {

    @Autowired
    private TestHistoryDao testHistoryDao;

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
}
