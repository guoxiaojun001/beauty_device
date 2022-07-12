package com.machine.manager.dao;

import com.machine.manager.entity.AgentAndStoreEntity;
import com.machine.manager.entity.store.StoresEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoresDao {
    int deleteByPrimaryKey(Integer id);

//    int insert(StoresEntity record);

    int insertSelective(StoresEntity record);

    StoresEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoresEntity record);

    int updateByPrimaryKey(StoresEntity record);

    /**
    * 功能描述 查询代理商下的门店
    * @author guoxi_789@126.com
    * @date 2022/6/20
    * @param  agentId 代理商id
    * @return 门店列表
    */
    List<AgentAndStoreEntity> queryStoresUnderAgent(@Param("agentId") int agentId);
    /**查询门店下的设备*/
    List<AgentAndStoreEntity> queryAllStoresAgent( );

//    List<AgentAndStoreEntity> queryAllStoresAndAgentList();
}