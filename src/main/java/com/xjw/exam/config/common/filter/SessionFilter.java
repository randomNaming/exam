package com.xjw.exam.config.common.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

    // 標識符；表示當前用戶為登錄
    String NO_LOGIN = "未登錄";

    // 白名單
    String[] includeURLs = new String[]{"/student/login","register"};

//    int int1= 1;
//    int int2 = 2;
//    int int3 = 3;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 獲取會話信息，如果不存在則爲空
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        System.out.println("filter url:" + uri);

        boolean needFilter = isNeedFilter(uri);

        if(!needFilter){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            if(session!=null && session.getAttribute("user")!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{

                System.out.println("應當做重定向了！");

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

    }
}
