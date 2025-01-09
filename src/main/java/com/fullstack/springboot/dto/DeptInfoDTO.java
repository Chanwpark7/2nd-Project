package com.fullstack.springboot.dto;

import com.fullstack.springboot.entity.DeptInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptInfoDTO {

	private Long deptNo;

	private String deptName;
	
	private String deptAddress;
	
	private String phoneNo;
	
	public DeptInfoDTO(DeptInfo deptInfo) {
		this.deptNo = deptInfo.getDeptNo();
		this.deptName = deptInfo.getDeptName();
		this.deptAddress = deptInfo.getDeptAddress();
		this.phoneNo = deptInfo.getPhoneNo();
	}
}
