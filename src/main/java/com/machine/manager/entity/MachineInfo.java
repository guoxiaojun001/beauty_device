package com.machine.manager.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * machine_info
 * @author 
 */
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
    private Date createTime;

    @Column(name = "cooperation_mode")
    private String cooperationMode;


    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "MachineInfo{" +
                "id=" + id +
                ", machineType='" + machineType + '\'' +
                ", machineFunction='" + machineFunction + '\'' +
                ", machineParam='" + machineParam + '\'' +
                ", machineAttribute='" + machineAttribute + '\'' +
                ", userId=" + userId +
                ", usedDuration=" + usedDuration +
                ", machineBrand='" + machineBrand + '\'' +
                ", createTime='" + createTime + '\'' +
                ", cooperationMode='" + cooperationMode + '\'' +
                ", machineStatus='" + machineStatus + '\'' +
                ", machineWorkTimeOnce=" + machineWorkTimeOnce +
                ", machineProviceId='" + machineProviceId + '\'' +
                ", machineProvice='" + machineProvice + '\'' +
                ", machineCityId='" + machineCityId + '\'' +
                ", machineCity='" + machineCity + '\'' +
                '}';
    }
}