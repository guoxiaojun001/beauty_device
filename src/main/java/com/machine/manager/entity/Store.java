package com.machine.manager.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * store
 * @author
 */
@Entity
@Table(name = "t_stores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable {

    private static final long serialVersionUID = 13435L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 所属代理商
     */
    private Integer agentId;

    /**
     * 设备数量
     */
    private Integer deviceCount;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 设备数量
     */
    private Integer newDeviceCount;


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Store other = (Store) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getContactPerson() == null ? other.getContactPerson() == null : this.getContactPerson().equals(other.getContactPerson()))
            && (this.getAgentId() == null ? other.getAgentId() == null : this.getAgentId().equals(other.getAgentId()))
            && (this.getDeviceCount() == null ? other.getDeviceCount() == null : this.getDeviceCount().equals(other.getDeviceCount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getStoreName() == null ? other.getStoreName() == null : this.getStoreName().equals(other.getStoreName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

}