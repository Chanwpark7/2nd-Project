package com.fullstack.springboot.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dayOffNo;
	
	private Long offHours;
	
	private LocalDateTime dayOffDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
}
