package com.machine.manager.jwt;

import cn.hutool.json.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.constant.UserRoleEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


//@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //http  请求头中的token
        String token = request.getHeader("token");
        String url = request.getRequestURL().toString();
        RestResult restResult = new RestResult();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        if(url.contains("login/userLogin")){
            filterChain.doFilter(request, response);
            return;
        }

        if (token!=null && token.length()>0) {
            JwtTokenUtil222.getClaimsFromToken(token);
            DecodedJWT decodedJWT = JwtTokenUtil222 .getTokenInfo(token);
            if(null == decodedJWT){

                try {
                    JSONObject res = new JSONObject();
                    res.put("success", false);
                    res.put("code", 200);
                    res.put("msg", "被拦截，登录用户失效0！");
                    res.put("data", "no data！");
                    response.setStatus(200);
                    out = response.getWriter();
                    out.append(res.toString());

//                        restResult.setCode(200);
//                        restResult.setData("no data");
//                        restResult.setMsg("被拦截，登录用户失效0");
//                        restResult.setSuccess(false);

//                        out = response.getWriter();
//                        out.append(restResult.toString());
                    return ;
                } catch (Exception e) {
                    response.sendError(500);
                    return ;
                }
            }else {
                Map<String, Claim> map =  decodedJWT.getClaims();
                String userName = decodedJWT.getClaim("userName").asString();
                String userType = decodedJWT.getClaim("userType").asString();
                if(StringUtils.isEmpty(userName)|| StringUtils.isEmpty(userType)){
//                        restResult.setCode(200);
//                        restResult.setData("no data");
//                        restResult.setMsg("被拦截，登录用户失效1");
//                        restResult.setSuccess(false);

                    JSONObject res = new JSONObject();
                    res.put("success", false);
                    res.put("code", 200);
                    res.put("msg", "被拦截，登录用户失效1！");
                    res.put("data", "no data！");
                    response.setStatus(200);
                    out = response.getWriter();
                    out.append(res.toString());

//                        out = response.getWriter();
//                        out.append(restResult.toString());
                    return;
                }else {
                    if(url.contains("/user/addUser")
                            || url.contains("/user/addUser")
                            || url.contains("/user/deleteUser")
                            || url.contains("/user/queryUserList")

                            || url.contains("/uploadFile/upload")
                            || url.contains("/machine/addMachine")
                            || url.contains("/machine/deleteMachine")
                            || url.contains("/machine/updateMachine")
                            ){
                        if("admin".equals(userType) ){
                            filterChain.doFilter(request, response);
                        }else if("user".equals(userType) ){

                            JSONObject res = new JSONObject();
                            res.put("success", false);
                            res.put("code", 200);
                            res.put("msg", "被拦截，用户权限不足3！");
                            res.put("data", "no data！");
                            response.setStatus(200);
                            out = response.getWriter();
                            out.append(res.toString());
                            return;
                        }else {
                            JSONObject res = new JSONObject();
                            res.put("success", false);
                            res.put("code", 200);
                            res.put("msg", "被拦截，用户权限不足！");
                            res.put("data", "no data！");
                            response.setStatus(200);
                            out = response.getWriter();
                            out.append(res.toString());
                            return;
                        }
                    } else {
                        if("admin".equals(userType)||"user".equals(userType) ){
                            filterChain.doFilter(request, response);
                            return;
                        }else {
                            JSONObject res = new JSONObject();
                            res.put("success", false);
                            res.put("code", 200);
                            res.put("msg", "被拦截，未知权限！");
                            res.put("data", "no data！");
                            response.setStatus(200);
                            out = response.getWriter();
                            out.append(res.toString());
                            return;
                        }
                    }

                }
            }
        }else {
            JSONObject res = new JSONObject();
            res.put("success", false);
            res.put("code", 200);
            res.put("msg", "被拦截，token为空！");
            res.put("data", "no data！");
            response.setStatus(200);
            out = response.getWriter();
            out.append(res.toString());
            return;
        }

    }
}
