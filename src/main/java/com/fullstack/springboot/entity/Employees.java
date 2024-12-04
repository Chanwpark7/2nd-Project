package com.fullstack.springboot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Employees {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empNo;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDateTime hireDate;
	
	private String mailAddress;
	
	private String salary;
	
	@OneToOne
	private Job job;
	
}
