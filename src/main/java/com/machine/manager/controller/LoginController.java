//package com.machine.manager.controller;
//
//import com.machine.manager.entity.UserInfo;
//import com.machine.manager.entity.user.request.UserLoginRequest;
//import com.machine.manager.entity.user.respone.UserLoginInResp;
//import com.machine.manager.service.UserService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 用户登录
// *
// * @author guoxi_789@126.com
// * @date 2021/1/11
// */
//@Controller
//@RequestMapping
//public class LoginController {
//    @Autowired
//    private UserService userService;
//
//    @ApiOperation("用户登录")
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @ApiOperation("用户登录")
//    @PostMapping("/submit")
//    @ResponseBody
//    public UserLoginInResp submit(HttpServletRequest request) {
//
//        //写用户名密码是否正确
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("passWord");
//        String token = request.getParameter("_csrf");
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName(userName);
//        userInfo.setPassword(password);
//        UserInfo result = userService.selectByUserName(userInfo);
//
//        UserLoginInResp userLoginInResp = new UserLoginInResp();
//        userLoginInResp.setToken(token);
//        userLoginInResp.setUserType(result.getUserType());
//
//        if (result != null) {
//            userLoginInResp.setSuccess(true);
//            return userLoginInResp;
//        } else {
//            userLoginInResp.setSuccess(false);
//            return userLoginInResp;
//        }
//    }
//}
