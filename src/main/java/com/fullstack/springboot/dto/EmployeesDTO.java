package com.fullstack.springboot.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesDTO {

	private long empNo;
	private String firstName;
	private String lastName;
	private LocalDateTime hireDate;
	private String mailAddress;
	private long salary;
	private LocalDateTime birthday;
	private String address;
	private String phoneNum;
	private String gender;
	private String citizenId;
		
	public EmployeesDTO(long empNo, String firstName, String lastName, String mailAddress, String phoneNum) {
		this.empNo = empNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailAddress = mailAddress;
		this.phoneNum = phoneNum;
	}
}	
	
	