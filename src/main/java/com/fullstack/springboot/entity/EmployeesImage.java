package com.fullstack.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeesImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empImgNo;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Employees employees;
	
	private String url;

	private String uuid;
}

