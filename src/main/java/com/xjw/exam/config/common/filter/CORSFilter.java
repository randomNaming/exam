package com.xjw.exam.config.common.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器添加跨域支持
 *
 * @describe 如果是web.xml配置拦截器，请将@component删除
 *
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-16
 */
//@Component
//@Configuration
@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
public class CORSFilter implements Filter {

    Logger corsLogger = Logger.getLogger(CORSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        corsLogger.info("跨域拦截器 >>>>>>>>>>>>>>>>>>>>>>>>>>>> 启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        /**
         * 设置允许跨域的配置
         */
        String origin = request.getHeader("Origin");
        if (!org.springframework.util.StringUtils.isEmpty(origin)){
            // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP - 目前允许所有跨域）
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Authentication, Authorization, content-type, Accept, x-requested-with, Cache-Control");
        // 是否支持cookie跨域
        response.setHeader("Access-Control-Allow-Credentials","true");

        // 交付下一个拦截器
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
