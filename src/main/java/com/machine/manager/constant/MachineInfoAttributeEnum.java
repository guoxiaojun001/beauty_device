package com.machine.manager.constant;


/**
 * 设备种类
 *
 * @author guoxi_789@126.com
 * @date 2020/12/13
 */

public enum MachineInfoAttributeEnum {
    /***/
    ATTRIBUTEA("ATTRIBUTEA","属性A"),
    ATTRIBUTEB("ATTRIBUTEB","属性B"),
    ATTRIBUTEC("ATTRIBUTEC","属性C");
    /***/
    private String code;
    /***/
    private String desc;

    MachineInfoAttributeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
