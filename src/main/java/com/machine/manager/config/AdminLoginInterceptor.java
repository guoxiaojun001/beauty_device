package com.machine.manager.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.reject.AdminToken;
import com.machine.manager.reject.PassToken;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.UserService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.hutool.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 后台系统身份验证拦截器
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
            throws Exception {


        String token = request.getHeader("token");// 从 http 请求头中取出 token
        String blackId = request.getHeader("blackId");// android设备用
        System.out.println("method blackId = " + blackId);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        String url = request.getRequestURL().toString();
        if(url.contains("/swagger-ui")){
            return true;
        }else {
            HandlerMethod handlerMethod;
            Method method;
            try {
                handlerMethod=(HandlerMethod)object;
                method=handlerMethod.getMethod();

                Annotation[] ann = method.getAnnotations();
                if(null == ann || ann.length <= 0){
                    return true;
                }

            }catch (Exception e){
                System.out.println("method 转换异常");
                return true;
            }


            if (method.isAnnotationPresent(PassToken.class)) {
                System.out.println(" 带有注解 PassToken");
                return true;
            }else {
                // 如果不是映射到方法直接通过
                if(!(object instanceof HandlerMethod)){
                    System.out.println("不是映射到方法直接通过");
                    return true;
                }

                DecodedJWT decodedJWT = JwtTokenUtil222 .getTokenInfo(token);

                JSONObject res = new JSONObject();

                if (method.isAnnotationPresent(AdminToken.class)) {
                    System.out.println(" 带有注解AdminToken");

                    if(null == decodedJWT){

                        res.put("success", false);
                        res.put("code", 300);
                        res.put("msg", "被拦截，需要管理员权限！");
                        res.put("data", "no data！");
                        response.setStatus(200);
                        out = response.getWriter();
                        out.append(res.toString());

                        return false;
                    }else {
                        String userName = decodedJWT.getClaim("userName").asString();
                        String userType = decodedJWT.getClaim("userType").asString();

                        if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(userType)){
                            res.put("success", false);
                            res.put("code", 201);
                            res.put("msg", "被拦截，权限为空！");
                            res.put("data", "no data！");
                            response.setStatus(200);
                            out = response.getWriter();
                            out.append(res.toString());
                            return false;
                        }else {
                            if("admin".equals(userType)){
                                System.out.println("管理员 放行");
                                return true;
                            }else if("user".equals(userType) || "normal".equals(userType)){
                                System.out.println("普通用户 不放行");
                                res.put("success", false);
                                res.put("code", 300);
                                res.put("msg", "被拦截，权限不足！");
                                res.put("data", "no data！");
                                response.setStatus(200);
                                out = response.getWriter();
                                out.append(res.toString());
                                return false;
                            }else {
                                System.out.println("未知用户 不放行");
                                res.put("success", false);
                                res.put("code", 201);
                                res.put("msg", "被拦截，未知权限 ！");
                                res.put("data", "no data！");
                                response.setStatus(200);
                                out = response.getWriter();
                                out.append(res.toString());
                                return false;
                            }
                        }
                    }

                }else {
                    System.out.println(" 没有注解AdminToken");

                    if(null != decodedJWT){
                        String userName = decodedJWT.getClaim("userName").asString();
                        String userType = decodedJWT.getClaim("userType").asString();
                        //检查是否有UserLoginToken注释，有则跳过认证
                        if (method.isAnnotationPresent(UserLoginToken.class)) {
                            System.out.println("带有注解");
                            if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(userType)){
                                System.out.println("token异常用户 不放行");
                                res.put("success", false);
                                res.put("code", 201);
                                res.put("msg", "token异常用户 ！");
                                res.put("data", "no data！");
                                response.setStatus(200);
                                out = response.getWriter();
                                out.append(res.toString());
                                return false;
                            }else {
                                if("admin".equals(userType) || "user".equals(userType)  || "normal".equals(userType)){
                                    System.out.println("登录用户 放行");
                                    return true;
                                }else {
                                    System.out.println("token异常用户 不放行");
                                    res.put("success", false);
                                    res.put("code", 201);
                                    res.put("msg", "未知用户 ！");
                                    res.put("data", "no data！");
                                    response.setStatus(200);
                                    out = response.getWriter();
                                    out.append(res.toString());
                                    return false;
                                }
                            }
                        }else {
                            System.out.println("没有注解1 UserLoginToken");
                            return true;
                        }
                    }else {

                        if (method.isAnnotationPresent(UserLoginToken.class)) {
                            System.out.println("token异常用户 不放行");
                            res.put("success", false);
                            res.put("code", 201);
                            res.put("msg", "未知用户 ！");
                            res.put("data", "no data！");
                            response.setStatus(200);
                            out = response.getWriter();
                            out.append(res.toString());
                            return false;
                        }else {

                            System.out.println("没有注解 UserLoginToken");
                            return true;
                        }

                    }

                }

            }

        }

    }
/*
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }*/
}