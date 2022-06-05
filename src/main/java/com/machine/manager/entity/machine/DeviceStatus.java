package com.machine.manager.entity.machine;

import lombok.Data;

@Data
public class DeviceStatus {

    //在线状态
    private int onLineStatus;
    //工作状态
    private int workStatus;

    //最后登录时间
    private String lastloginTime;

    //锁定
    private int lockStatus;

    @Override
    public String toString() {
        return "DeviceStatus{" +
                "onLineStatus=" + onLineStatus +
                ", workStatus=" + workStatus +
                ", lastloginTime='" + lastloginTime + '\'' +
                ", lockStatus=" + lockStatus +
                '}';
    }
}
