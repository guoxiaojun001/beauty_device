package com.machine.manager.dao;

import com.machine.manager.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

//    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);


    Integer selectAll();

    List<UserInfo> selectByUserInfo(UserInfo userInfo);


//    List<UserInfo>  selectUserInfoAndStoreCount(@Param("id")  Integer id);



    List<UserInfo>  selectUserInfoByParmAndId(@Param("id")  Integer id,@Param("parms")  String parms,@Param("pageIndex") int pageIndex,@Param("pageSize")int pageSize);
}