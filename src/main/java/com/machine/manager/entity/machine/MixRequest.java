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
public class MixRequest extends MachineRequest {

    @ApiModelProperty("搜索关键字")
    private String keywords;

}
