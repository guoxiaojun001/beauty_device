package com.machine.manager.dao;

import java.util.List;

import com.machine.manager.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    long countByExample(Order example);

    int deleteByExample(Order example);

    int deleteByPrimaryKey(Integer id);


    int insertSelective(Order record);

    List<Order> selectByExample(Order example);


    Order selectByOrderNo(@Param("parms") String parms);

//    List<Order> selectAll();

    List<Order> selectCurrentUser(@Param("agentId") Integer agentId, @Param("parms") String parms);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}