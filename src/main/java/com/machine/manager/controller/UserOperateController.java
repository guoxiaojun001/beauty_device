package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.dao.StoreDao;
import com.machine.manager.dao.StoresDao;
import com.machine.manager.entity.AgentAndStoreEntity;
import com.machine.manager.entity.Store;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.machine.CommonRequest;
import com.machine.manager.entity.user.request.UserQueryRequest;
import com.machine.manager.entity.user.request.UserQueryRequestName;
import com.machine.manager.entity.user.request.UserQueryRequestParm;
import com.machine.manager.entity.user.request.UserQueryRequestPhone;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.AdminToken;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.UserService;
import com.machine.manager.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

    @Autowired
    private StoresDao storesDao;


    @Autowired
    StoreDao storeDao;


    @ApiOperation("新增用户")
    @UserLoginToken
    @AdminToken
    @PostMapping("/addUser")
    public RestResult addUser(@RequestBody UserInfo userInfo) {
        RestResult restResult = new RestResult();
        if(null == userInfo || StringUtils.isEmpty(userInfo.getName()) ||  StringUtils.isEmpty(userInfo.getUserType())){
            System.out.print("用户名信息不全");
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("用户名信息不全");
            return restResult;
        }

        List<UserInfo> userInfoList = userService .selectByName(userInfo.getName());

        if(null == userInfoList  || userInfoList.size() <= 0){
            int code = userService.insertSelective(userInfo);
            if(code == 1){
                restResult.setCode(200);
                restResult.setSuccess(true);
                restResult.setMsg("添加成功");
            }else {
                restResult.setCode(203);
                restResult.setSuccess(false);
                restResult.setMsg("添加失败");
            }

            return restResult;

        }else {
            System.out.print("用户名已经存在");
            restResult.setCode(203);
            restResult.setSuccess(false);
            restResult.setMsg("用户名已经存在");
            return restResult;
        }
    }


    @ApiOperation("删除用户")
    @UserLoginToken
    @AdminToken
    @PostMapping("/deleteUser")
    public RestResult deleteUserById(Integer userId) {
        RestResult restResult = new RestResult();

        //删除经销商用户
        int code = userService.deleteByPrimaryKey(userId);
//        //查询出该经销商下的所有门店
//        List<Store> storeList = storeDao.selectCurrentUser(userId);
//        for(Store store : storeList){
//            storeDao.deleteByPrimaryKey(store.getId());
//        }
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("删除成功");
        }else {
            restResult.setCode(203);
            restResult.setSuccess(false);
            restResult.setMsg("删除失败");
        }

        return restResult;
    }

    @ApiOperation("修改用户信息,管理员和当前用户都可以修改自己信息")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/updateUserInfo")
    public RestResult updateUserInfo(@RequestBody UserInfo userInfo) {
        List<UserInfo> userInfoList = userService .selectByName(userInfo.getName());

        RestResult restResult = new RestResult();
        String pwd = userInfo.getPassword();
        System.out.print("修改密码 pwd = " + pwd);
        String pp = bCryptPasswordEncoder.encode(pwd);
        System.out.println("修改密码 xxxx == "  + pp);
        userInfo.setPassword(pp);
        int code = userService.updateByPrimaryKeySelective(userInfo);
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("更新成功");
        }else {
            restResult.setCode(203);
            restResult.setSuccess(false);
            restResult.setMsg("更新失败");
        }

        return restResult;
    }


    @ApiOperation("查询某个经销商下的门店列表, 暂时不用")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/storesUnderAgent")
    public RestResult getStoresUnderAgent(Integer agentId){
        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        RestResult restResult= new RestResult();
        if(null == httpServletRequest ){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常3");
            restResult.setSuccess(false);
            return restResult;
        }

        String token = httpServletRequest.getHeader("token");
        if( StringUtils.isEmpty(token)){
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("请求token为空3");
            return restResult;
        }else {
            try {
                DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
                int userId = decodedJWT.getClaim("userId").asInt();
                String userType = decodedJWT.getClaim("userType").asString();

                if(!StringUtils.isEmpty(userType) && "admin".equals(userType)){
                    //管理查询，不通过agentId
                    logger.info("判断身份是管理员，直接查询所有经销商&门店");
                    restResult = queryAllStoresAgent();
                }else {
                    logger.info("判断身份是经销商，查询指定经销商的门店");
                    List<AgentAndStoreEntity> agentAndStoreEntities = storesDao.queryStoresUnderAgent(agentId);
                    restResult.setData(agentAndStoreEntities);
                    restResult.setSuccess(true);
                    restResult.setMsg("success");
                }

            } catch ( Exception e) {
                e.printStackTrace();
            }
        }

        return restResult;
    }



//    @ApiOperation("查询所有经销商列表 以及每个经销商下包含的门店列表，暂时不用")
//    @UserLoginToken
//    @AdminToken
//    @PostMapping("/queryAllStoresAgent")
    public RestResult queryAllStoresAgent(){
        List<AgentAndStoreEntity> agentAndStoreEntities = storesDao.queryAllStoresAgent();
        RestResult restResult= new RestResult();
        restResult.setData(agentAndStoreEntities);
        restResult.setSuccess(true);
        restResult.setMsg("success");
        return restResult;
    }





    @ApiOperation("查询某个经销商，名称或者手机号查询， 以及每个经销商下包含的门店列表，参数传空 返回所有")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/selectUserInfoByParmAndStoreCount")
    public RestResult selectUserInfoByParmAndStoreCount(@RequestBody CommonRequest commonRequest){
        RestResult restResult= new RestResult();
        logger.info("selectUserInfoByParmAndStoreCount commonRequest = " + commonRequest);
        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == httpServletRequest ){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常3");
            restResult.setSuccess(false);
            return restResult;
        }

        String token = httpServletRequest.getHeader("token");
        if( StringUtils.isEmpty(token)){
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("请求token为空3");
        }else {
            try {
                DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
                int userId = decodedJWT.getClaim("userId").asInt();
                String userType = decodedJWT.getClaim("userType").asString();

                if(!StringUtils.isEmpty(userType) && "admin".equals(userType)){
                    //管理查询，不通过agentId
                    logger.info("判断身份是管理员，直接查询所有经销商&门店");

                    List<UserInfo>  userInfoList = userService. selectUserInfoByParmAndId(null,commonRequest.getParms());
                    restResult.setData(userInfoList);
                    restResult.setSuccess(true);
                    restResult.setMsg("success");
                }else {
                    logger.info("判断身份是经销商，查询指定经销商的门店");
                    List<UserInfo> userInfoList = userService.selectUserInfoByParmAndId(userId,commonRequest.getParms());
                    restResult.setData(userInfoList);
                    restResult.setSuccess(true);
                    restResult.setMsg("success");
                }

            } catch ( Exception e) {
                e.printStackTrace();
                restResult.setData(null);
                restResult.setSuccess(false);
                restResult.setCode(202);
                restResult.setMsg("error");
            }
        }

        return restResult;
    }

}
