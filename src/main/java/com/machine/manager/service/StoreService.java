package com.machine.manager.service;

import com.machine.manager.entity.Store;

import java.util.List;

public interface StoreService {
    long countByExample( Store  example);

    int deleteByExample(Store example);

    int deleteByPrimaryKey(Integer id);

//    int insert(Store record);

    int insertSelective(Store record);

    List<Store> selectAll();

    List<Store> selectCurrentUser(String agentId);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}
