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
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reportNo;
	
	private LocalDate deadLine;
	
	@CreationTimestamp
	private LocalDate reportingDate;
	
	private String reportStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees receiver;
	
	public void changeStatus(String status) {
		this.reportStatus = status;
	}
	
	@ElementCollection
	@Builder.Default
	private List<ReportFiles> reportFiles = new ArrayList<>();
	
	public void addFile(ReportFiles files) {
		files.setOrd(this.reportFiles.size());
		reportFiles.add(files);
	}
	
	public void addFileString(String fileName) {
		ReportFiles reportFiles = ReportFiles.builder()
				.fileName(fileName)
				.build();
		addFile(reportFiles);
	}
	
	public void clearList() {
		this.reportFiles.clear();
	}
}
