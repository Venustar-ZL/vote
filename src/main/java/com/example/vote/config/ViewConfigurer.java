package com.example.vote.config;

import com.example.vote.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: ViewConfigurer
 * @Description: 视图控制
 * @Date : 2020-07-22 11:27
 * @Author: ZhangLei
 * Version: 1.0
 **/
@Configuration
public class ViewConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            // 添加视图控制
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/index");
                registry.addViewController("/index.html").setViewName("main/index");
                registry.addViewController("/login.html").setViewName("main/login");
                registry.addViewController("/register.html").setViewName("main/register");
                registry.addViewController("/join.html").setViewName("main/join");
                registry.addViewController("/index").setViewName("main/index");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        //指定要拦截的请求 /** 表示拦截所有请求
                        .addPathPatterns("/**")
                        //排除不需要拦截的请求路径
                        .excludePathPatterns("/", "/index.html", "/login.html", "/register.html", "/toRegister", "/register", "/login")
                        //springboot2+之后需要将静态资源文件的访问路径 也排除
                        .excludePathPatterns("/css/*", "/img/*","/js/*","/My97DatePicker/*");
            }
        };
    }

}
