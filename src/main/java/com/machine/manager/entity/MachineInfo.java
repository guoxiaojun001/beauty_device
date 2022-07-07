package com.machine.manager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * machine_info
 * @author 
 */
@Proxy(lazy = false)
@Entity
@Table(name = "machine_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "machine_type")
    private String machineType;

    @Column(name = "machine_function")
    private String machineFunction;

    @Column(name = "machine_param")
    private String machineParam;

    @Column(name = "machine_attribute")
    private String machineAttribute;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 使用时长
     */
    @Column(name = "used_duration")
    private Integer usedDuration;

    /**
     * 品牌
     */
    @Column(name = "machine_brand")
    private String machineBrand;


    /**
     * 设备状态
     */
    @Column(name = "machine_status")
    private String machineStatus;

    /**
     * 单词使用时长
     */
    @Column(name = "machine_work_time_once")
    private Integer machineWorkTimeOnce;

    @Column(name = "machine_provice_id")
    private String machineProviceId;

    @Column(name = "machine_provice")
    private String machineProvice;

    @Column(name = "machine_city_id")
    private String machineCityId;

    @Column(name = "machine_city")
    private String machineCity;


    @Column(name = "create_time")
    private String createTime;

    @Column(name = "cooperation_mode")
    private String cooperationMode;



    @Column(name = "online_status")
    private Integer onlineStatus;

    @Column(name = "lastlogin_time")
    private String lastloginTime;

    @Column(name = "lock_status")
    private Integer lockStatus;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "other_parm")
    private String otherParm;


    @Column(name = "device_sn")//用于标签 sn号
    private String deviceSn;

    @Column(name = "left_time")//剩余时间
    private Integer leftTime;

    private static final long serialVersionUID = 1L;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MachineInfo)) return false;
        MachineInfo info = (MachineInfo) o;
        return Objects.equals(id, info.id) && Objects.equals(userId, info.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}