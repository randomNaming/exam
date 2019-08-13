package com.xjw.exam.service;

import com.xjw.exam.dao.QuestionSetsDao;
import com.xjw.exam.entity.QuestionSets;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionSetsService {

    @Autowired
    private QuestionSetsDao questionSetsDao;

    public List<QuestionSets> findAllList() {
        return questionSetsDao.findAllList();
    }

    public JSONResult deleteStudnet(String id) {

        int result = questionSetsDao.delete(id);
        if(result > 0){
            JSONResult jsonResult = new JSONResult(200,"删除成功!",result);
            return jsonResult;
        }else {
            return JSONResult.errorMsg("删除失败");
        }
    }


    public JSONResult insert(QuestionSets questionSet) {
        int result = questionSetsDao.insert(questionSet);
        if(result > 0){
            return new JSONResult("添加成功");
        }else{
            return JSONResult.error();
        }
    }
}
