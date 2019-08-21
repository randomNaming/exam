package com.xjw.exam.service;


import com.xjw.exam.dao.LoginHistoryDao;
import com.xjw.exam.entity.LoginHistory;
import com.xjw.exam.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 登录历史记录 service层
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@Service
public class LoginHistoryService {

    @Autowired
    private LoginHistoryDao loginHistoryDao;

    /*
     * 新增登錄時間
     */
    public JSONResult insert(LoginHistory loginHistory) {
        loginHistory.setLoginTime(new Date());
        int addResult = loginHistoryDao.insert(loginHistory);
        if(addResult > 0){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("添加信息失敗！");
        }
    }

    public int count(LoginHistory loginRecord) {
        int recordCount = loginHistoryDao.count(loginRecord);
        return recordCount;
    }

    public boolean update(LoginHistory loginRecord) {
        loginRecord.setLoginTime(new Date());
        try {
            int effectedNum = loginHistoryDao.update(loginRecord);
            if(effectedNum > 0){
                return true;
            }else{
                throw new RuntimeException("更新登陸記錄息失败！");
            }
        }catch (Exception e){
            throw new RuntimeException("更新登陸記錄息失败:" + e.getMessage());
        }

    }
}
