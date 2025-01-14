package com.fullstack.springboot.service.report;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;
	
	@Override
	public PageResponseDTO<ReportDTO> getRecivedList(Long empNo, PageRequestDTO pageRequestDTO) {

		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage()-1,
				pageRequestDTO.getSize(),
				Sort.by("reportNo").descending()
			);
		
		Employees employees = Employees.builder()
				.empNo(empNo)
				.build();
		
		Page<Object[]> result = reportRepository.selectReceivedList(employees, pageable);
		
		List<ReportDTO> dtoList = result.get().map(arr -> {
			
			Report report = (Report) arr[0];
			ReportFiles reportFiles = (ReportFiles)arr[1];
			
			ReportDTO reportDTO = ReportDTO.builder()
					.reportNo(report.getReportNo())
					.deadLine(report.getDeadLine())
					.reportingDate(report.getReportingDate())
					.reportStatus(report.getReportStatus())
					.sender(report.getSender().getEmpNo())
					.receiver(report.getReceiver().getEmpNo())
					.build();
			
			String imageStr = reportFiles.getFileName();
			reportDTO.setUploadFileNames(List.of(imageStr));
			
			return reportDTO;
		}).collect(Collectors.toList());
		
		Long totalCount = result.getTotalElements();
		
		return PageResponseDTO.<ReportDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
	
	@Override
	public PageResponseDTO<ReportDTO> getSentList(Long empNo, PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage()-1,
				pageRequestDTO.getSize(),
				Sort.by("reportNo").descending()
			);
		
		Employees employees = Employees.builder()
				.empNo(empNo)
				.build();
		
		
		Page<Object[]> result = reportRepository.selectSentList(employees, pageable);
		
		List<ReportDTO> dtoList = result.get().map(arr -> {
			
			Report report = (Report) arr[0];
			ReportFiles reportFiles = (ReportFiles)arr[1];
			
			ReportDTO reportDTO = ReportDTO.builder()
					.reportNo(report.getReportNo())
					.deadLine(report.getDeadLine())
					.reportingDate(report.getReportingDate())
					.reportStatus(report.getReportStatus())
					.sender(report.getSender().getEmpNo())
					.receiver(report.getReceiver().getEmpNo())
					.build();
			
			String imageStr = reportFiles.getFileName();
			reportDTO.setUploadFileNames(List.of(imageStr));
			
			return reportDTO;
		}).collect(Collectors.toList());
		
		Long totalCount = result.getTotalElements();
		
		return PageResponseDTO.<ReportDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
	
	@Override
	public Long register(Long empNo, ReportDTO reportDTO) {

		reportDTO.setSender(empNo);
		
		Report report = Report.builder()
				.deadLine(reportDTO.getDeadLine())
				.reportStatus(reportDTO.getReportStatus())
				.sender(Employees.builder().empNo(reportDTO.getSender()).build())
				.receiver(Employees.builder().empNo(reportDTO.getReceiver()).build())
				.build();
		
		//업로드 처리 끝난 파일들의 이름 리스트
		List<String> uploadFileNames = reportDTO.getUploadFileNames();
		log.error(uploadFileNames);
		if(uploadFileNames != null) {
			uploadFileNames.stream().forEach(uploadName -> {
				report.addFileString(uploadName);
			});
		}
		
		Report result = reportRepository.save(report);
		
		return 1L;
	}
	
	@Override
	public ReportDTO getOne(Long reportNo) {

		return entityToDTO(reportRepository.findById(reportNo).get());
	}
	
	@Override
	public void modify(ReportDTO reportDTO) {
		Optional<Report> result = reportRepository.findById(reportDTO.getReportNo());
		
		Report report = result.orElseThrow();
		
		report.changeReceiver(reportDTO.getReceiver());
		report.changeSender(reportDTO.getSender());
		report.changeStatus(reportDTO.getReportStatus());
		
		reportRepository.save(report);
	}
	
//	private Report dtoToEntity(ReportDTO reportDTO) {
//		Report report = Report.builder()
//				.reportNo(reportDTO.getReportNo())
//				.deadLine(reportDTO.getDeadLine())
//				.reportingDate(reportDTO.getReportingDate())
//				.reportStatus(reportDTO.getReportStatus())
//				.sender(Employees.builder().empNo(reportDTO.getSender()).build())
//				.receiver(Employees.builder().empNo(reportDTO.getReceiver()).build())
//				.build();
//		
//		//업로드 처리 끝난 파일들의 이름 리스트
//		List<String> uploadFileNames = reportDTO.getUploadFileNames();
//		
//		if(uploadFileNames == null) {
//			return report;
//		}
//		
//		uploadFileNames.stream().forEach(uploadName -> {
//			report.addFileString(uploadName);
//		});
//		
//		return report;
//	}
	
	private ReportDTO entityToDTO(Report report) {
		ReportDTO reportDTO = ReportDTO.builder()
				.reportNo(report.getReportNo())
				.deadLine(report.getDeadLine())
				.reportingDate(report.getReportingDate())
				.reportStatus(report.getReportStatus())
				.sender(report.getSender().getEmpNo())
				.receiver(report.getReceiver().getEmpNo())
				.build();
		
		List<ReportFiles> reportFiles = report.getReportFiles();
		
		if(reportFiles == null || reportFiles.size()==0) {
			return reportDTO;
		}
		
		List<String> fileNameList = reportFiles.stream().map(reportFile->reportFile.getFileName()).toList();
		
		reportDTO.setUploadFileNames(fileNameList);
		
		return reportDTO;
	}
}
