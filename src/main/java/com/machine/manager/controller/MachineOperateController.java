package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.config.SecurityConfig;
import com.machine.manager.constant.UserRoleEnum;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.WebData;
import com.machine.manager.entity.WorkRecords;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.MachineRequestAfter;
import com.machine.manager.entity.machine.MachintCount;
import com.machine.manager.entity.machine.MixRequest;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


        //插入设备时 用户id 通过选择，或者不填写，为空
//        UserInfo qu =  userService. selectByPrimaryKey(machineInfo.getUserId());

//        MachineInfo machineInfo1 = service.selectDeviceId(machineInfo.getMachineParam());
     /*   if(null == qu ){
            System.out.print("指定的用户id 不存在");
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("指定的用户id 不存在");
            return restResult;
        }else if(machineInfo1 != null){
            System.out.print("指定的设备id 已存在" + machineInfo1.getId());
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("指定的设备id 已存在");
            return restResult;
        }else */{
//            machineInfo.setUserName(qu.getUsername());
//            System.out.print("添加设备getUserName = " + qu.getUsername());
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
            //TODO 所有者id  通过选择添加，不能手动输入
//            UserInfo qu =  userService. selectByPrimaryKey(machineInfo.getUserId());
//            if(null == qu ){
//                System.out.print("指定的用户id 不存在2");
//                restResult.setCode(202);
//                restResult.setSuccess(false);
//                restResult.setMsg("指定的用户id 不存在2");
//                return restResult;
//            }

//            System.out.print("修改设备getUserName = " + qu.getUsername());
//            machineInfo.setUserName(qu.getUsername());

            //TODO 使用时长暂时不提供修改 只有重置0
            if(machineInfo.getUsedDuration() == 0) {
                System.out.print("如果指定了 使用时长为0，那么就重置为0");
                machineInfo.setUsedDuration(0);
            }
            int result = service.updateByPrimaryKeySelective(machineInfo);
//            if(machineInfo.getUsedDuration() == -1){
//                System.out.print("如果指定了 使用时长为-1，那么就重置为0");
//                List<WorkRecords> workRecordsList = workRecordsService.getMachineRecordsByMachineId(machineInfo.getId());
//                System.out.print("记录信息 workRecordsList = " + workRecordsList);
//                if(null != workRecordsList && !workRecordsList.isEmpty()){
//                    for(WorkRecords workRecords : workRecordsList){
//                        if(workRecords.getMachineId() == machineInfo.getId()){
//                            workRecordsService.deleteByDeviceId(workRecords.getMachineId());
//                            System.out.print("删除此条记录信息 workRecords = " + workRecords);
//                        }
//                    }
//                }
//            }

            if(result == 1){
                restResult.setCode(200);
                restResult.setSuccess(true);
                restResult.setMsg("更新设备信息成功,更新记录表");

//                WorkRecords workRecords = new WorkRecords();
//                workRecords.setMachineId(machineInfo.getId());
//                workRecords.setUsedDuration(machineInfo.getUsedDuration());
//                workRecordsService.updateByPrimaryKey(workRecords);
            }else {
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("更新设备信息失败");
            }

            return restResult;
        }

    }



