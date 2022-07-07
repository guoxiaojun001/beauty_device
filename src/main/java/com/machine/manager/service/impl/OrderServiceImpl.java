package com.machine.manager.service.impl;

import com.machine.manager.dao.JwtUserDao;
import com.machine.manager.dao.OrderDao;
import com.machine.manager.entity.Order;
import com.machine.manager.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private JwtUserDao jwtUserDao;

    @Override
    public long countByExample(Order  example) {
        return orderDao.countByExample(example);
    }

    @Override
    public int deleteByExample(Order example) {
        return orderDao.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderDao.deleteByPrimaryKey(id);
    }


    @Override
    public int insertSelective(Order record) {
        return orderDao.insertSelective(record);
    }

    @Override
    public List<Order> selectByExample(Order example) {
        return orderDao.selectByExample(example);
    }

//    @Override
//    public Order selectByPrimaryKey(Integer id) {
//        return orderDao.selectByPrimaryKey(id);
//    }

    @Override
    public Order selectByOrderNo(String orderNo) {
        return orderDao.selectByOrderNo(orderNo);
    }


    public List<Order> selectAllByAgentId(Integer agentId){
        return orderDao.selectAllByAgentId(agentId);
    }


    @Override
    public List<Order> selectCurrentUser(Integer agentId, String parms,int pageIndex,int pageSize) {
        return orderDao.selectCurrentUser(agentId,parms,pageIndex,pageSize);
    }


    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderDao.updateByPrimaryKey(record);
    }
}
