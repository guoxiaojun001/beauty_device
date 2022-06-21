package com.machine.manager.service;

import com.machine.manager.entity.Order;

import java.util.List;

public interface OrderService {
    long countByExample(Order example);

    int deleteByExample(Order example);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Order record);

    List<Order> selectByExample(Order example);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    List<Order> selectCurrentUser(Integer agentId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
