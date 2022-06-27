package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.dao.MachineDao;
import com.machine.manager.entity.AgentAndStoreEntity;
import com.machine.manager.entity.Store;
import com.machine.manager.entity.StoreAndMachineEntity;
import com.machine.manager.entity.StoreData;
import com.machine.manager.entity.machine.CommonRequest;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.AdminToken;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.StoreService;
import com.machine.manager.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @UserLoginToken
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
    @UserLoginToken
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
    @UserLoginToken
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

/*
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
    }*/



/*
    @ApiOperation("查询门店 指定id查询 ，暂时不用")
    @UserLoginToken
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
*/


    @ApiOperation("查询门店 指定门店名称 查询 ，暂时不用")
    @UserLoginToken
//    @AdminToken
//    @PostMapping("/queryByStoreName")
    public RestResult selectByStoreName(String storeName) {
        System.out.print("  selectByStoreName :" + storeName);
        RestResult restResult = new RestResult();
        Store store = storeService.selectByStoreName(storeName);
        restResult.setCode(200);
        restResult.setData(store);
        restResult.setMsg("查询成功");
        restResult.setSuccess(true);

        return restResult;
    }


    @ApiOperation("查询门店信息，返回列表,暂时不用 ")
    @UserLoginToken
//    @AdminToken
//    @PostMapping("/queryAllStore")
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


    @Autowired
    MachineDao machineDao;

    @ApiOperation("查询某个门店下的设备列表")
    @UserLoginToken
//    @AdminToken
//    @PostMapping("/queryDeviceUnderStore")
    public RestResult getStoresUnderAgent(Integer storeId){
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

        try {
            DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
            userType = decodedJWT.getClaim("userType").asString();

            logger.info("  userType {} -- {} ",userType);
        } catch ( Exception e) {
        }

        if("admin".equals(userType)){
            logger.info("判断身份是管理员，直接查询所有 门店 & 设备列表");
            restResult = queryAllStoresAgent();
        }else {
            logger.info("判断身份是管理员，直接查询指定门店 & 设备列表");
            List<StoreAndMachineEntity> agentAndStoreEntities = machineDao.queryAllDeviceUderStores(storeId);
            restResult.setData(agentAndStoreEntities);
            restResult.setSuccess(true);
            restResult.setMsg("success");
        }

        return restResult;
    }


    @ApiOperation("查询所有门店列表 以及每个门店下包含的设备,暂时不用")
    @UserLoginToken
//    @AdminToken
//    @PostMapping("/getAllStoreAndDevice")
    public RestResult queryAllStoresAgent(){
        List<StoreAndMachineEntity> agentAndStoreEntities = machineDao.queryAllStoreDevice();
        RestResult restResult= new RestResult();
        restResult.setData(agentAndStoreEntities);
        restResult.setSuccess(true);
        restResult.setMsg("success");
        return restResult;
    }

   /* @ApiOperation("查询所有门店列表,以及每个门店下包含的设备数量")
    @UserLoginToken
    @PostMapping(value = "/storeInfoList")
    public RestResult  selectStoreInfoAndDeviceCount(){
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

        try {
            DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
            userType = decodedJWT.getClaim("userType").asString();
            userId = decodedJWT.getClaim("userId").asInt();

            logger.info("  userType {} -- {} ",userId ,userType);
        } catch ( Exception e) {
        }

        if("admin".equals(userType)){
            logger.info("判断身份是管理员，直接查询所有 门店 & 设备列表");
            List<Store> storeList=  storeService.selectStoreInfoAndDeviceCount(null);
            restResult.setCode(200);
            restResult.setData(storeList);
            restResult.setSuccess(true);
            restResult.setMsg("success");

        }else {
            List<Store> storeList=  storeService.selectStoreInfoAndDeviceCount(userId);
            restResult.setCode(200);
            restResult.setData(storeList);
            restResult.setSuccess(true);
            restResult.setMsg("success");
        }

       return restResult;
    }*/

    @ApiOperation("查询所有门店列表,以及每个门店下包含的设备数量,输入参数后 根据门店名字 门店联系人或者联系人电话 地址 模糊匹配门店列表,以及每个门店下包含的设备数量")
    @UserLoginToken
    @PostMapping(value = "/storeInfoListByParms")
    public RestResult  selectStoreInfoAndDeviceCountByStoreName(@RequestBody CommonRequest commonRequest){
        RestResult restResult = new RestResult();
        logger.info("selectStoreInfoAndDeviceCountByStoreName commonRequest = " + commonRequest);
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

        try {
            DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
            userType = decodedJWT.getClaim("userType").asString();
            userId = decodedJWT.getClaim("userId").asInt();

            logger.info("  userType {} -- {} ",userId ,userType);
        } catch ( Exception e) {
        }

        int currentPage =commonRequest.getCurPage();
        int pageSize = commonRequest.getPageSize();
        int pageIndex =(currentPage-1) * pageSize;
        if("admin".equals(userType)){
            List<Store> storeList = storeService.selectStoreInfoAndDeviceCountByStoreName(null ,commonRequest.getParms(),pageIndex,pageSize);
            restResult.setData(storeList);
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("success");
        }else {
            List<Store> storeList = storeService.selectStoreInfoAndDeviceCountByStoreName( userId,commonRequest.getParms(),pageIndex,pageSize);
            restResult.setData(storeList);
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("success");
        }

        return restResult;
    }

}
