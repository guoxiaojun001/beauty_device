package com.machine.manager.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * user_info
 *
 * @author
 */
@Data
public class UserInfo implements Serializable {
    @ApiModelProperty(value = "试卷ID",example = "1")
    private Integer id;

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private String email;

    @ApiModelProperty
    private String telephone;

    @ApiModelProperty
    private String address;

    @ApiModelProperty
    private String password;

    @ApiModelProperty
    private String roleId;

    private static final long serialVersionUID = 1L;
}