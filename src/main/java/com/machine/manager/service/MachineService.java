package com.machine.manager.service;

import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.entity.machine.MachineRequest;
import com.machine.manager.entity.machine.MachineRequestAfter;

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

    /**
     * 功能描述
     *
     * @param request
     * @return MachineInfo
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
    MachineInfo getMachineInfoByAdmin(MachineRequestAfter request);

    /**
     * 功能描述
     *
     * @param request
     * @return MachineInfo
     * @author guoxi_789@126.com
     * @date 2020/12/14
     */
    MachineInfo getMachineInfoByNormal(MachineRequestAfter request);


    /**
     * 功能描述
     * 查询设备列表
     * @param
     * @return MachineInfo
     * @author guoxiaojun8804@126.com
     * @date 2021/01/24
     */
    List<MachineInfo> selectAllByAdmin();

    List<MachineInfo> selectAllByNormal( Integer id);

    //1按照省查询
    List<MachineInfo> selectByProv( MachineRequestAfter request);

    //2按照省市查询
    List<MachineInfo> selectByProvCity( MachineRequestAfter request);

    //3，按照名称
    List<MachineInfo> selectByBrand( MachineRequestAfter request);

    List<MachineInfo> selectByNameProvCity( MachineRequestAfter request);

    //4名称 省查询，
    List<MachineInfo> selectByNameProv ( MachineRequestAfter request);

    List<MachineInfo> selectCommon ( MachineRequestAfter request);

    List<MachineInfo> selectByUserId ( Integer userId);
}
