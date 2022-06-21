package com.machine.manager.entity;

import com.machine.manager.entity.store.StoresEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 代理商门店关系实体类
 *
 * @author guoxi_789@126.com
 * @date 2022/6/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentAndStoreEntity implements Serializable {

    private Integer id;

    private String name;

    private String email;

    private String telephone;

    private String address;

    private String password;

    private String roleId;

    private String userType;

    private String companyName;

    /**门店列表*/
    List<StoresEntity> storesEntities;
}
