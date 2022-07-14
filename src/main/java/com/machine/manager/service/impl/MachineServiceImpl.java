package com.machine.manager.service.impl;

import com.machine.manager.dao.MachineInfoDao;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.MachineInfoEntity;
import com.machine.manager.entity.machine.MachintCount;
import com.machine.manager.service.MachineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备逻辑
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@Service
public class MachineServiceImpl implements MachineService {
    @Resource
    private MachineInfoDao machineInfoDao;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return machineInfoDao.deleteByPrimaryKey(id);
    }


    @Override
    public int insertSelective(MachineInfo record) {
        return machineInfoDao.insertSelective(record);
    }

    @Override
    public MachineInfo selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public MachineInfo selectDeviceId(String machineParam) {
        return machineInfoDao.selectDeviceId(machineParam);
    }

    @Override
    public int updateByPrimaryKeySelective(MachineInfo record) {
        return machineInfoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MachineInfo record) {
        return machineInfoDao.updateByPrimaryKeySelective(record);
    }


    @Override
    public List<MachintCount> selectByDevType() {
        return machineInfoDao.selectByDevType();
    }

    @Override
    public List<MachintCount> selectByDevLocation() {
        return machineInfoDao.selectByDevLocation();
    }



    @Override
    public List<MachineInfo> queryMachineCurrent(Integer userId) {
        return machineInfoDao.queryMachineCurrent(userId);
    }

    @Override
    public List<MachineInfo> selectAllByNormalWithParm(Integer id, String parms,int pageIndex,int pageSize) {
        return machineInfoDao.queryMachineByIDAndParm(id,parms,pageIndex,pageSize);
    }

    @Override
    public List<MachineInfoEntity> selectAllByNormalWithParm22(Integer id, String parms, int pageIndex, int pageSize) {
        return machineInfoDao.queryMachineByIDAndParm22(id,parms,pageIndex,pageSize);
    }


}
