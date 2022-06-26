package com.machine.manager.service;

import com.machine.manager.entity.MachineInfo;

import java.util.List;

/**
 * 设备操作service
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
public interface MachineService {
    /**
     * 功能描述
     *
     * @param request
     * @return MachineInfo
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
//    MachineInfo getMachineInfo(MachineRequestAfter request);

    /**
     * 功能描述
     *
     * @param id
     * @return int
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 功能描述
     *
     * @param machineInfo
     * @return int
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
    int insertSelective(MachineInfo machineInfo);


    /**
     * 功能描述
     *
     * @param id
     * @return MachineInfo
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
    MachineInfo selectByPrimaryKey(Integer id);

    MachineInfo selectDeviceId(String machineParam);

    /**
     * 功能描述
     *
     * @param record
     * @return int
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
    int updateByPrimaryKeySelective(MachineInfo record);

    /**
     * 功能描述
     *
     * @param record
     * @return int
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
    int updateByPrimaryKey(MachineInfo record);


//    public List<MachineInfo> selectAllByStore(Integer sid);
    List<MachineInfo> selectAllByNormalWithParm( Integer id,String parm);
}
