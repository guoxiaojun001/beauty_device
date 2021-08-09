package com.machine.manager.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 前端接口使用
 */
public class WebData implements Serializable {


    private MachineInfo machineInfo;

    private UserInfo userInfo;

    public MachineInfo getMachineInfo() {
        return machineInfo;
    }

    public void setMachineInfo(MachineInfo machineInfo) {
        this.machineInfo = machineInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public WebData(MachineInfo machineInfo , UserInfo userInfo) {
        this.machineInfo = machineInfo;
        this.userInfo = userInfo;
    }


    public WebData() {
    }

    @Override
    public String toString() {
        return "WebData{" +
                "machineInfo=" + machineInfo +
                ", userInfo=" + userInfo +
                '}';
    }
}
