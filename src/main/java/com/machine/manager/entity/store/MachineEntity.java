package com.machine.manager.entity.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 门店列表实体类
 *
 * @author guoxi_789@126.com
 * @date 2022/6/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineEntity implements Serializable {
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

    private String otherParm;

    private static final long serialVersionUID = 154L;
}