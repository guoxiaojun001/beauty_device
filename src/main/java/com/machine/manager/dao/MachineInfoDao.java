package com.machine.manager.dao;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.machine.MachineRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MachineInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MachineInfo record);

    int insertSelective(MachineInfo record);

    MachineInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MachineInfo record);

    int updateByPrimaryKey(MachineInfo record);

    MachineInfo getMachineInfoByAdmin(MachineRequest request);

    MachineInfo getMachineInfoByNormal(MachineRequest request);

    List<MachineInfo> selectAllByAdmin();

    List<MachineInfo> selectAllByNormal(Integer id);

    List<MachineInfo> selectByType( String machine_type);


    public List<MachineInfo> selectByProv(MachineRequest request);

    public List<MachineInfo> selectByProvCity(MachineRequest request) ;

    public List<MachineInfo> selectByNameProvCity(MachineRequest request) ;

    public List<MachineInfo> selectByNameProv(MachineRequest request) ;


}