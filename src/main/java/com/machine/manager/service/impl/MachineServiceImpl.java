package com.machine.manager.service.impl;

import com.machine.manager.constant.UserRoleEnum;
import com.machine.manager.dao.MachineInfoDao;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.MachineRequestAfter;
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

//    @Override
//    public MachineInfo getMachineInfo(MachineRequestAfter request) {
////        if (UserRoleEnum.ADMIN.getCode().equals(request.getRole())) {
////            return machineInfoDao.getMachineInfoByAdmin(request);
////        }
////        if (UserRoleEnum.NORMAL.getCode().equals(request.getRole())) {
////            return machineInfoDao.getMachineInfoByNormal(request);
////        }
//        return null;
//    }

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
    public MachineInfo getMachineInfoByAdmin(MachineRequestAfter request) {
        return null;
    }

    @Override
    public MachineInfo getMachineInfoByNormal(MachineRequestAfter request) {
        return null;
    }

    @Override
    public List<MachineInfo> selectAllByAdmin() {
        return machineInfoDao.selectAllByAdmin();
    }

    @Override
    public List<MachineInfo> selectAllByNormal(Integer id) {
        return machineInfoDao.selectAllByNormal(id);
    }

    @Override
    public List<MachineInfo> selectByType(MachineRequestAfter request) {
        return machineInfoDao.selectByType(request);
    }

    @Override
    public List<MachineInfo> selectByProv(MachineRequestAfter request) {
        return machineInfoDao.selectByProv(request);
    }

    @Override
    public List<MachineInfo> selectByProvCity(MachineRequestAfter request) {
        return machineInfoDao.selectByProvCity(request);
    }

    @Override
    public List<MachineInfo> selectByNameProvCity(MachineRequestAfter request) {
        return machineInfoDao.selectByNameProvCity(request);
    }

    @Override
    public List<MachineInfo> selectByNameProv(MachineRequestAfter request) {
        return machineInfoDao.selectByNameProv(request);
    }

    @Override
    public List<MachineInfo> selectCommon(MachineRequestAfter request) {
        return machineInfoDao.selectCommon(request);
    }
}
