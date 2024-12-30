package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.service.job.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
	
}
