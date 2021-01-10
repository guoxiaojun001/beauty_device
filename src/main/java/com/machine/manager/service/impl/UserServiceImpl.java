package com.machine.manager.service.impl;

import com.machine.manager.dao.UserInfoDao;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
        //比如对密码进行 md5 加密
        String md5Pass = DigestUtils.md5DigestAsHex(record.getPassword().getBytes());
        record.setPassword(md5Pass);
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
    public UserInfo selectByUserName(UserInfo userInfo) {
        if (StringUtils.isNotBlank(userInfo.getName()) && StringUtils.isNotBlank(userInfo.getPassword())){
            return userInfoDao.selectByUserName(userInfo.getName(), DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes()));
        }
        return null;
    }

}
