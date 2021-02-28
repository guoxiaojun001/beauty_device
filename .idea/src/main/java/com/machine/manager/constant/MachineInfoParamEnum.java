package com.machine.manager.constant;


/**
 * 设备种类
 *
 * @author guoxi_789@126.com
 * @date 2020/12/13
 */

public enum MachineInfoParamEnum {
    /***/
    PARAMA("PARAMA","参数A"),
    PARAMB("PARAMB","参数B"),
    PARAMC("PARAMC","参数C");
    /***/
    private String code;
    /***/
    private String desc;

    MachineInfoParamEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
