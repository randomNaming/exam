package com.xjw.exam.handler;

import com.xjw.exam.utils.JSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * @author xiajingwei - S.H.Xjw@outlook.com
 * @date 2019-08-19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private Object exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if (isAjax(request)){
            return JSONResult.errorException(e.getMessage());
        }
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("success",false);
        modelMap.put("url",request.getRequestURL());
        modelMap.put("errMsg", e.getMessage());
        return modelMap;

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", e);
//        modelAndView.addObject("url", request.getRequestURL());
//        modelAndView.setViewName(ERROR_VIEW);
//        return modelAndView;
    }

    /**
     *
     * @Title: GlobalExceptionHandler.java
     * @Package com.xjw.exam.handlerqq.com
     * @Description 判断是否Ajax请求
     *
     * @authir 654787383@
     * @date 20190807
     * @version v1.0
     *
     */
    public static boolean isAjax(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
