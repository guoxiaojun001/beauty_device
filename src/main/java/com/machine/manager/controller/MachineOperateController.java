package com.machine.manager.controller;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.service.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户操作
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@RestController
@Api("设备操作")
@RequestMapping("/machine")
public class MachineOperateController {
    @Autowired
    private MachineService service;

    @ApiOperation("根据用户角色查询设备信息")
    @GetMapping("/getMachineInfoByRole")
    public MachineInfo getMachineInfo(MachineRequest request) {
        return service.getMachineInfo(request);
    }

    @ApiOperation("新增设备信息")
    @PostMapping("/addMachine")
    public int addMachine(@RequestBody MachineInfo machineInfo) {
        return service.insertSelective(machineInfo);
    }

    @ApiOperation("删除设备信息")
    @PostMapping("/deleteMachine")
    public int deleteMachine(Integer id) {
        return service.deleteByPrimaryKey(id);
    }

    @ApiOperation("修改设备信息")
    @PostMapping("/updateMachine")
    public int updateMachine(@RequestBody MachineInfo machineInfo) {
        return service.updateByPrimaryKeySelective(machineInfo);
    }

}