//    @ApiOperation("查询所有设备信息,用户id， 名称 省市 可选参数")
//    @UserLoginToken
//    @PostMapping("/selectAllMachineList")
//    public RestResult machineInfoDao(@RequestBody MachineRequest request) {
//
//        List<WebData> webDataList = new ArrayList<>();
//        List<MachineInfo> machineList;
//        RestResult restResult = new RestResult();
//        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
//        if(null == request || null == httpServletRequest){
//            restResult.setCode(202);
//            restResult.setData("no data");
//            restResult.setMsg("查询不到有效数据");
//            restResult.setSuccess(false);
//            return restResult;
//        }
//
//        String token =  httpServletRequest.getHeader("token");
//        if(StringUtils.isEmpty(token)){
//            restResult.setCode(202);
//            restResult.setData("no data");
//            restResult.setMsg("请求token为空");
//            restResult.setSuccess(false);
//            return restResult;
//        }
//
//        System.out.print("查询 设备信息:" + request );
//        MachineRequestAfter machineRequestAfter = new MachineRequestAfter(request);
//        try {
//            DecodedJWT decodedJWT = JwtTokenUtil222 .getTokenInfo(token);
//            int userId = decodedJWT.getClaim("userId").asInt();
//            String userName = decodedJWT.getClaim("userName").asString();
//            String userType = decodedJWT.getClaim("userType").asString();
//
//            machineRequestAfter.setRole(userType);
//            machineRequestAfter.setUserId(userId);
//
//            System.out.print("查询 machineRequestAfter:" + machineRequestAfter.toString() );
//
//        } catch ( Exception e) {
//            machineRequestAfter.setRole("");
//            machineRequestAfter.setUserId(-1);
//        }
//
//        machineList = service.selectCommon(machineRequestAfter);
//
//        System.out.print("设置 设备id machineList :" +machineList  );
//        webDataList.clear();
//        if(null != machineList && machineList.size() > 0){
//            WebData webData = new WebData();
//            for (MachineInfo machineInfo : machineList){
//
//                if(null == workRecordsService.sumRecordsById(machineInfo.getId())){
//                    machineInfo.setUsedDuration(0);
//                }else{
//                    machineInfo.setUsedDuration(workRecordsService.sumRecordsById(machineInfo.getId()));
//                }
//
//                webData.setMachineInfo(machineInfo);
//                if(null != machineInfo.getUserId() && -1 != machineInfo.getUserId()){
//                    UserInfo userInfo = userService.selectByPrimaryKey(machineInfo.getUserId());
//                    System.out.print("根据设备中的用户id 查询到的用户信息:" + userInfo );
//                    webData.setUserInfo(userInfo);
//                }
//
//                webDataList.add(webData);
//                System.out.print("设置 设备id 时长getUsedDuration :" +machineInfo.getUsedDuration() );
//            }
//
//            restResult.setCode(200);
//            restResult.setData(webDataList);
//            restResult.setMsg("查询设备数据");
//            restResult.setSuccess(true);
//            return restResult;
//        }else {
//            restResult.setCode(200);
//            restResult.setData(webDataList);
//            restResult.setMsg("查询0条设备数据");
//            restResult.setSuccess(true);
//            return restResult;
//        }
//
//    }



    @ApiOperation("查询所有设备信息,用户id， 名称 省市 可选参数")
    @UserLoginToken
    @PostMapping("/mixSearchMachineList")
    public RestResult mixSearchMachineList(@RequestBody MixRequest request) {

        List<WebData> webDataList = new ArrayList<>();
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
//            String userName = decodedJWT.getClaim("userName").asString();
            String userType = decodedJWT.getClaim("userType").asString();


            machineRequestAfter.setRole(userType);
            machineRequestAfter.setUserId(userId);

            System.out.print("查询 machineRequestAfter:" + machineRequestAfter.toString() );

        } catch ( Exception e) {
            machineRequestAfter.setRole("");
            machineRequestAfter.setUserId(-1);
        }


        //根据 姓名查询到用户信息
        List<UserInfo> userNameList = null;
        List<UserInfo> userPhoneList = null;
        List<UserInfo> otherList = null;
        if(null == request.getKeywords() || "".equals(request.getKeywords().replace(" ",""))){
            System.out.print("无条件查询==>" );
        }else {
            userNameList  = userService.selectByName(request.getKeywords());
            System.out.print("根据 姓名查询 userNameList:" + userNameList);
            userPhoneList  = userService.selectByPhone(request.getKeywords());
            System.out.print("根据 手机号查询 userPhoneList:" + userPhoneList);

            otherList = new ArrayList<>();

            if(null != userNameList && !userNameList.isEmpty()){
                otherList.addAll(userNameList);
            }

            if(null != userPhoneList && !userPhoneList.isEmpty()){
                for(UserInfo p : userPhoneList){
                    if(otherList.contains(p)){
                        System.out.print("------------------------根据 已经包含，不用添加:" + p.toString());
                    }else {
                        otherList.add(p);
                        System.out.print("--------------------根据 没有包含，添加:" + p.toString());
                    }
                }
            }
        }


        System.out.print("-=------------根据 otherList ===>" + otherList);
        webDataList.clear();

        if(null != otherList && otherList.size() > 0){
            WebData webData = new WebData();
            for (UserInfo uInfo : otherList){
                List<MachineInfo> machineInfos = service.selectByUserId(uInfo.getId());
                for(MachineInfo info : machineInfos){
//                    if(null == workRecordsService.sumRecordsById(info.getId())){
//                        info.setUsedDuration(0);
//                    }else{
//                        info.setUsedDuration(workRecordsService.sumRecordsById(info.getId()));
//                    }

                    webData.setMachineInfo(info);
                    if(null != info.getUserId() && -1 != info.getUserId()){
                        System.out.print("---------------设置用户数据 = " + uInfo);
                        webData.setUserInfo(uInfo);
                    }

                    if(!webDataList.contains(webData)){
                        System.out.print("---------------不包含此数据 = " + webData);
                        webDataList.add(webData);
                    }
                    System.out.print("XXXX uInfo = " + uInfo);
                }
            }
        }

        System.out.print("==================根据   webDataList:" + webDataList);

        machineList = service.selectCommon(machineRequestAfter);
        System.out.print("==============根据 设备名称查询 machineList:" + machineList);

        if(null != machineList && machineList.size() > 0){

            for (MachineInfo machineInfo : machineList){
                WebData webData = new WebData();

//                if(null == workRecordsService.sumRecordsById(machineInfo.getId())){
//                    machineInfo.setUsedDuration(0);
//                }else{
//                    machineInfo.setUsedDuration(workRecordsService.sumRecordsById(machineInfo.getId()));
//                }


                System.out.print("================数据machineInfo:" + machineInfo.toString() );

                webData.setMachineInfo(machineInfo);

                if(null != machineInfo.getUserId() && -1 != machineInfo.getUserId()){
                    UserInfo userInfo = userService.selectByPrimaryKey(machineInfo.getUserId());
                    System.out.print("根据设备中的用户id 查询到的用户信息:" + userInfo );
                    webData.setUserInfo(userInfo);
                }


                if(webDataList.contains(webData)){
                    System.out.print("======AAAA========================已经包含这数据:" + webData.toString() );
                }else {
                    System.out.print("=======BBBBB==============不包含 webData:" + webData );
                    webDataList.add(webData);
                }
                System.out.print("设置 设备id 时长getUsedDuration :" +machineInfo.getUsedDuration() );
            }

            restResult.setCode(200);
            restResult.setData(webDataList);
            restResult.setMsg("查询设备数据");
            restResult.setSuccess(true);
            return restResult;
        }else {
            restResult.setCode(200);
            restResult.setData(webDataList);
            restResult.setMsg("查询0条设备数据");
            restResult.setSuccess(true);
            return restResult;
        }

    }


    @ApiOperation("更新设备使用时长")
