package com.xjw.exam.service;

import com.xjw.exam.dao.GradeViewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生成绩service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class GradeViewService {

    @Autowired
    private GradeViewDao gradeDao;
}
