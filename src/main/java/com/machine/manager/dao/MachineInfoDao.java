package com.machine.manager.dao;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.MachineRequestAfter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MachineInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MachineInfo record);

    int insertSelective(MachineInfo record);

    MachineInfo selectByPrimaryKey(Integer id);

    MachineInfo selectDeviceId(String machineParam);

    int updateByPrimaryKeySelective(MachineInfo record);

    int updateByPrimaryKey(MachineInfo record);

    MachineInfo getMachineInfoByAdmin(MachineRequest request);

    MachineInfo getMachineInfoByNormal(MachineRequest request);

    List<MachineInfo> selectAllByAdmin();

    List<MachineInfo> selectAllByNormal(Integer id);


    List<MachineInfo> selectByBrand( MachineRequestAfter request);

    public List<MachineInfo> selectByProv(MachineRequestAfter request);

    public List<MachineInfo> selectByProvCity(MachineRequestAfter request) ;

    public List<MachineInfo> selectByNameProvCity(MachineRequestAfter request) ;

    public List<MachineInfo> selectByNameProv(MachineRequestAfter request) ;


    public List<MachineInfo> selectCommon(MachineRequestAfter request) ;


    public List<MachineInfo> selectByUserId(Integer userId) ;


}