package com.fullstack.springboot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fullstack.springboot.entity.Employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeesDTO {

	private Long empNo;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate hireDate;
	
	private String mailAddress;
	
	private Long salary;
	
	private Long deptNo;
	
	private Long jobNo;
	
	private LocalDate birthday;
	
	private String address;
	
	private String phoneNum;
	
	private String gender;
	
	private String citizenId;
	
	public EmployeesDTO(Employees employees) {
		this.empNo = employees.getEmpNo();
		this.firstName = employees.getFirstName();
		this.lastName = employees.getLastName();
		this.hireDate = employees.getHireDate();
		this.mailAddress = employees.getMailAddress();
		this.salary = employees.getSalary();
		this.deptNo = employees.getDeptInfo().getDeptNo();
		this.jobNo = employees.getJob().getJobNo();
		this.birthday = employees.getBirthday();
		this.address = employees.getAddress();
		this.phoneNum = employees.getPhoneNum();
		this.gender = employees.getGender();
		this.citizenId = employees.getCitizenId();
	}
}