package com.machine.manager.dao;

import com.machine.manager.entity.UserInfo;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JwtUserDao extends JpaRepository<UserInfo, Integer> {

}
