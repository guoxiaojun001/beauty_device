package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.dao.StoreDao;
import com.machine.manager.dao.StoresDao;
import com.machine.manager.entity.AgentAndStoreEntity;
import com.machine.manager.entity.Store;
import com.machine.manager.entity.UserInfo;
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


/*    @ApiOperation("管理员 查询用户信息，返回列表，搜索的话，需要传入手机号，或者姓名 ，不限制条件，字段传空就行 ")
//    @UserLoginToken
//    @AdminToken
    @PostMapping("/queryUserList")
    public RestResult queryUserList(@RequestBody UserQueryRequestParm request) {
        RestResult pre = preCheck("queryUserList",UserOperateController.class);
        if(null != pre){
            System.out.print("提前判断权限，权限失败");
            return pre;
        }else {
            List<UserInfo> list;
            System.out.print("queryUserList  request :" + request);
            RestResult restResult = new RestResult();
            if(null == request){
                restResult.setCode(202);
                restResult.setData(null);
                restResult.setMsg("参数为空");
                restResult.setSuccess(false);
                return restResult;
            }

            if( !StringUtils.isEmpty( request.getName())  ){
                if(!StringUtils.isEmpty( ( request.getTelephone()))){
                    //综合查询用户名和手机号
                    System.out.print("综合查询用户名和手机号:" + request.getName()  + request.getTelephone());
                    UserInfo userInfo = new UserInfo();
                    userInfo.setName(request.getName());
                    userInfo.setTelephone(request.getTelephone());
                    list = userService.selectByUserInfo(userInfo);
                    restResult.setCode(200);
                    restResult.setData(list);
                    restResult.setSuccess(true);
                    restResult.setMsg("用户名和手机号查询所有");
                    return restResult;
                }else {
                    //根据查询用户名即可
                    System.out.print("根据查询用户名:" + request.getName() );
                    UserQueryRequestName userQueryRequestName = new UserQueryRequestName();
                    userQueryRequestName.setName(request.getName());
                    return queryUserInfoByName(userQueryRequestName);
                }
            } else {
                if(!StringUtils.isEmpty( ( request.getTelephone()))){
                    // 根据查询手机号
                    System.out.print("根据查询手机号:" + request.getTelephone());
                    UserQueryRequestPhone userQueryRequestPhone = new UserQueryRequestPhone();
                    userQueryRequestPhone.setTelephone(request.getTelephone());
                    return queryUserByPhone(userQueryRequestPhone);
                }else {
                    //查询所有
                    System.out.print("==查询所有:"  );

                    restResult.setCode(200);
                    restResult.setData(userService.selectAll());
                    restResult.setMsg("查询所有");
                    restResult.setSuccess(true);
                    return restResult;
                }
            }
        }

    }*/



/*//    @ApiOperation("输入id查询可能有多个，不输入 查询多个")
//    @PostMapping("/queryUserInfo")
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

    }*/


/*//    @ApiOperation("输入name查询，可能有多个， 不输入查询所有")
//    @PostMapping("/queryUserByName")
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
    }*/


    //    @ApiOperation("输入手机号查询 也可能多个， 不输入查询所有")
//    @PostMapping("/queryUserByPhone")
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



    @ApiOperation("查询某个经销商下的门店列表, 暂时不用")
    @UserLoginToken
//    @AdminToken
//    @PostMapping("/storesUnderAgent")
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


    @ApiOperation("查询所有经销商列表 以及每个经销商下包含的门店列表，暂时不用")
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


//    @ApiOperation("查询所有经销商列表 以及每个经销商下包含的门店列表")
//    //    @UserLoginToken
////    @AdminToken
////    @PostMapping("/AllStoresUnderAgent2")
//    public RestResult getAllStoresUnderAgent(){
//        List<AgentAndStoreEntity> agentAndStoreEntities = storesDao.queryAllStoresAndAgentList();
//        RestResult restResult= new RestResult();
//        restResult.setData(agentAndStoreEntities);
//        restResult.setSuccess(true);
//        restResult.setMsg("success");
//        return restResult;
//    }



    @ApiOperation("查询所有经销商列表 以及每个经销商下包含的门店列表,用户列表使用")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/selectUserInfoAndStoreCount")
    public RestResult selectUserInfoAndStoreCount(){
//        List<UserInfo> agentAndStoreEntities = userService.selectUserInfoAndStoreCount();
//        RestResult restResult= new RestResult();
//        restResult.setData(agentAndStoreEntities);
//        restResult.setSuccess(true);
//        restResult.setMsg("success");
//        return restResult;

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
        }else {
            try {
                DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
                int userId = decodedJWT.getClaim("userId").asInt();
                String userType = decodedJWT.getClaim("userType").asString();

                if(!StringUtils.isEmpty(userType) && "admin".equals(userType)){
                    //管理查询，不通过agentId
                    logger.info("判断身份是管理员，直接查询所有经销商&门店");

                    List<UserInfo> userInfoList = userService.selectUserInfoAndStoreCount();
                    restResult.setData(userInfoList);
                    restResult.setSuccess(true);
                    restResult.setMsg("success");
                }else {
                    logger.info("判断身份是经销商，查询指定经销商的门店");
                    List<UserInfo> userInfoList = new ArrayList<>();
                    userInfoList.add( userService.selectByPrimaryKey(userId));
                    restResult.setData(userInfoList);
                    restResult.setSuccess(true);
                    restResult.setMsg("success");
                }

            } catch ( Exception e) {
                e.printStackTrace();
            }
        }

        return restResult;
    }



    @ApiOperation("查询某个经销商，名称或者手机号查询， 以及每个经销商下包含的门店列表")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/selectUserInfoByParmAndStoreCount")
    public RestResult selectUserInfoByParmAndStoreCount(String parms){

        UserInfo userInfo = userService. selectUserByUserName(parms);
        RestResult restResult= new RestResult();
        restResult.setData(userInfo);
        restResult.setSuccess(true);
        restResult.setMsg("success");

        return restResult;

    }

}
