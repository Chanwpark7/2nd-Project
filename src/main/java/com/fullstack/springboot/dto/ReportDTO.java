package com.fullstack.springboot.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

	private Long reportNo;
	
	private LocalDate deadLine;

	private String title;
	
	private String contents;
	
	private LocalDate reportingDate;
	
	private String reportStatus;
	
	private Long sender;
	
	private List<Long> receivers;
	
	private Boolean isDayOff;	
	
	@Builder.Default
	private List<MultipartFile> files = new ArrayList<>();
	
	@Builder.Default
	private List<String> uploadFileNames = new ArrayList<>();
}
