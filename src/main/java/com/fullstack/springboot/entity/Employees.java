package com.fullstack.springboot.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	
	//직원 엔티티
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empNo;
	
	private String firstName;
	
	private String lastName;
	
	@CreationTimestamp
	private LocalDateTime hireDate;
	
	private String mailAddress;
	
	private String salary;
	
	@OneToOne
	private DeptInfo deptInfo;
	
	@OneToOne
	private Job job;
	
}
