package com.machine.manager.service.impl;

import com.machine.manager.dao.MachineInfoDao;
import com.machine.manager.entity.MachineInfo;
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





//    @Override
//    public List<MachineInfo> queryMachineByParm(String parms) {
//        return machineInfoDao.queryMachineByParm(parms);
//    }

    @Override
    public List<MachineInfo> selectAllByNormalWithParm(Integer id, String parms) {
        return machineInfoDao.queryMachineByIDAndParm(id,parms);
    }

}
