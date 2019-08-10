package com.xjw.exam.dao;

import com.xjw.exam.entity.LoginHistory;
import org.springframework.stereotype.Repository;

/**
 * 登录记录DAO接口
 * @author SHXjw
 * @version 20190910
 */
@Repository
public interface LoginHistoryDao extends CurdDao<LoginHistory> {
}
