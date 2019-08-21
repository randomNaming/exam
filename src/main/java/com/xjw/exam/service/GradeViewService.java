package com.xjw.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjw.exam.dao.GradeViewDao;
import com.xjw.exam.entity.GradeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生成绩service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class GradeViewService {

    @Autowired
    private GradeViewDao gradeDao;

    public int getPaperScore(String studentId) {
        GradeView grade = gradeDao.get(studentId);
        return grade.getTotal();
    }


    public PageInfo<GradeView> searchInfo(GradeView searchParam, Integer leftScoresRange, Integer rightScoresRange, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<GradeView> list = gradeDao.search(searchParam,leftScoresRange,rightScoresRange);
        PageInfo<GradeView> page = new PageInfo<>(list);
        return page;
    }
}
