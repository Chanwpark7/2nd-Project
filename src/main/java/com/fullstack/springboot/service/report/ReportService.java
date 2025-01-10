package com.fullstack.springboot.service.report;

import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.report.ReportDTO;

@Transactional
public interface ReportService {

	PageResponseDTO<ReportDTO> getList(Long empNo, PageRequestDTO pageRequestDTO);
	
	Long register(ReportDTO reportDTO);
}
