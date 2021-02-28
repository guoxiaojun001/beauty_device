package com.machine.manager.controller;

import com.machine.manager.constant.UserRoleEnum;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.service.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation("查询所有设备信息,管理员传id+admin，普通用户user+id,名称 省市 可选参数")
    @PostMapping("/selectAllMachineList")
    public RestResult selectAllMachineList(@RequestBody MachineRequest request) {

        RestResult restResult = new RestResult();
        List<MachineInfo> machineList;
        if(null == request ||  null == request.getUserId()
            /* &&checkValue(request.getRole())*/){
            restResult.setCode(200);
            restResult.setData("no data");
            restResult.setMsg("查询不到有效数据");
            restResult.setSuccess(false);
            return restResult;
        }

        System.out.print("查询 设备信息:" + request );

        if(checkValue(request.getDeviceName())){
            if(checkValue(request.getMachineProviceId())){
                if(checkValue(request.getMachineCityId())){
                    //TODO  根据名称， 省，市来综合查询
                    System.out.print("根据名称， 省，市来综合查询"  );
                    machineList = service.selectByNameProvCity(request);
                }else {
                    //TODO  根据名称 ，省 来综合查询
                    System.out.print(" 根据名称 ，省 来综合查询"  );
                    machineList = service.selectByNameProv(request);
                }
            }else {
                //TODO  根据 名称来综合查询
                System.out.print("根据 名称来综合查询:"  );
                return selectByType(request.getDeviceName());
            }
        }else {

            if(checkValue(request.getMachineProviceId())){
                if(checkValue(request.getMachineCityId())){
                    //TODO  根据  省，市来综合查询
                    System.out.print("根据  省，市来综合查询"  );
                    machineList = service.selectByProvCity(request);
                }else {
                    //TODO  根据 省 来综合查询
                    System.out.print(" 根据 省 来综合查询"  );
                    machineList = service.selectByProv(request);
                }
            }else {
                //TODO 管理员查询所有
                if (UserRoleEnum.ADMIN.getCode().equals(request.getRole())) {
                    System.out.print("根据 管理员查询所有:"  );
                    return   selectAllByAdmin();
                }else {
                    System.out.print("根据 user查询所有:"  );
                    return   selectAllByNormal(request.getUserId());
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



    //    @ApiOperation("经销商查询所有设备信息,传入用户id")
//    @PostMapping("/selectAllByNormal")
    public RestResult selectAllByNormal(Integer id) {

        RestResult restResult = new RestResult();
        System.out.print("经销商查询所有设备信息 " + id);
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



    public RestResult selectByType(String type) {

        RestResult restResult = new RestResult();
        System.out.print("经销商查询所有设备信息 " + type);
        List<MachineInfo> machineList = service.selectByType(type);
        restResult.setCode(200);
        if(machineList != null){
            restResult.setSuccess(true);
            restResult.setMsg("根据设备名称查询设备成功");
            restResult.setData(machineList);
        }else {
            restResult.setSuccess(false);
            restResult.setMsg("根据设备名称查询设备为空");
            restResult.setData(null);
        }

        return restResult;
    }
}
