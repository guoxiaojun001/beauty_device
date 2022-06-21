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

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}
