package com.machine.manager.dao;

import com.machine.manager.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByUserName(String name, String password);

    List<UserInfo> selectByName(String name);

    List<UserInfo> selectByPhone(String telephone);

    List<UserInfo> selectAll();

    List<UserInfo> selectByUserInfo(UserInfo userInfo);
}