package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query("select j from Job j where j.jobNo = :jobNo")
	Job getJobById(@Param("jobNo") Long jobNo);
	
	@Query("Select new com.fullstack.springboot.dto.JobDTO(jb) from Job jb")
	List<JobDTO> getJobList();

	@Query("Select new com.fullstack.springboot.dto.JobDTO(jb) from Job jb where jb.jobTitle = :jobTitle")
	JobDTO getJobWithTitle(@Param("jobTitle") String jobTitle);
}
