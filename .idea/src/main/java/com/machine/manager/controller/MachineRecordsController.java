package com.machine.manager.controller;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.WorkRecords;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.WorkRecordsRequest;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.service.MachineService;
import com.machine.manager.service.impl.WorkRecordsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("根据用户角色查询设备记录信息")
    @GetMapping("/getWorkRecordsByRole")
    public WorkRecords getWorkRecordsByRole(WorkRecordsRequest request) {
        return workRecordsService.selectByPrimaryKey(request);
    }

    @ApiOperation("新增记录信息")
    @PostMapping("/addWorkRecords")
    public RestResult addMachine(@RequestBody WorkRecords workRecords) {
        RestResult restResult = new RestResult();

        int result = workRecordsService.insertSelective(workRecords);
        restResult.setCode(200);
        if(result == 1){
            restResult.setSuccess(true);
            restResult.setMsg("添加记录成功");
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("添加记录失败");
        }

        return restResult;
    }


    @ApiOperation("删除设备记录信息")
    @PostMapping("/deleteWorkRecords")
    public RestResult deleteMachine(Integer id) {

        RestResult restResult = new RestResult();
        int result = workRecordsService.deleteByPrimaryKey(id);
        restResult.setCode(200);
        if(result == 1){
            restResult.setSuccess(true);
            restResult.setMsg("删除设备记录成功");
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("删除设备记录失败");
        }

        return restResult;
    }

    @ApiOperation("修改设备记录信息")
    @PostMapping("/updateWorkRecords")
    public RestResult updateMachine(@RequestBody WorkRecords workRecords) {

        RestResult restResult = new RestResult();

        int result = workRecordsService.updateByPrimaryKey(workRecords);
        restResult.setCode(200);
        if(result == 1){
            restResult.setSuccess(true);
            restResult.setMsg("更新设备记录信息成功");
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("更新设备记录信息失败");
        }

        return restResult;
    }


    @ApiOperation("查询设备使用记录信息")
    @PostMapping("/selectAllWorkRecords")
    public RestResult selectAllWorkRecords(WorkRecordsRequest request) {

        RestResult restResult = new RestResult();

        List<WorkRecords> machineList = workRecordsService.getMachineRecords(request);
        restResult.setCode(200);
        if(machineList != null){
            restResult.setSuccess(true);
            restResult.setMsg("normal查询设备记录成功");
            restResult.setData(machineList);
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("normal查询设备记录为空");
            restResult.setData(null);
        }

        return restResult;
    }

    @ApiOperation("查询所有设备使用记录信息")
    @PostMapping("/selectAll")
    public RestResult selectAll() {

        RestResult restResult = new RestResult();

        List<WorkRecords> machineList = workRecordsService.getMachineRecordsAll();
        restResult.setCode(200);
        if(machineList != null){
            restResult.setSuccess(true);
            restResult.setMsg("查询设备记录成功");
            restResult.setData(machineList);
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("查询设备记录为空");
            restResult.setData(null);
        }

        return restResult;
    }



    @ApiOperation("统计指定设备使用记录信息")
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
            restResult.setSuccess(false);
            restResult.setMsg("统计查询设备记录为空");
            restResult.setData(0);
        }

        return restResult;
    }


    @ApiOperation("统计所有设备使用记录信息")
    @PostMapping("/sumRecordsAll")
    public RestResult sumRecordsAll() {

        RestResult restResult = new RestResult();

        Integer sum = workRecordsService.sumRecordsAll();
        restResult.setCode(200);
        if(sum >= 0){
            restResult.setSuccess(true);
            restResult.setMsg("统计查询设备记录成功");
            restResult.setData(sum);
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("统计查询设备记录为空");
            restResult.setData(0);
        }

        return restResult;
    }

}
