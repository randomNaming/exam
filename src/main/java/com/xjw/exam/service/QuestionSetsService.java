package com.xjw.exam.service;

import com.xjw.exam.dao.QuestionSetsDao;
import com.xjw.exam.entity.QuestionSets;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 试卷集合 service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class QuestionSetsService {

    @Autowired
    private QuestionSetsDao questionSetsDao;

    public QuestionSets get(Integer paperId){
        return questionSetsDao.get(paperId);
    }

    public List<QuestionSets> findAllList() {
        return questionSetsDao.findAllList();
    }

    public JSONResult deleteStudnet(String id) {

        int result = questionSetsDao.delete(id);
        if(result > 0){
            return new JSONResult(200,"删除成功!",result);
        }else {
            return JSONResult.errorMsg("删除失败");
        }
    }


    public JSONResult insert(QuestionSets questionSet) {
        questionSet.setCreateTime(new Date());
        int result = questionSetsDao.insert(questionSet);
        if(result > 0){
            return new JSONResult("添加成功");
        }else{
            return JSONResult.error();
        }
    }

    public boolean updateInclude(String qId) {
        List<Integer> idList = questionSetsDao.tempByUpdateInclude(qId);
        if(idList != null) {
            int result = questionSetsDao.updateInclude(qId, idList);
            if(result > 0 ){
                return true;
            }else{
                throw new RuntimeException("更新考卷題目失敗！");
            }
        }else {
            return false;
        }
    }
}
