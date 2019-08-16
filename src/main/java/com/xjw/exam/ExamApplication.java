package com.xjw.exam;

import com.xjw.exam.config.common.filter.CORSFilter;
import com.xjw.exam.config.common.filter.SessionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@MapperScan(basePackages = "")
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();

        bean.addUrlPatterns("/*");
        bean.setFilter(new CORSFilter());
        bean.setOrder(0);

        return bean;
    }

    @Bean
    public FilterRegistrationBean sessionFilterBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();

        bean.addUrlPatterns("/*");
        bean.setFilter(new SessionFilter());
        bean.setOrder(1);

        return bean;
    }

    // 如果filter過多請直接在class頭部增加@ServletComponentScan(basePackages= "filter所在的包")
}
