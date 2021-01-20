package com.machine.manager.controller;

import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.user.request.UserLoginRequest;
import com.machine.manager.entity.user.respone.UserLoginInResp;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.service.UserService;
import com.machine.manager.service.impl.JwtAuthService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 用户登录
 *
 * @author guoxi_789@126.com
 * @date 2021/1/11
 */
@RestController
@RequestMapping("/login")
@Api("用户登录接口")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthService jwtAuthService;

    @PostMapping(value = "/userLogin")
    public UserLoginInResp login(@RequestBody UserLoginRequest request) {
        UserLoginInResp resp = new UserLoginInResp();
        String username = request.getUserName();
        String password = request.getPassWord();
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setName(request.getUserName());
//            userInfo.setPassword(request.getPassWord());
//            UserInfo result = userService.selectByUserName(userInfo);
//
//            if (result != null) {
//                //登录成功
//                resp.setToken(  jwtAuthService.login(username,password));
//                resp.setSuccess(true);
//                resp.setUserType(result.getUserType());
//                return resp;
//            }
//            resp.setSuccess(false);
//            resp.setToken(  jwtAuthService.login(username,password));
//            resp.setUserType("");
//            return resp;
//        }
//        resp.setSuccess(false);

//        return resp;
        }
        return jwtAuthService.login(username, password);
    }

//
//    // 这个方法用于在登录后登录验证后返回token
//    @PostMapping("/login")
//    public RestResult login(String username, String password){
//
//        RestResult result = RestResult.newInstance();
//        result.setCode(200);
//        // 该方法会调用UserDetailsServiceImpl的LoadUserByUsername
//        String token = jwtAuthService.login(username,password);
//        result.put("token",token);
//        return result;
//    }
}
