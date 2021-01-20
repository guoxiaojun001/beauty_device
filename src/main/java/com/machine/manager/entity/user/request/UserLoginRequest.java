package com.machine.manager.entity.user.request;

import lombok.Data;

/**
 * 登录请求request
 *
 * @author guoxi_789@126.com
 * @date 2021/1/11
 */
@Data
public class UserLoginRequest {
    private String userName;
    private String passWord;
}
