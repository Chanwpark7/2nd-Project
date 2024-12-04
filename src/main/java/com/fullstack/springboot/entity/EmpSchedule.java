package com.fullstack.springboot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmpSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empSchNo;
	
	private LocalDateTime scheduleDate;
	
	private String scheduleText;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
}
