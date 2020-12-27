package com.machine.manager.constant;


/**
 * 设备种类
 *
 * @author guoxi_789@126.com
 * @date 2020/12/13
 */

public enum MachineInfoTypeEnum {
    /***/
    A("A","设备A"),
    B("B","设备B"),
    C("C","设备C");
    /***/
    private String code;
    /***/
    private String desc;

    MachineInfoTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
