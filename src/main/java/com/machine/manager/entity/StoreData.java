package com.machine.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gxj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreData implements Serializable  {

	private Integer id;

	private String code;
	private String address;
	private String contactPerson;

	private Integer agentId;

	private Integer deviceCount;

	private String createTime;

	private Integer version;

	private String storeName;

	private List<MachineInfo> machineInfoList;


}
