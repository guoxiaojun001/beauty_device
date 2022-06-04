package com.machine.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 把swagger-ui.html映射到jar包中
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@Configuration
public class SwaggerWebMvcConfig implements WebMvcConfigurer {

//    @Autowired(required = false)
//    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/admin为前缀的url路径
//        registry.addInterceptor(authenticationInterceptor()).
//                addPathPatterns("/machine/**").
//                addPathPatterns("/user/**").
//                addPathPatterns("/mqtt/**").
//                addPathPatterns("/uploadFile/**").
//                //excludePat路径hPatterns 不需要拦截的
//                 excludePathPatterns("/login/userLogin");

//        registry.addInterceptor(authenticationInterceptor()).
////                addPathPatterns("/**").
//                //excludePat路径hPatterns 不需要拦截的
//                        excludePathPatterns("/login/userLogin");

        registry.addInterceptor( authenticationInterceptor())
                .addPathPatterns("/**")  //所有请求都被拦截包括静态资源
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/login/userLogin",
                        "/swagger-ui","/js/**","/aa/**"); //放行的请求
    }

    @Bean
    public AdminLoginInterceptor authenticationInterceptor() {
        return new AdminLoginInterceptor();// 自己写的拦截器
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
//    }
}