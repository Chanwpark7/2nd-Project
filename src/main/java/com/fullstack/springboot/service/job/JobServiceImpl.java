package com.fullstack.springboot.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.repository.JobRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class JobServiceImpl implements JobService{
	
	@Autowired
	private final JobRepository jobRepository;
	
	@Override
	public JobDTO createOrModifyJob(JobDTO jobDTO) {

		jobRepository.save(dtoToEntity(jobDTO));
		
		return jobDTO;
	}

	@Override
	public void deleteJob(Long jobNo) {
		
		jobRepository.delete(jobRepository.findById(jobNo).get());
	}

	@Override
	public List<JobDTO> getJobList() {

		List<JobDTO> list = jobRepository.getJobList();
		
		return list;
	}

	
}
