package com.machine.manager.entity.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 门店列表实体类
 *
 * @author guoxi_789@126.com
 * @date 2022/6/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoresEntity implements Serializable {
    private Integer id;

    private String code;

    private String address;

    private String contactPerson;
    private String contactPhone;

    /**
     * 代理商id
     */
    private Integer agentId;

    private String createTime;

    private String storeName;

    private Integer deviceCount;

    private Integer version;

    private static final long serialVersionUID = 1L;
}