//    @PostMapping("/updateUsedTime")
    @RequestMapping(value = "/updateUsedTime", method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
    public RestResult updateUsedTime( int duration){
        RestResult restResult = new RestResult();
        System.out.print("duration==>" + duration);
        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == httpServletRequest ){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常3");
            restResult.setSuccess(false);
            return restResult;
        }

        String machineParam = httpServletRequest.getHeader("blackId");
        System.out.print("machineParam==>" + machineParam);
        if(null == machineParam || "".equals(machineParam)){
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("请求blackId为空");
            return restResult;
        }else {
            //TODO 所有者id  通过选择添加，不能手动输入
//            MachineInfo xx = service.selectDeviceId("12324");
//            System.out.print("qu1==>" + xx);
            MachineInfo qu = service.selectDeviceId(machineParam);
            System.out.print("qu==>" + qu);
            if(null == qu ){
                System.out.print("指定的设备id 不存在2");
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("指定的设备id 不存在2");
                return restResult;
            }

            int dura = qu.getUsedDuration();
            System.out.println("已经使用时长 dura = " + dura);
            qu.setUsedDuration(duration + dura);
            System.out.println("已经使用时长 最新 = " + qu.getUsedDuration());

            //TODO 使用时长暂时不提供修改 只有重置0
            int result = service.updateByPrimaryKeySelective(qu);
            System.out.println(" 最新 result= " + result);

            if(result == 1){
                restResult.setCode(200);
                restResult.setSuccess(true);
                restResult.setMsg("更新设备时长信息成功,更新记录表");

            }else {
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("更新设备时长信息失败");
            }

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


        if(!StringUtils.isEmpty(request.getMachineType())){
            if(!StringUtils.isEmpty(request.getMachineProviceId())){
                if(!StringUtils.isEmpty(request.getMachineCityId())){
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

            if(!StringUtils.isEmpty(request.getMachineProviceId())){
                if(!StringUtils.isEmpty(request.getMachineCityId())){
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


    @ApiOperation("设备类型排名")
    @PostMapping("/deviceRanking")
    public RestResult deviceRanking() {
        RestResult restResult = new RestResult();

        List<MachintCount> machineList = service.selectByDevType();

        restResult.setCode(200);
        restResult.setData(machineList);
        restResult.setSuccess(true);
        return restResult;
    }


    @ApiOperation("设备位置地图显示")
    @PostMapping("/deviceMapDisplay")
    public RestResult deviceMapDisplay() {
        RestResult restResult = new RestResult();

        List<MachintCount> machineList = service.selectByDevLocation();

        restResult.setCode(200);
        restResult.setData(machineList);
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
