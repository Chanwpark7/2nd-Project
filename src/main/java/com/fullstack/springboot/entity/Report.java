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
	
	public void changeStatus(String status) {
		this.reportStatus = status;
	}
	
	public void changeSender(Long sender) {
		this.sender = Employees.builder().empNo(sender).build();
	}
	
	public void changeReceiver(Long receiver) {
		this.receiver = Employees.builder().empNo(receiver).build();
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
