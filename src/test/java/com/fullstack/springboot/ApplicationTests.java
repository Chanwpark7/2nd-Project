package com.fullstack.springboot;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.entity.SalaryChart;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;
import com.fullstack.springboot.repository.RoomListRepository;
import com.fullstack.springboot.repository.SalaryChartRepository;

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
	
	@Transactional
	@Test
	void insertDummies() {
		//System.out.println(employeesRepository.findById(1L));
		System.out.println(jobRepository.getJobById(100L));
		
//		SalaryChart salaryChart = SalaryChart.builder()
//				.saleryNo(100L)
//				.job((Job)jobRepository.getJobById(100L))
//				.minSalary(6500)
//				.maxSalary(7500)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(200L)
//				.job((Job)jobRepository.getJobById(200L))
//				.minSalary(5500)
//				.maxSalary(6500)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(300L)
//				.job((Job)jobRepository.getJobById(300L))
//				.minSalary(4500)
//				.maxSalary(5500)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(400L)
//				.job((Job)jobRepository.getJobById(400L))
//				.minSalary(3500)
//				.maxSalary(4500)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(500L)
//				.job((Job)jobRepository.getJobById(500L))
//				.minSalary(2500)
//				.maxSalary(3500)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
	}
	
//	void contextLoads() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			Employees employees = Employees.builder()
//				.firstName("first"+i)
//				.lastName("last"+i)
//				.build();
//		
//		employeesRepository.save(employees);
//		});
//	}

}
