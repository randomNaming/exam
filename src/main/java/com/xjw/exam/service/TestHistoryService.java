package com.xjw.exam.service;

import com.xjw.exam.dao.TestHistoryDao;
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
}
