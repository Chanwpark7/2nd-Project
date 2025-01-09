package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.job.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/job")
public class JobController {

	private final JobService jobService;
	
	@GetMapping("/list")
	public List<JobDTO> list() {
		
		return jobService.getJobList();
	}
	
	@GetMapping("/read/{jobNo}")
	public JobDTO getOne(@PathVariable(name = "jobNo") Long jobNo) {
		return jobService.getOne(jobNo);
	}
	
	@GetMapping("/list/{jobNo}")
	public PageResponseDTO<EmployeesDTO> getEmpList(PageRequestDTO pageRequestDTO, @PathVariable(name = "jobNo") Long jobNo) {
		
		return jobService.getEmployeesListPageByJobNo(pageRequestDTO, jobNo);
	}
	
	@PutMapping("/{jobNo}")
	public String modify(@PathVariable(name = "jobNo") Long jobNo, @RequestBody JobDTO jobDTO) {
		
		jobService.createOrModifyJob(jobDTO);
		return "success";
	}
	
	@DeleteMapping("/{jobNo}")
	public void delete(@PathVariable(name = "jobNo") Long jobNo) {
		jobService.deleteJob(jobNo);
	}
	
	@PostMapping("/add")
	public void postMethodName(@RequestBody JobDTO jobDTO) {
		jobService.createOrModifyJob(jobDTO);
	}
	
}
