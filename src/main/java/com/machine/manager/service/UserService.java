package com.machine.manager.service;

import com.machine.manager.entity.UserInfo;

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

    UserInfo selectByUserName(UserInfo userInfo);
}
