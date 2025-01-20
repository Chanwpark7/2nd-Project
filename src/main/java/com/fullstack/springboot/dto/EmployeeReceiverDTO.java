package com.fullstack.springboot.dto;

import com.fullstack.springboot.entity.Employees;

import lombok.Data;

@Data
public class EmployeeReceiverDTO {
	private Long empNo;
	private String email;
	private String firstName;
	private String lastName;
	private String DeptName;
	private String jobName;
	
	public EmployeeReceiverDTO(Employees emp) {
		this.empNo = emp.getEmpNo();
		this.email = emp.getMailAddress();
		this.firstName = emp.getFirstName();
		this.lastName = emp.getLastName();
		this.DeptName = emp.getDeptInfo().getDeptName();
		this.jobName = emp.getJob().getJobTitle();
	}
}
