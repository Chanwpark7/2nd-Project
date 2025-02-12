package com.fullstack.springboot;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fullstack.springboot.entity.AnnualLeave;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.repository.AnnualleaveRepository;
import com.fullstack.springboot.repository.DeptInfoRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;

@Component
public class CommandLineRun implements CommandLineRunner{
	
	@Autowired
	JobRepository jobRepository;
	@Autowired
	DeptInfoRepository deptInfoRepository;
	@Autowired
	EmployeesRepository employeesRepository;
	@Autowired
	AnnualleaveRepository annualleaveRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		if(!jobRepository.findById(999L).isEmpty()) {
			return;
		}
		Job job = Job.builder()
				.jobTitle("admin")
				.build();
		
		jobRepository.save(job);
		
		DeptInfo deptInfo = DeptInfo.builder()
				.deptName("admin")
				.deptAddress("admin")
				.build();
		
		deptInfoRepository.save(deptInfo);
		
		Employees employees = Employees.builder()
				.firstName("관리자")
				.lastName("계정")
				.mailAddress("admin")
				.salary(0)
				.job(Job.builder().jobNo(job.getJobNo()).build())
				.deptInfo(DeptInfo.builder().deptNo(deptInfo.getDeptNo()).build())
				.birthday(LocalDate.of(2000, 1, 1))
				.address("admin")
				.phoneNum("01011111111")
				.gender("m")
				.citizenId("0000000000000")
				.password(passwordEncoder.encode("admin"))
				.build();
		
		employeesRepository.save(employees);
		
		AnnualLeave annualLeave = AnnualLeave.builder()
				.antecedent(0)
				.employees(Employees.builder().empNo(employees.getEmpNo()).build())
				.hours(0)
				.build();
		
		annualleaveRepository.save(annualLeave);
	}
}
