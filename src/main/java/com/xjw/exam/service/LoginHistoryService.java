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
     * 添加题目
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
}
