package com.machine.manager.dao;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.MachineInfoEntity;
import com.machine.manager.entity.machine.MachintCount;
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

    public List<MachintCount> selectByDevType ();
    public List<MachintCount> selectByDevLocation ();

    List<MachineInfo> selectByUserId(Integer userId) ;

    List<MachineInfo> queryMachineCurrent(@Param("userId") Integer userId);

    List<MachineInfo> queryMachineByIDAndParm(@Param("id") Integer id,@Param("parms") String parms,@Param("pageIndex") int pageIndex,@Param("pageSize")int pageSize);
    List<MachineInfoEntity> queryMachineByIDAndParm22(@Param("id") Integer id, @Param("parms") String parms, @Param("pageIndex") int pageIndex, @Param("pageSize")int pageSize);
}