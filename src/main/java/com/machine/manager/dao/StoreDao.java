package com.machine.manager.dao;

import java.util.List;

import com.machine.manager.entity.Store;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreDao {
//    long countByExample(Store example);


    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);


    List<Store> selectAll();

    List<Store> selectCurrentUser(String agentId);

    Store selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}