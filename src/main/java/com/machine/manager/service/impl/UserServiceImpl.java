package com.machine.manager.service.impl;

import com.machine.manager.dao.UserInfoDao;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guoxi_789@126.com
 * @date 2020/12/14
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoDao.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public boolean selectByUserName(UserInfo userInfo) {
        boolean result = userInfoDao.selectByUserName(userInfo.getName(), userInfo.getPassword());
        return result;
    }

}
