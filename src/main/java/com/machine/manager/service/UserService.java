package com.machine.manager.service;

import com.machine.manager.entity.UserInfo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 用户操作接口
 *
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    Integer selectAll();

    List<UserInfo> selectByUserInfo(UserInfo userInfo);

//    List<UserInfo>  selectUserInfoAndStoreCount(Integer id);

    List<UserInfo>  selectUserInfoByParmAndId(Integer id, String parms,int pageIndex,int pageSize);

    //jwt使用
    public UserInfo selectUserByUserName(String userName);
}
