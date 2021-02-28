package com.machine.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author pingwazi
 * @description 用户信息实体
 */
@Data
@AllArgsConstructor
public class UserEntity {
    private String userName;
    private String password;
    private List<String> authorities;
}
 