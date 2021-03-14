package com.machine.manager.controller;

import com.machine.manager.MD5Util;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.user.request.UserLoginRequest;
import com.machine.manager.entity.user.respone.UserLoginInResp;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.PassToken;
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
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthService jwtAuthService;

    @PostMapping(value = "/userLogin")
    @PassToken
    public UserLoginInResp login(@RequestBody UserLoginRequest request) {
        UserLoginInResp resp = new UserLoginInResp();
        String username = request.getUserName();
        String password = request.getPassWord();
        return jwtAuthService.loaddUsergin(username, password);
    }

//    @PostMapping(value = "/userLoginMD5")
//    @PassToken
    public UserLoginInResp userLoginMD5(@RequestBody UserLoginRequest request) {
        UserLoginInResp resp = new UserLoginInResp();
        String username = request.getUserName();
        String password = request.getPassWord();

        System.out.println("经过md5 的password ：" + password);
        String MD5_pwd = MD5Util.convertMD5(password);
        System.out.println("解密的：" + MD5_pwd);

        return jwtAuthService.loaddUsergin(username, MD5_pwd);
    }
}
