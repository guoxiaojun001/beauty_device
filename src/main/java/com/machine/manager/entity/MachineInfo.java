package com.machine.manager.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * machine_info
 * @author 
 */
@Data
public class MachineInfo implements Serializable {
    private Integer id;

    private String machineType;

    private String machineFunction;

    private String machineParam;

    private String machineAttribute;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}