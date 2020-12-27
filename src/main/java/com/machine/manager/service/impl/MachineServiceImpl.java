package com.machine.manager.service.impl;

import com.machine.manager.constant.UserRoleEnum;
import com.machine.manager.dao.MachineInfoDao;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.service.MachineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public MachineInfo getMachineInfo(MachineRequest request) {
        if (UserRoleEnum.ADMIN.getCode().equals(request.getRole())) {
            return machineInfoDao.getMachineInfoByAdmin(request);
        }
        if (UserRoleEnum.NORMAL.getCode().equals(request.getRole())) {
            return machineInfoDao.getMachineInfoByNormal(request);
        }
        return null;
    }

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
    public int updateByPrimaryKeySelective(MachineInfo record) {
        return machineInfoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MachineInfo record) {
        return 0;
    }

    @Override
    public MachineInfo getMachineInfoByAdmin(MachineRequest request) {
        return null;
    }

    @Override
    public MachineInfo getMachineInfoByNormal(MachineRequest request) {
        return null;
    }
}
