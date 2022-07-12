package com.machine.manager.service.impl;

import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.user.respone.UserLoginInResp;
import com.machine.manager.jwt.JwtTokenUtil222;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthService {

    // 此处注入的bean在SpringConfig中产生, 如果不在其中声明则注入AuthenticationManager报错
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtTokenUtils jwtTokenUtils;


    /**
     * 登录认证换取JWT令牌
     * @param username
     * @param password
     * @return
     */
    public UserLoginInResp loaddUsergin(String username, String password) {
        //用户验证
        Authentication authentication = null;

        UserLoginInResp resp = new UserLoginInResp();

        try {
            // 进行身份验证,
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            resp.setCode(202);
            resp.setSuccess(false);
            resp.setToken("");
            resp.setUserType("");
            resp.setMsg("手机号或密码错误2");
            return resp;
        }

//        List list = (List) authentication.getAuthorities();
//        System.out.print("list = " + list);
        UserInfo loginUser = (UserInfo) authentication.getPrincipal();

        if (loginUser != null) {
            //登录成功
            resp.setCode(200);
            resp.setToken( /*jwtTokenUtils.generateToken(loginUser)*/JwtTokenUtil222.createToken(loginUser));
            resp.setSuccess(true);
            resp.setUserType(loginUser.getUserType());
            resp.setMsg("登录成功");
            resp.setUserId(loginUser.getId());
            return resp;
        }
        resp.setCode(202);
        resp.setSuccess(false);
        resp.setToken("");
        resp.setUserType("");
        resp.setMsg("用户名密码错误1");
        return resp;

    }

}
