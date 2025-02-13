package com.fullstack.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
public class AnnualLeave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long annualId;
	
	@OneToOne
	private Employees employees;
	
	private int antecedent;
	
	private long hours;
	
}
