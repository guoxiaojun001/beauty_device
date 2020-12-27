package com.machine.manager.constant;


/**
 * 设备种类
 *
 * @author guoxi_789@126.com
 * @date 2020/12/13
 */

public enum MachineInfoFunctionEnum {
    /***/
    FUNCTIONA("FUNCTIONA","功能A"),
    FUNCTIONB("FUNCTIONB","功能B"),
    FUNCTIONC("FUNCTIONC","功能C");
    /***/
    private String code;
    /***/
    private String desc;

    MachineInfoFunctionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
