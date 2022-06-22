package com.machine.manager.dao;

import java.util.List;

import com.machine.manager.entity.Store;
import com.machine.manager.entity.StoreData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreDao {
//    long countByExample(Store example);


    int deleteByPrimaryKey(Integer id);


    int insertSelective(Store record);


    List<Store> selectAll();

    List<Store> selectCurrentUser(Integer agentId);

    List<StoreData> getAllStoreAndDevice();

    Store selectByPrimaryKey(Integer id);

    Store selectByStoreName(String storeName);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    /**
    * 功能描述 查询门店信息以及门店下设备数量
    * @author guoxi_789@126.com
    * @date 2022/6/22
    * @return 门店信息列表
    */
    List<Store>  selectStoreInfoAndDeviceCount();
}