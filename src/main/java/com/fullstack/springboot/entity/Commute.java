package com.fullstack.springboot.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commNo;
	
	@CreationTimestamp
	private LocalDateTime checkInTime;
	
	private LocalDateTime checkOutTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
	
	public void changeCheckOut(LocalDateTime now) {
		this.checkOutTime = now;
	}
	
	public void changeCommNo(Long commNo) {
		this.commNo = commNo;
	}
}
