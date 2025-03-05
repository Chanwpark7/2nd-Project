package com.fullstack.springboot.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.entity.CompanyAuth;
import com.fullstack.springboot.entity.DeptInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeesWithDeptAndJobDTO {

	private Long empNo;
	
	private String firstName;
	
	private String lastName;
	
  
	//private LocalDateTime hireDate;
	private LocalDate hireDate;
	
	private String mailAddress;
	
	private Long salary;
	
	private Long deptNo;
	
	private Long jobNo;
	
	//private LocalDateTime birthday;
	private LocalDate birthday;
	
	private String address;
	
	private String phoneNum;
	
	private String gender;
	
	private String citizenId;
	
	private String password;
	
	private String jobTitle;
	
	private String deptName;
	
	
	public EmployeesWithDeptAndJobDTO(Employees employees, Job job, DeptInfo deptInfo) {
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
		this.jobTitle = job.getJobTitle();
		this.deptName = deptInfo.getDeptName();
	}
}