package com.machine.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * machine_info
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineInfoEntity implements Serializable {

    private Integer id;

    private String machineType;

    private String machineFunction;

    private String machineParam;

    private String machineAttribute;

    private Integer userId;
    private String owner;

    /**
     * 使用时长
     */
    private Integer usedDuration;

    /**
     * 品牌
     */
    private String machineBrand;


    /**
     * 设备状态
     */
    private String machineStatus;

    /**
     * 单词使用时长
     */
    private Integer machineWorkTimeOnce;

    private String machineProviceId;

    private String machineProvice;

    private String machineCityId;

    private String machineCity;


    private String createTime;

    private String cooperationMode;

    private Integer onlineStatus;

    private String lastloginTime;

    private Integer lockStatus;

    private Integer storeId;

    private String belongsStore;

    private String otherParm;


    private String deviceSn;

    private Integer leftTime;



    private static final long serialVersionUID = 1L;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MachineInfoEntity)) return false;
        MachineInfoEntity info = (MachineInfoEntity) o;
        return Objects.equals(id, info.id) && Objects.equals(userId, info.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}