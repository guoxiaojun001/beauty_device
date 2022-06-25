package com.machine.manager.dao;

import java.util.List;

import com.machine.manager.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
    long countByExample(Order example);

    int deleteByExample(Order example);

    int deleteByPrimaryKey(Integer id);


    int insertSelective(Order record);

    List<Order> selectByExample(Order example);

//    Order selectByPrimaryKey(Integer id);

    Order selectByOrderNo(String orderNo);

    List<Order> selectAll();

    List<Order> selectCurrentUser(Integer agentId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}