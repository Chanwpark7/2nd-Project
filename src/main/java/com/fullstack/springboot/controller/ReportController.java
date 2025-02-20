package com.fullstack.springboot.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.ReportDTO;
import com.fullstack.springboot.service.EmployeesService;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;
import com.fullstack.springboot.service.report.ReportService;
import com.fullstack.springboot.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/report")
public class ReportController {

	private final ReportService reportService;
	
	private final FileUtil fileUtil;
	
	@GetMapping("/list/received/{empNo}")
	public PageResponseDTO<ReportDTO> receivedList(@PathVariable(name = "empNo") Long empNo, PageRequestDTO pageRequestDTO) {
		
		return reportService.getRecivedList(empNo, pageRequestDTO);
	}
	
	@GetMapping("/list/sent/{empNo}")
	public PageResponseDTO<ReportDTO> sentList(@PathVariable(name = "empNo") Long empNo, PageRequestDTO pageRequestDTO) {
		
		return reportService.getSentList(empNo, pageRequestDTO);
	}
	
	@PostMapping("/register/{empNo}")
	public Long checkIn(@PathVariable(name = "empNo") Long empNo, ReportDTO reportDTO) {
		List<MultipartFile> files = reportDTO.getFiles();

	    List<String> uploadFileNames = fileUtil.attachFiles(files);

	    reportDTO.setUploadFileNames(uploadFileNames);
	    
		return reportService.register(empNo,reportDTO);
	}
	
	@GetMapping("/read/{reportNo}")
	public ReportDTO getOne(@PathVariable(name = "reportNo") Long reportNo) {
		return reportService.getOne(reportNo);
	}
	
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> getMethodName(@PathVariable(name = "fileName") String fileName) {
		return fileUtil.getFile(fileName);
	}
	
	
	@PutMapping("/modify/{reportNo}")
	public String putMethodName(@PathVariable(name = "reportNo") Long reportNo, @RequestBody ReportDTO reportDTO) {
		return reportService.modify(reportDTO);
		
	}
	
}
