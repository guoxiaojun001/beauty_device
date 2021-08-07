package com.machine.manager.entity.machine;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 不同用户查看设备信息
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@Data
public class MachineRequestAfter {

    public MachineRequestAfter(){}

    public MachineRequestAfter(MachineRequest request) {
        this.machineBrand = request.getMachineBrand();
        this.machineProviceId = request.getMachineProviceId();
        this.machineCityId = request.getMachineCityId();
        this.curPage = request.getCurPage();
        this.pageSize = request.getPageSize();
    }

    @Override
    public String toString() {
        return "MachineRequestAfter{" +
                "role='" + role + '\'' +
                ", userId=" + userId +
                ", machineBrand='" + machineBrand + '\'' +
                ", machineProviceId='" + machineProviceId + '\'' +
                ", machineCityId='" + machineCityId + '\'' +
                ", curPage=" + curPage +
                ", pageSize=" + pageSize +
                '}';
    }

    @ApiModelProperty("用户角色")
    private String role;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("设备名称")
    private String machineBrand;

    @ApiModelProperty("设备省")
    private String machineProviceId;

    @ApiModelProperty("设备市")
    private String machineCityId;

    @ApiModelProperty("第几页")
    private int curPage;

    @ApiModelProperty("一页多少条")
    private int pageSize;
}
