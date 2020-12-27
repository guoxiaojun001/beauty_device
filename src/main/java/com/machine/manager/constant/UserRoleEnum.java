package com.machine.manager.constant;


/**
 * 设备种类
 *
 * @author guoxi_789@126.com
 * @date 2020/12/13
 */

public enum UserRoleEnum {
    /***/
    ADMIN("ADMIN","管理员"),
    NORMAL("NORMAL","普通用户");
    /***/
    private String code;
    /***/
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    UserRoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
