package com.machine.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * machine_info
 * @author 
 */
@Entity
@Table(name = "work_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkRecords implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "machine_id")
    private Integer machineId;

    /**
     * 使用时长
     */
    @Column(name = "used_duration")
    private Integer usedDuration;

    private static final long serialVersionUID = 1L;

}