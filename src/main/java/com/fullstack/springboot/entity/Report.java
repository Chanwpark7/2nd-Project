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
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reportNo;
	
	private LocalDateTime deadLine;
	
	private LocalDateTime reportingDate;
	
	private String reportUUID;
	
	private String reportStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees receiver;
}
