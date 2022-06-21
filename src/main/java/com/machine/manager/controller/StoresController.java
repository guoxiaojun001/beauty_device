package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.entity.Store;
import com.machine.manager.entity.StoreData;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.service.StoreService;
import com.machine.manager.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 门店操作
 *
 */
@RestController
@Api("门店操作")
@RequestMapping("/store")
public class StoresController extends  BaseController{
    @Autowired
    private StoreService storeService;

    @ApiOperation("新增门店")
//    @UserLoginToken
//    @AdminToken
    @PostMapping("/addStore")
    public RestResult addStore(@RequestBody Store store) {
        RestResult restResult = new RestResult();
//        List<Store> storeList = storeService .selectByExample(store);

        int code = storeService.insertSelective(store);
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
    }

    @ApiOperation("删除门店")
//    @UserLoginToken
//    @AdminToken
    @PostMapping("/deleteStore")
    public RestResult deleteStoreById(Integer id) {
        RestResult restResult = new RestResult();
        int code = storeService.deleteByPrimaryKey(id);
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

    @ApiOperation("修改门店")
    //    @UserLoginToken
//    @AdminToken
    @PostMapping("/updateStore")
    public RestResult updateStoreInfo(@RequestBody Store store) {
//        List<Store> storeList = storeService .selectByExample(store);

        RestResult restResult = new RestResult();
        int code = storeService.updateByPrimaryKeySelective(store);
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


    @ApiOperation("查询门店列表  并且包含设备列表 ")
//    @UserLoginToken
//    @AdminToken
    @PostMapping("/getAllStoreAndDevice")
    public RestResult getAllStoreAndDevice() {
        System.out.print("  getAllStoreAndDevice :"  );
        RestResult restResult = new RestResult();
        List<StoreData> store = storeService.getAllStoreAndDevice();
        restResult.setCode(200);
        restResult.setData(store);
        restResult.setMsg("查询成功");
        restResult.setSuccess(true);

        return restResult;
    }


    @ApiOperation("查询门店 指定id查询 ")
//    @UserLoginToken
//    @AdminToken
    @PostMapping("/queryByStoreId")
    public RestResult queryStoreById(Integer id) {
        System.out.print("  queryStoreById :" + id);
        RestResult restResult = new RestResult();
        Store store = storeService.selectByPrimaryKey(id);
        restResult.setCode(200);
        restResult.setData(store);
        restResult.setMsg("查询成功");
        restResult.setSuccess(true);

        return restResult;
    }


    @ApiOperation("查询门店信息，返回列表 ")
//    @UserLoginToken
//    @AdminToken
    @PostMapping("/queryAllStore")
    public RestResult queryAllStore() {
        //TODO 涉及多表关联查询
        RestResult restResult = new RestResult();
        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == httpServletRequest ){
            restResult.setCode(200);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常2");
            restResult.setSuccess(false);
            return restResult;
        }

        String token = httpServletRequest.getHeader("token");
        if(null == token || "".equals(token)){
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("请求token为空");
            return restResult;
        }

        String userType = "user";
        int userId = -1;
        List<Store> list ;

        try {
            DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
            userId = decodedJWT.getClaim("userId").asInt();
            userType = decodedJWT.getClaim("userType").asString();

            logger.info("userId , userType {} -- {} ", userId,userType);
        } catch ( Exception e) {
        }

        if("admin".equals(userType)){
            list = storeService.selectAll();
            restResult.setData(list);
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("查询成功");
        }else {
            list = storeService.selectCurrentUser(userId );
            restResult.setData(list);
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("查询成功2");
        }

        return restResult;
    }
}
