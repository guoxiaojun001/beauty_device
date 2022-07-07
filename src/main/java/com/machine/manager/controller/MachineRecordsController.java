package com.machine.manager.controller;

import com.machine.manager.entity.WorkRecords;
import com.machine.manager.entity.machine.WorkRecordsRequest;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.PassToken;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.impl.WorkRecordsServiceImpl;
import com.machine.manager.util.AesEncryptUtils;
import com.machine.manager.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户操作
 *
 * @author guoxiaojun8804@126.com
 * @date 2021/02/16
 */
@RestController
@Api("设备操作")
@RequestMapping("/machine/records")
public class MachineRecordsController extends BaseController {
    @Autowired
    private WorkRecordsServiceImpl workRecordsService;

    @ApiOperation("新增记录信息")
    @PassToken
    @PostMapping("/addWorkRecords")
    public RestResult addWorkRecords(@RequestBody WorkRecords workRecords) {
        RestResult restResult = new RestResult();

        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == httpServletRequest ){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常1");
            restResult.setSuccess(false);
            return restResult;
        }

        String specialId = httpServletRequest.getHeader("blackId");
        logger.info("解密前 specialId ：" + specialId);
        try {
            specialId = AesEncryptUtils.decrypt(specialId);
            logger.info("解密后：" + specialId);

            String[] arr = specialId.split("#####");
            System.out.println("解密后：arr = " + arr[1]);
            long start = Long.parseLong(arr[1]);
            System.out.println("start = " + start);
            Thread.sleep(1000);
            long end = System.currentTimeMillis();
            System.out.println("end = " + end);
            long diff = end-start;
            System.out.println("diff = " + diff);

            if(diff >= 3000){
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("参数过期");
                return restResult;
            }

        } catch (Exception e) {
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg(e.getMessage());
            return restResult;
        }

        if( StringUtils.isEmpty(specialId)){
            System.out.print("提前判断权限，权限失败");
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("设备插入记录失败2");
            restResult.setSuccess(false);
            return restResult;
        }else {

            int result = workRecordsService.insertSelective(workRecords);
            if(result == 1){
                restResult.setCode(200);
                restResult.setSuccess(true);
                restResult.setMsg("添加记录成功");
            }else {
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("添加记录失败");
            }

            return restResult;
        }

    }


    @ApiOperation("删除设备记录信息")
    @UserLoginToken
    @PostMapping("/deleteWorkRecords")
    public RestResult deleteWorkRecords(Integer id) {
        RestResult pre = preCheck("deleteWorkRecords",MachineRecordsController.class);
        if(null != pre){
            System.out.print("提前判断权限，权限失败");
            return pre;
        }else {
            RestResult restResult = new RestResult();
            int result = workRecordsService.deleteByPrimaryKey(id);

            if(result == 1){
                restResult.setCode(200);
                restResult.setSuccess(true);
                restResult.setMsg("删除设备记录成功");
            }else {
                restResult.setCode(202);
                restResult.setSuccess(false);
                restResult.setMsg("删除设备记录失败");
            }

            return restResult;
        }

    }

    @ApiOperation("修改设备记录信息")
    @UserLoginToken
    @PostMapping("/updateWorkRecords")
    public RestResult updateWorkRecords(@RequestBody WorkRecords workRecords) {

        RestResult restResult = new RestResult();

        if(null == workRecords){
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("请求参数异常2");
            return restResult;
        }

        int result = workRecordsService.updateByPrimaryKey(workRecords);

        if(result == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("更新设备记录信息成功");
        }else {
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("更新设备记录信息失败");
        }

        return restResult;

    }


    @ApiOperation("查询设备使用记录信息")
    @UserLoginToken
    @PostMapping("/getMachineRecordsByMachineId")
    public RestResult getMachineRecordsByMachineId(WorkRecordsRequest request) {

        RestResult restResult = new RestResult();
        if(null == request){
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("请求参数异常2");
            return restResult;
        }


        List<WorkRecords> machineList = workRecordsService.getMachineRecordsByMachineId(request.getMachineId());
        if(machineList != null){
            restResult.setSuccess(true);
            restResult.setCode(200);
            restResult.setMsg("normal查询设备记录成功");
            restResult.setData(machineList);
        }else {
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("normal查询设备记录为空");
            restResult.setData(null);
        }

        return restResult;

    }

    @ApiOperation("查询所有设备使用记录信息")
    @PostMapping("/selectAll")
    @UserLoginToken
    public RestResult selectAll() {

        RestResult restResult = new RestResult();

        List<WorkRecords> machineList = workRecordsService.getMachineRecordsAll();

        if(machineList != null){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("查询设备记录成功");
            restResult.setData(machineList);
        }else {
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("查询设备记录为空");
            restResult.setData(null);
        }

        return restResult;

    }


    @ApiOperation("统计指定设备使用记录信息")
    @UserLoginToken
    @PostMapping("/sumRecordsById")
    public RestResult sumRecordsById(WorkRecordsRequest request) {

        RestResult restResult = new RestResult();

        System.out.println("sumRecordsById getMachineId =" +request.getMachineId());
        Integer sum = workRecordsService.sumRecordsById(request.getMachineId());
        System.out.println("sumRecordsById sum =" +sum);
        //select sum(if(type=1,money,0)) from user group by id;
        restResult.setCode(200);
        if(null != sum && sum >= 0){
            restResult.setSuccess(true);
            restResult.setMsg("统计查询设备记录成功");
            restResult.setData(sum);
        }else {
            restResult.setSuccess(true);
            restResult.setMsg("统计查询设备记录为空");
            restResult.setData(0);
        }

        return restResult;

    }


    @ApiOperation("统计所有设备使用记录信息")
    @PostMapping("/sumRecordsAll")
    @UserLoginToken
    public RestResult sumRecordsAll() {
        RestResult pre = preCheck("sumRecordsAll",MachineRecordsController.class);
        if(null != pre){
            System.out.print("提前判断权限，权限失败");
            return pre;
        }else {
            RestResult restResult = new RestResult();

            Integer sum = workRecordsService.sumRecordsAll();
            restResult.setCode(200);
            if(sum >= 0){
                restResult.setSuccess(true);
                restResult.setMsg("统计查询设备时长记录成功");
                restResult.setData(sum);
            }else {
                restResult.setSuccess(true);
                restResult.setMsg("统计查询设备时长记录为空");
                restResult.setData(0);
            }
            return restResult;
        }
    }

}
