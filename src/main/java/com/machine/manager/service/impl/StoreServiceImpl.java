package com.machine.manager.service.impl;

import com.machine.manager.dao.JwtUserDao;
import com.machine.manager.dao.StoreDao;
import com.machine.manager.entity.Store;
import com.machine.manager.entity.StoreData;
import com.machine.manager.service.MachineService;
import com.machine.manager.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreDao storeDao;


    @Resource
    private JwtUserDao jwtUserDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return storeDao.deleteByPrimaryKey(id);
    }


    @Override
    public int insertSelective(Store record) {
        return storeDao.insertSelective(record);
    }

    @Override
    public List<Store> selectAll() {
        return storeDao.selectAll();
    }

    @Override
    public List<Store> selectCurrentUser(Integer agentId) {
        return storeDao.selectCurrentUser(agentId);
    }

    @Override
    public List<StoreData> getAllStoreAndDevice() {
        return storeDao.getAllStoreAndDevice();
    }


    @Override
    public Store selectByStoreName(String name) {
        return storeDao.selectByStoreName(name);
    }

    @Override
    public Store selectByPrimaryKey(Integer id) {
        return storeDao.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(Store record) {
        return storeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Store record) {
        return storeDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Store>  selectStoreInfoAndDeviceCount(Integer id){
        return storeDao.selectStoreInfoAndDeviceCount(id);
    }

    @Override
    public List<Store>  selectStoreInfoAndDeviceCountByStoreName(Integer id,String storeName,int pageIndex,int pageSize){
        return storeDao.selectStoreInfoAndDeviceCountByStoreName(id,storeName,pageIndex,pageSize);
    }
}
