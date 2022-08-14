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
public class UserTimeRequest {


    @ApiModelProperty("累计使用时长")
    private int totalTime  ;

    @ApiModelProperty("单此使用时长")
    private int leftTime;

    @ApiModelProperty("真实地址")
    private String realAddress;

}
