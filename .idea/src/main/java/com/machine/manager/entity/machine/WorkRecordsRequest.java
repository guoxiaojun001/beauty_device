package com.machine.manager.entity.machine;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 不同用户查看设备信息
 *
 * @author guoxiaojun8804@126.com
 * @date 2021/02/16
 */
@Data
public class WorkRecordsRequest {

    @ApiModelProperty("设备id")
    private Integer machineId;
}
