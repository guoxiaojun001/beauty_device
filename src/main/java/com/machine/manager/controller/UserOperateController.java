package com.machine.manager.controller;

import com.machine.manager.entity.UserInfo;
import com.machine.manager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户操作
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@RestController
@Api("用户操作")
@RequestMapping("/user")
public class UserOperateController {
    @Autowired
    private UserService userService;

   /* @ApiOperation("用户登录")
    @GetMapping("/login")
    public boolean login(@RequestBody UserInfo userInfo) {
        return userService.selectByUserName(userInfo);
    }*/

    @ApiOperation("新增用户")
    @PostMapping("/addUser")
    public int addUser(@RequestBody UserInfo userInfo) {
        return userService.insertSelective(userInfo);
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    public int deleteUserById(Integer userId) {
        return userService.deleteByPrimaryKey(userId);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUserInfo")
    public int updateUserInfo(@RequestBody UserInfo userInfo) {
        return userService.updateByPrimaryKeySelective(userInfo);
    }

    @ApiOperation("根据用户id查询单个用户信息")
    @GetMapping("/queryUserInfo")
    public UserInfo queryUserInfoById(Integer userId) {
        return userService.selectByPrimaryKey(userId);
    }
}
