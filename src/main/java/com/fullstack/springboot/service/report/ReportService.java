package com.fullstack.springboot.service.report;

import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.report.ReportDTO;

@Transactional
public interface ReportService {

	PageResponseDTO<ReportDTO> getRecivedList(Long empNo, PageRequestDTO pageRequestDTO);
	
	PageResponseDTO<ReportDTO> getSentList(Long empNo, PageRequestDTO pageRequestDTO);
	
	Long register(Long empNo, ReportDTO reportDTO);
	
	void modify(ReportDTO reportDTO);
	
	ReportDTO getOne(Long reportNo);
}
