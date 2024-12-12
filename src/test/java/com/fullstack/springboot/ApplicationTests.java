package com.fullstack.springboot;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.entity.SalaryChart;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;
import com.fullstack.springboot.repository.RoomListRepository;
import com.fullstack.springboot.repository.SalaryChartRepository;

import jakarta.persistence.Version;
import lombok.RequiredArgsConstructor;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private EmployeesRepository employeesRepository;

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private SalaryChartRepository salaryChartRepository;
	
	@Autowired
	private RoomListRepository roomListRepository;
	
	@Test
//	 void insertDummies() {
//	      Job job = Job.builder()
//	            .jobNo(100L)
//	            .jobTitle("DIRECTOR")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	            .jobNo(200L)
//	            .jobTitle("MANAGER")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	            .jobNo(300L)
//	            .jobTitle("SENIOR")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	            .jobNo(400L)
//	            .jobTitle("EMPLOYEE")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	      .jobNo(500L)
//	      .jobTitle("INTERN")
//	      .build();
//	      
//	      jobRepository.save(job);
//		
//		SalaryChart salaryChart = SalaryChart.builder()
//				.saleryNo(100L)
//				.job(Job.builder()
//						.jobNo(100L)
//						.build())
//				.minSalary(6500L)
//				.maxSalary(7500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(200L)
//				.job((Job)jobRepository.getJobById(200L))
//				.minSalary(5500L)
//				.maxSalary(6500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(300L)
//				.job((Job)jobRepository.getJobById(300L))
//				.minSalary(4500L)
//				.maxSalary(5500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(400L)
//				.job((Job)jobRepository.getJobById(400L))
//				.minSalary(3500L)
//				.maxSalary(4500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(500L)
//				.job((Job)jobRepository.getJobById(500L))
//				.minSalary(2500L)
//				.maxSalary(3500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//	}
	
	void insertDummies() {
		DeptInfo deptInfo = DeptInfo.builder().build();
	}

}
