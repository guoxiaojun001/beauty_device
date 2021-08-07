package com.machine.manager.service.impl;

import com.machine.manager.dao.WorkRecordsDao;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.WorkRecords;
import com.machine.manager.entity.machine.WorkRecordsRequest;
import com.machine.manager.service.WorkRecordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备逻辑
 *
 * @author guoxiaojun8804@126.com
 * @date 2021/02/16
 */
@Service
public class WorkRecordsServiceImpl implements WorkRecordsService {
    @Resource
    private WorkRecordsDao workRecordsDao;


    @Override
    public int insertSelective(WorkRecords records) {
        return workRecordsDao.insertSelective(records);
    }

    @Override
    public WorkRecords selectByPrimaryKey(WorkRecordsRequest id) {
        return workRecordsDao.selectByPrimaryKey(id.getMachineId());
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return workRecordsDao.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(WorkRecords record) {
        return workRecordsDao.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkRecords> getMachineRecordsAll() {
        return workRecordsDao.getMachineRecordsAll();
    }


    @Override
    public List<WorkRecords> getMachineRecordsByMachineId(Integer machineId) {
        return workRecordsDao.getMachineRecordsByMachineId(machineId);
    }

    @Override
    public Integer sumRecordsById(Integer id) {
        System.out.println("查询 设备id 时长:" +id );
        return workRecordsDao.sumRecordsById(id);
    }

    @Override
    public Integer sumRecordsAll() {
        return workRecordsDao.sumRecordsAll();
    }
}
