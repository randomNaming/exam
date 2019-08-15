package com.xjw.exam;

import com.xjw.exam.config.common.CORSFilter;
import org.mybatis.spring.annotation.MapperScan;
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

        return bean;
    }

}
