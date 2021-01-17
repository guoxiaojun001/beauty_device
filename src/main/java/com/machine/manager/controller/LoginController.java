package com.machine.manager.controller;

import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.user.request.UserLoginRequest;
import com.machine.manager.entity.user.respone.UserLoginInResp;
import com.machine.manager.service.UserService;
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

    @PostMapping(value = "/userLogin")
    public UserLoginInResp login(@RequestBody UserLoginRequest request) {
        UserLoginInResp resp = new UserLoginInResp();
        if (StringUtils.isNotBlank(request.getUserName()) && StringUtils.isNotBlank(request.getPassWord())) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(request.getUserName());
            userInfo.setPassword(request.getPassWord());
            UserInfo result = userService.selectByUserName(userInfo);
            if (result != null) {
                //登录成功
                resp.setToken(UUID.randomUUID().toString());
                resp.setSuccess(true);
                resp.setUserType(result.getUserType());
                return resp;
            }
            resp.setSuccess(false);
            return resp;
        }
        resp.setSuccess(false);
        return resp;
    }
}
