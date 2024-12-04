package com.fullstack.springboot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JobHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobHistoryNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	@OneToOne
	private Job job;

	@OneToOne
	private DeptInfo deptInfo;
}

