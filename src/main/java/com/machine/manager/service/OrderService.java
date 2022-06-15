package com.machine.manager.service;

import com.machine.manager.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    long countByExample(Order example);

    int deleteByExample(Order example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(Order example);

    Order selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
