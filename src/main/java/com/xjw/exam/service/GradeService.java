package com.xjw.exam.service;

import com.xjw.exam.dao.GradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    @Autowired
    private GradeDao gradeDao;
}
