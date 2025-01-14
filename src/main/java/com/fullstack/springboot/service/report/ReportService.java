package com.fullstack.springboot.service.report;

public interface ReportService {

	PageResponseDTO<ReportDTO> getRecivedList(Long empNo, PageRequestDTO pageRequestDTO);
	
	PageResponseDTO<ReportDTO> getSentList(Long empNo, PageRequestDTO pageRequestDTO);
	
	Long register(Long empNo, ReportDTO reportDTO);
	
	void modify(ReportDTO reportDTO);
	
	ReportDTO getOne(Long reportNo);
}
