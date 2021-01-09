package com.machine.manager.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * machine_info
 * @author 
 */
@Data
public class MachineInfo implements Serializable {
    private Integer id;

    private String machineType;

    private String machineFunction;

    private String machineParam;

    private String machineAttribute;

    private Integer userId;

    /**
     * 使用时长
     */
    private Integer usedDuration;

    /**
     * 品牌
     */
    private String machineBrand;

    private String userName;

    /**
     * 设备状态
     */
    private String machineStatus;

    /**
     * 单词使用时长
     */
    private Date machineWorkTimeOnce;

    private static final long serialVersionUID = 1L;
}