//package com.machine.manager.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//
///**
// * web security-config
// *
// * @author guoxi_789@126.com
// * @date 2020/12/15
// */
//@Configuration
//@EnableWebSecurity
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * 更高级配置
//     *
//     * @author guoxi_789@126.com
//     * @date 2020/12/15
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//
//    }
//    /**
//    * 功能描述 基于http协议的配置
//    * @author guoxi_789@126.com
//    * @date 2020/12/15
//    * @param  http
//    * @return void
//    */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository());
//    }
//}
