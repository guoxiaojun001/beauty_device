package com.machine.manager.entity.user.respone;

import lombok.Data;

import java.io.Serializable;
/**
 * 用户登录返回信息
 *
 * @author guoxi_789@126.com
 * @date 2021/1/10
 */
@Data
public class UserLoginInResp implements Serializable {
    private String userType;
    private String token;
    private boolean isSuccess;
}
