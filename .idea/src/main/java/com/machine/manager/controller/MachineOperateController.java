package com.machine.manager.controller;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.service.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @ApiOperation("根据用户角色查询设备信息")
//    @GetMapping("/getMachineInfoByRole")
//    public MachineInfo getMachineInfo(MachineRequest request) {
//        return service.getMachineInfo(request);
//    }

    @ApiOperation("新增设备信息")
    @PostMapping("/addMachine")
    public RestResult addMachine(@RequestBody MachineInfo machineInfo) {
        RestResult restResult = new RestResult();

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


    @ApiOperation("新增设备使用记录")
    @PostMapping("/addRecord")
    public RestResult addRecord(@RequestBody MachineInfo machineInfo) {
        RestResult restResult = new RestResult();

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

    @ApiOperation("删除设备信息")
    @PostMapping("/deleteMachine")
    public RestResult deleteMachine(Integer id) {

        RestResult restResult = new RestResult();

        int result = service.deleteByPrimaryKey(id);
        restResult.setCode(200);
        if(result == 1){
            restResult.setSuccess(true);
            restResult.setMsg("删除设备成功");
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("删除设备失败");
        }

        return restResult;
    }

    @ApiOperation("修改设备信息")
    @PostMapping("/updateMachine")
    public RestResult updateMachine(@RequestBody MachineInfo machineInfo) {

        RestResult restResult = new RestResult();

        int result = service.updateByPrimaryKeySelective(machineInfo);
        restResult.setCode(200);
        if(result == 1){
            restResult.setSuccess(true);
            restResult.setMsg("更新设备信息成功");
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("更新设备信息失败");
        }

        return restResult;
    }



    @ApiOperation("管理员查询所有设备信息")
    @PostMapping("/selectAllByAdmin")
    public RestResult selectAllByAdmin() {

        RestResult restResult = new RestResult();

        List<MachineInfo> machineList = service.selectAllByAdmin();
        restResult.setCode(200);
        if(machineList != null){
            restResult.setSuccess(true);
            restResult.setMsg("管理员查询设备成功");
            restResult.setData(machineList);
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("管理员查询设备为空");
            restResult.setData(null);
        }

        return restResult;
    }



    @ApiOperation("经销商查询所有设备信息,传入用户id")
    @PostMapping("/selectAllByNormal")
    public RestResult selectAllByNormal(Integer id) {

        RestResult restResult = new RestResult();

        List<MachineInfo> machineList = service.selectAllByNormal(id);
        restResult.setCode(200);
        if(machineList != null){
            restResult.setSuccess(true);
            restResult.setMsg("normal查询设备成功");
            restResult.setData(machineList);
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("normal查询设备为空");
            restResult.setData(null);
        }

        return restResult;
    }
}
