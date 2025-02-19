package com.fullstack.springboot.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = "reportFiles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rhNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Report report;
	
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees receiver;
	
	public void changeStatus(String status) {
		this.status = status;
	}
	
	public void changeReceiver(Long receiver) {
		this.receiver = Employees.builder().empNo(receiver).build();
	}
	
}
