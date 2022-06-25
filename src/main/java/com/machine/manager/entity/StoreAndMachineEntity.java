package com.machine.manager.entity;

import com.machine.manager.entity.store.MachineEntity;
import com.machine.manager.entity.store.StoresEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *  门店-》设备 关系实体类
 *
 * @date 2022/6/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreAndMachineEntity implements Serializable {

    private Integer id;

    private String code;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contactPerson;
    private String contactPhone;

    /**
     * 所属代理商
     */
    private Integer agentId;

    /**
     * 设备数量
     */
    private Integer deviceCount;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 门店名称
     */
    private String storeName;

    /**门店列表*/
    List<MachineEntity> machineEntityList;
}
