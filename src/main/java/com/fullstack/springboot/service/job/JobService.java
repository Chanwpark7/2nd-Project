package com.fullstack.springboot.service.job;

import java.util.List;

import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.entity.Job;

public interface JobService {

	public JobDTO createOrModifyJob(JobDTO jobDTO);
	
	public void deleteJob(Long jobNo);
	
	public List<JobDTO> getJobList();
	
	default Job dtoToEntity(JobDTO jobDTO) {
		Job job = Job.builder()
				.jobNo(jobDTO.getJobNo())
				.jobTitle(jobDTO.getJobTitle())
				.build();
		
		return job;
	}
	
	default JobDTO entityToDto(Job job) {
		JobDTO jobDTO = JobDTO.builder()
				.jobNo(job.getJobNo())
				.jobTitle(job.getJobTitle())
				.build();
		
		return jobDTO;
	}
}
