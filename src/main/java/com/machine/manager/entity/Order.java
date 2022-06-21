package com.machine.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * orders
 * @author
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单内容
     */
    private String orderContent;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 付款状态
     */
    private Integer payStatus;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单时间
     */
    private String createTime;

    /**
     * 操作时间
     */
    private Integer operationTime;


    private String machineParam;//设备id

    private String store_id;//门店id

    private static final long serialVersionUID = 1L;


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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNo);
    }

}