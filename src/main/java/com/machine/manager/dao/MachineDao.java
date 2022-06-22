package com.machine.manager.dao;

import com.machine.manager.entity.StoreAndMachineEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备查询DAO层
 * 门店 --设备列表
 *
 * @date 2022/6/21
 */
@Mapper
public interface MachineDao {
    List<StoreAndMachineEntity> queryAllDeviceUderStores(@Param("storeId") int storeId);

    List<StoreAndMachineEntity> queryAllStoreDevice( );

}