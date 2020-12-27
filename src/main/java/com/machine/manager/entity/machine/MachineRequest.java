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
public class MachineRequest {

    @ApiModelProperty("用户角色")
    private String role;

    @ApiModelProperty("用户id")
    private Integer userId;
}
