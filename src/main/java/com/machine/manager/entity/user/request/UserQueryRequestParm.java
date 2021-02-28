package com.machine.manager.entity.user.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户列表实体类
 *
 * @author guoxixiaojun8804@126.com
 * @date 2021/1/17
 */
@Data
public class UserQueryRequestParm {
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("用户手机号")
    private String telephone;
    @ApiModelProperty("用户名")
    private String name;
}
