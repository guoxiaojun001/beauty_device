package com.machine.manager.service;

import com.machine.manager.entity.Store;
import com.machine.manager.entity.StoreData;

import java.util.List;

public interface StoreService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Store record);

    List<Store> selectAll();

    List<Store> selectCurrentUser(Integer agentId);

    List<StoreData> getAllStoreAndDevice();

    Store selectByPrimaryKey(Integer id);

    Store selectByStoreName(String name);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    /**
    * 功能描述 查询门店信息及门店下设备数量
    * @author guoxi_789@126.com
    * @date 2022/6/22
    * @return 门店信息列表
    */
    List<Store>  selectStoreInfoAndDeviceCount();
}
