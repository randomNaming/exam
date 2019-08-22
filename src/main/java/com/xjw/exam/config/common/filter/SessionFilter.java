package com.xjw.exam.config.common.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

    // 标示符；表示當前用戶為登錄
    String NO_LOGIN = "未登录";

    // 白名單
    // TODO：List - String
    String[] includeURLs = new String[]{"/exam/login","/exam/student/checkById","/exam/teacher/checkById","/exam/student/addStudent","/exam/teacher/addStudent"};

    // 会话日志
    Logger sessionInitLog = Logger.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionInitLog.info("会话拦截器 >>>>>>>>>>>>>>>>>>>>>> 启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 獲取會話信息，如果不存在則爲空
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        // 调试作用：监测是否要过滤或排除
        // sessionInitLog.info("Filter URL >>>>>> " + uri);

        boolean needFilter = isNeedFilter(uri);

        if(!needFilter){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{

            // sessionInitLog.info("this session: " + session.getId());

            if(session!=null && (session.getAttribute("user"))!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                // TODO：做重定向
                sessionInitLog.info("@@@@@@@@@@@@@ 应当做重定向了！！！");
                sessionInitLog.warn(NO_LOGIN);

                return;
            }
        }
    }

    /**
     * @Auther S.H.Xjw@outlook.com
     * @Description 白名單網站請求
     * @param uri
     * @return true-需要過濾器 false-不需要過濾器
     */
    private boolean isNeedFilter(String uri) {

        for(String includeURL : includeURLs){
            if (includeURL.equals(uri)){
                return false;
            }
        }

        return true;
    }

    @Override
    public void destroy() {
        /*
         * 继承父类
         */
    }
}
