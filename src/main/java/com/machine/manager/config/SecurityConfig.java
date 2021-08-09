package com.machine.manager.config;

import com.machine.manager.controller.BaseController;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 放行某些具体的url
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl myUserDetailsService;

//    @Autowired
//    private JwtAuthTokenFilter jwtAuthTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //调用DetailsService完成用户身份验证              设置密码加密方式
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/login/**")
                .antMatchers("/user/**")
                .antMatchers("/machine/**")
                .antMatchers("/uploadFile/**")
                .antMatchers("/mqtt/**")
        ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //自定义登录页面
        http
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()

                .antMatchers("/submit").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/success")
                .permitAll()
                .failureUrl("/error")
        // 由于使用的是JWT，我们这里不需要csrf
//                .and() .csrf()  .csrfTokenRepository(new HttpSessionCsrfTokenRepository())

        ;
        //  CRSF禁用，因为不使用session
        //禁用跨站csrf攻击防御，否则无法登陆成功
        //.and().csrf().disable();

        http.logout().deleteCookies("JSESSIONID");

        //  添加JWT  filter, 在每次http请求前进行拦截
//        System.out.println("xxxxxxxxxxxxxxx："  +jwtAuthTokenFilter .toString());
//        http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/hello","/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页的路径
                .loginPage("/login")
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/authentication/form")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/success")
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .permitAll();
                //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
                http .csrf().disable();

    }*/

    /**
     * 功能描述  装载BCrypt密码编码器
     *
     * @param
     * @return
     * @author guoxi_789@126.com
     * @date 2021/1/11
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @UserLoginToken
    public PasswordEncoder xx(int a) {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] arg){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pp = bCryptPasswordEncoder.encode("111111");
        System.out.println("xxxx == "  + pp);


        try {
//         boolean checkAuthByAnotation =   BaseController.checkAuthByAnotation("xx",SecurityConfig.class);

            BaseController.preCheck("xx",SecurityConfig.class);
//            System.out.println("checkAuthByAnotation == "  + checkAuthByAnotation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
