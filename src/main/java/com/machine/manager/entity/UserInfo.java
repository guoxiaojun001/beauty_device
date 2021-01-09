package com.machine.manager.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private String telephone;

    private String address;

    private String password;

    private String roleId;

    private String userType;

    private static final long serialVersionUID = 1L;
}