package com.fullstack.springboot.service.job;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.data.domain.Page;

import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;

public interface JobService {

	public JobDTO createOrModifyJob(JobDTO jobDTO);
	
	public void deleteJob(Long jobNo);
	
	public List<JobDTO> getJobList();
	
	public JobDTO getOne(Long jobNo);
	
	public PageResponseDTO<EmployeesDTO> getEmployeesListPageByJobNo(PageRequestDTO pageRequestDTO, Long jobNo);
	
//	public String createExcelFile(JobDTO jobDTO);
	
//	public List<List<String>> dataFromExcel(String filePath) throws Exception;
	
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
