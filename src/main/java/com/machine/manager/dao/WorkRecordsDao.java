package com.machine.manager.dao;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.WorkRecords;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.WorkRecordsRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkRecordsDao {
    int deleteByPrimaryKey(Integer id);

    int deleteByDeviceId(Integer id);

    int insert(WorkRecords record);

    int insertSelective(WorkRecords record);

    WorkRecords selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(WorkRecords record);

    List<WorkRecords> getMachineRecordsByMachineId(Integer machineId);

    List<WorkRecords> getMachineRecordsAll();


    Integer sumRecordsById(Integer machineId);

    Integer sumRecordsAll();

}