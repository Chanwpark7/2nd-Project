package com.fullstack.springboot.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class JobServiceImpl implements JobService{
	
	@Autowired
	private final JobRepository jobRepository;
	
	@Autowired
	private final EmployeesRepository employeesRepository;
	
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

	@Override
	public JobDTO getOne(Long jobNo) {

		Job job = jobRepository.findById(jobNo).get();
		
		return entityToDto(job);
	}
	
	@Override
	public PageResponseDTO<EmployeesDTO> getEmployeesListPageByJobNo(PageRequestDTO pageRequestDTO, Long jobNo) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("empNo").ascending());
		
		Page<EmployeesDTO> page = employeesRepository.getEmployeesListByJobNo(pageable, jobNo);
		
		List<EmployeesDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<EmployeesDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
}
