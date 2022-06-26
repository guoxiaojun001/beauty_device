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
public class CommonRequest {


    @ApiModelProperty("搜索参数")
    private String parms = "";

    @ApiModelProperty("请求页")
    private int page = 1;

    @ApiModelProperty("第几页")
    private int curPage =1;

    @ApiModelProperty("一页多少条")
    private int pageSize=1;

}
