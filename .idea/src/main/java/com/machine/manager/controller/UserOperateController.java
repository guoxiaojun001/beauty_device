package com.machine.manager.controller;

import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.user.request.UserQueryRequest;
import com.machine.manager.entity.user.request.UserQueryRequestName;
import com.machine.manager.entity.user.request.UserQueryRequestPhone;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 用户操作
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@RestController
@Api("用户操作")
@RequestMapping("/user")
public class UserOperateController  extends  BaseController{
    @Autowired
    private UserService userService;


    @ApiOperation("新增用户")
    @PostMapping("/addUser")
    public RestResult addUser(@RequestBody UserInfo userInfo) {
        RestResult restResult = new RestResult();
        int code = userService.insertSelective(userInfo);
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("添加成功");
        }else {
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("添加失败");
        }

        return restResult;
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    public RestResult deleteUserById(Integer userId) {

        RestResult restResult = new RestResult();
        int code = userService.deleteByPrimaryKey(userId);
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("删除成功");
        }else {
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("删除失败");
        }

        return restResult;
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUserInfo")
    public RestResult updateUserInfo(@RequestBody UserInfo userInfo) {
        RestResult restResult = new RestResult();
        int code = userService.updateByPrimaryKeySelective(userInfo);
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("更新成功");
        }else {
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("更新失败");
        }

        return restResult;
    }


    @ApiOperation("输入id查询可能有多个，不输入 查询多个")
    @PostMapping("/queryUserInfo")
    public RestResult queryUserInfo(@RequestBody UserQueryRequest request) {
        RestResult restResult = new RestResult();

        List<UserInfo> list;
        if (request.getId() != null) {
            UserInfo userInfo = userService.selectByPrimaryKey(request.getId());
            list = Arrays.asList(userInfo);

            restResult.setCode(200);
            if(null != list){
                restResult.setData(list);
                restResult.setMsg("查询到一条");
            }else {
                restResult.setData(list);
                restResult.setMsg("查询到0条");
            }
            return restResult;
        }

        list = userService.selectAll();
        restResult.setCode(200);
        restResult.setData(list);
        restResult.setMsg("查询所有");
        return restResult;

    }


    @ApiOperation("输入name查询，可能有多个， 不输入查询所有")
    @PostMapping("/queryUserByName")
    public RestResult queryUserInfoByName(@RequestBody UserQueryRequestName request) {
        RestResult restResult = new RestResult();

        List<UserInfo> list;
        String name = request.getName();
        if (name != null && !"".equals(name)) {
            list = userService.selectByName(name);

            restResult.setCode(200);
            if(null != list){
                restResult.setData(list);
                restResult.setMsg("查询到数据");
            }else {
                restResult.setData(list);
                restResult.setMsg("查询到0条");
            }
            return restResult;
        }

        list = userService.selectAll();
        restResult.setCode(200);
        restResult.setData(list);
        restResult.setMsg("查询所有");
        return restResult;
    }


    @ApiOperation("输入手机号查询 也可能多个， 不输入查询所有")
    @PostMapping("/queryUserByPhone")
    public RestResult queryUserByPhone(@RequestBody UserQueryRequestPhone request) {
        RestResult restResult = new RestResult();

        List<UserInfo> list;
        String telephone = request.getTelephone();
        if (telephone != null && !"".equals(telephone)) {
            list = userService.selectByPhone(telephone);

            restResult.setCode(200);
            if(null != list){
                restResult.setData(list);
                restResult.setMsg("查询到数据");
            }else {
                restResult.setData(list);
                restResult.setMsg("查询到0条");
            }
            return restResult;
        }

        list = userService.selectAll();
        restResult.setCode(200);
        restResult.setData(list);
        restResult.setMsg("查询所有");
        return restResult;
    }
}
