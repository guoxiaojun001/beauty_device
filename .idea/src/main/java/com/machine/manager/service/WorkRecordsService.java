package com.machine.manager.service;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.WorkRecords;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.WorkRecordsRequest;

import java.util.List;

/**
 * 设备操作service
 *
 * @author guoxiaojun8804@126.com
 * @date 2021/02/16
 */
public interface WorkRecordsService {

    int insertSelective(WorkRecords machineInfo);

    WorkRecords selectByPrimaryKey(WorkRecordsRequest id);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(WorkRecords record);

    List<WorkRecords> getMachineRecordsAll();

    List<WorkRecords> getMachineRecords( WorkRecordsRequest machineId);

    Integer sumRecordsById(Integer id);

    Integer sumRecordsAll();
}
