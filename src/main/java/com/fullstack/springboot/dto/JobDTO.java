package com.fullstack.springboot.dto;

import com.fullstack.springboot.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
	
	private Long jobNo;
	
	private String jobTitle;
	
	public JobDTO(Job job) {
		this.jobNo = job.getJobNo();
		this.jobTitle = job.getJobTitle();
	}

}
