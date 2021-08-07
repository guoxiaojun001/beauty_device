package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.config.SecurityConfig;
import com.machine.manager.constant.UserRoleEnum;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.WorkRecords;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.MachineRequestAfter;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.AdminToken;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.MachineService;
import com.machine.manager.service.UserService;
import com.machine.manager.service.impl.WorkRecordsServiceImpl;
import com.machine.manager.util.RequestUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

import static com.machine.manager.controller.UserOperateController.checkValue;

/**
 * 用户操作
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@RestController
@Api("设备操作")
@RequestMapping("/machine")
public class MachineOperateController extends BaseController {
    @Autowired
    private MachineService service;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkRecordsServiceImpl workRecordsService;


    @ApiOperation("新增设备信息,machineParam为设备id，唯一性")
    @UserLoginToken
    @AdminToken
    @PostMapping("/addMachine")
    public RestResult addMachine(@RequestBody MachineInfo machineInfo) {
        RestResult restResult = new RestResult();

        if(null == machineInfo ){
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("请求参数异常");
            return restResult;
        }

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


        UserInfo qu =  userService. selectByPrimaryKey(machineInfo.getUserId());
        MachineInfo machineInfo1 = service.selectDeviceId(machineInfo.getMachineParam());
        if(null == qu ){
            System.out.print("指定的用户id 不存在");
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("指定的用户id 不存在");
            return restResult;
        }else if(machineInfo1 != null){
            System.out.print("指定的设备id 已存在");
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("指定的设备id 已存在");
            return restResult;
        }else {
            machineInfo.setUserName(qu.getUsername());
            System.out.print("添加设备getUserName = " + qu.getUsername());
            int result = service.insertSelective(machineInfo);
            restResult.setCode(200);
            if(result == 1){
                restResult.setSuccess(true);
                restResult.setMsg("添加设备成功");
            }else {
                restResult.setSuccess(false);
                restResult.setMsg("添加设备失败");
            }

            return restResult;
        }

    }

    @ApiOperation("删除设备信息")
    @UserLoginToken
    @AdminToken
    @PostMapping("/deleteMachine")
    public RestResult deleteMachine(Integer id) {

        RestResult restResult = new RestResult();

        RestResult pre = preCheck("deleteMachine",MachineOperateController.class);
        if(null != pre){
            System.out.print("提前判断权限，权限失败");
            return pre;
        }else {
            int result = service.deleteByPrimaryKey(id);
            restResult.setCode(200);
            if(result == 1){
                restResult.setSuccess(true);
                restResult.setMsg("删除设备成功");
            }else {
                restResult.setSuccess(false);
                restResult.setMsg("删除设备失败,或者没有此设备");
            }

            return restResult;
        }


    }

    @ApiOperation("修改设备信息")
    @UserLoginToken
    @PostMapping("/updateMachine")
    public RestResult updateMachine(@RequestBody MachineInfo machineInfo) {

        RestResult restResult = new RestResult();

        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == httpServletRequest ){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常3");
            restResult.setSuccess(false);
            return restResult;
        }

        String token = httpServletRequest.getHeader("token");
        if(null == token || "".equals(token)){
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("请求token为空3");
            return restResult;
        }else {
            UserInfo qu =  userService. selectByPrimaryKey(machineInfo.getUserId());
            if(null == qu ){
                System.out.print("指定的用户id 不存在2");
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("指定的用户id 不存在2");
                return restResult;
            }

            System.out.print("修改设备getUserName = " + qu.getUsername());
            machineInfo.setUserName(qu.getUsername());
            int result = service.updateByPrimaryKeySelective(machineInfo);

            if(result == 1){
                restResult.setCode(200);
                restResult.setSuccess(true);
                restResult.setMsg("更新设备信息成功,更新记录表");

                WorkRecords workRecords = new WorkRecords();
                workRecords.setMachineId(machineInfo.getId());
                workRecords.setUsedDuration(machineInfo.getUsedDuration());
                workRecordsService.updateByPrimaryKey(workRecords);
            }else {
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("更新设备信息失败");
            }

            return restResult;
        }

    }



    @ApiOperation("查询所有设备信息,用户id， 名称 省市 可选参数")
    @UserLoginToken
    @PostMapping("/selectAllMachineList")
    public RestResult MachineInfoDao(@RequestBody MachineRequest request) {

        List<MachineInfo> machineList;
        RestResult restResult = new RestResult();
        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == request || null == httpServletRequest){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("查询不到有效数据");
            restResult.setSuccess(false);
            return restResult;
        }

        String token =  httpServletRequest.getHeader("token");
        if(StringUtils.isEmpty(token)){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("请求token为空");
            restResult.setSuccess(false);
            return restResult;
        }

        System.out.print("查询 设备信息:" + request );
        MachineRequestAfter machineRequestAfter = new MachineRequestAfter(request);
        try {
            DecodedJWT decodedJWT = JwtTokenUtil222 .getTokenInfo(token);
            int userId = decodedJWT.getClaim("userId").asInt();
            String userName = decodedJWT.getClaim("userName").asString();
            String userType = decodedJWT.getClaim("userType").asString();

            machineRequestAfter.setRole(userType);
            machineRequestAfter.setUserId(userId);

            System.out.print("查询 machineRequestAfter:" + machineRequestAfter.toString() );

        } catch ( Exception e) {
            machineRequestAfter.setRole("");
            machineRequestAfter.setUserId(-1);
        }

        machineList = service.selectCommon(machineRequestAfter);

        System.out.print("设置 设备id machineList :" +machineList.toString() );

        if(null != machineList && machineList.size() > 0){

            for (MachineInfo machineInfo : machineList){
                if(null == workRecordsService.sumRecordsById(machineInfo.getId())){
                    machineInfo.setUsedDuration(0);
                }else{
                    machineInfo.setUsedDuration(workRecordsService.sumRecordsById(machineInfo.getId()));
                }

                System.out.print("设置 设备id 时长getUsedDuration :" +machineInfo.getUsedDuration() );
            }

            restResult.setCode(200);
            restResult.setData(machineList);
            restResult.setMsg("查询设备数据");
            restResult.setSuccess(true);
            return restResult;
        }else {
            restResult.setCode(200);
            restResult.setData(machineList);
            restResult.setMsg("查询0条设备数据");
            restResult.setSuccess(true);
            return restResult;
        }


    }


    @ApiOperation("查询所有设备信息,管理员传id+admin，普通用户user+id,名称 省市 可选参数")
    @UserLoginToken
//    @PostMapping("/selectAllMachineList")
    public RestResult selectAllMachineList(@RequestBody MachineRequest request) {

        RestResult restResult = new RestResult();

        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == httpServletRequest){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("查询不到有效数据1");
            restResult.setSuccess(false);
            return restResult;
        }
        String token = httpServletRequest.getHeader("token");

        List<MachineInfo> machineList;
        if(StringUtils.isEmpty(token)){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("token 为空");
            restResult.setSuccess(false);
            return restResult;
        }

        System.out.print("查询 设备信息:" + request );
        MachineRequestAfter machineRequestAfter = new MachineRequestAfter(request);
        try {
            DecodedJWT decodedJWT = JwtTokenUtil222 .getTokenInfo(token);
            int userId = decodedJWT.getClaim("userId").asInt();
            String userName = decodedJWT.getClaim("userName").asString();
            String userType = decodedJWT.getClaim("userType").asString();

            machineRequestAfter.setRole(userType);
            machineRequestAfter.setUserId(userId);

            System.out.print("查询 machineRequestAfter:" + machineRequestAfter.toString() );

        } catch ( Exception e) {
            machineRequestAfter.setRole("");
            machineRequestAfter.setUserId(-1);
        }


        if(checkValue(request.getMachineBrand())){
            if(checkValue(request.getMachineProviceId())){
                if(checkValue(request.getMachineCityId())){
                    //TODO  根据名称， 省，市来综合查询
                    System.out.print("根据名称， 省，市来综合查询"  );
                    machineList = service.selectByNameProvCity(machineRequestAfter);
                }else {
                    //TODO  根据名称 ，省 来综合查询
                    System.out.print(" 根据名称 ，省 来综合查询"  );
                    machineList = service.selectByNameProv(machineRequestAfter);
                }
            }else {
                //TODO  根据 名称来综合查询
                System.out.print("根据 名称来综合查询:"  );
                return selectByType(machineRequestAfter);
            }
        }else {

            if(checkValue(request.getMachineProviceId())){
                if(checkValue(request.getMachineCityId())){
                    //TODO  根据  省，市来综合查询
                    System.out.print("根据  省，市来综合查询"  );
                    machineList = service.selectByProvCity(machineRequestAfter);
                }else {
                    //TODO  根据 省 来综合查询
                    System.out.print(" 根据 省 来综合查询"  );
                    machineList = service.selectByProv(machineRequestAfter);
                }
            }else {
                //TODO 管理员查询所有
                if (UserRoleEnum.ADMIN.getCode().equals(machineRequestAfter.getRole())) {
                    System.out.print("根据 管理员查询所有:"  );
                    return   selectAllByAdmin();
                }else {
                    System.out.print("根据 user查询所有:"  );
                    return   selectAllByNormal(machineRequestAfter.getUserId());
                }
            }
        }

        restResult.setCode(200);
        restResult.setData(machineList);
        restResult.setMsg("查询设备数据");
        restResult.setSuccess(true);
        return restResult;

    }


    //    @ApiOperation("管理员查询所有设备信息")
//    @PostMapping("/selectAllByAdmin")
    public RestResult selectAllByAdmin() {

        RestResult restResult = new RestResult();
        System.out.print("管理员查询所有设备信息");
        List<MachineInfo> machineList = service.selectAllByAdmin();

        if(machineList != null){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("管理员查询设备成功");
            restResult.setData(machineList);
        }else {
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("管理员查询设备为空");
            restResult.setData(null);
        }

        return restResult;

    }



    //    @ApiOperation("经销商查询所有设备信息,传入用户id")
//    @PostMapping("/selectAllByNormal")
    public RestResult selectAllByNormal(Integer id) {

        RestResult restResult = new RestResult();
        System.out.print("经销商查询所有设备信息 " + id);
        List<MachineInfo> machineList = service.selectAllByNormal(id);

        if(machineList != null){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("normal查询设备成功");
            restResult.setData(machineList);
        }else {
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("normal查询设备为空");
            restResult.setData(null);
        }

        return restResult;
    }



    public RestResult selectByType(MachineRequestAfter machineRequestAfter) {

        RestResult restResult = new RestResult();
        System.out.print("经销商查询所有设备信息 " + machineRequestAfter.toString());
        List<MachineInfo> machineList = service.selectByBrand(machineRequestAfter);

        if(machineList != null){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("根据设备名称查询设备成功");
            restResult.setData(machineList);
        }else {
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("根据设备名称查询设备为空");
            restResult.setData(null);
        }

        return restResult;
    }
}
