package com.machine.manager.dao;

import com.machine.manager.entity.MachineInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<MachineInfo> selectAllByStore(Integer id);


    List<MachineInfo> selectByUserId(Integer userId) ;

//    List<MachineInfo> queryMachineByParm(@Param("parms") String parms);

    List<MachineInfo> queryMachineByIDAndParm(@Param("id") Integer id,@Param("parms") String parms);
}