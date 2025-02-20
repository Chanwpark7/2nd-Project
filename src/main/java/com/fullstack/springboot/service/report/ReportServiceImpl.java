package com.fullstack.springboot.service.report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.ReportDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Report;
import com.fullstack.springboot.entity.ReportFiles;
import com.fullstack.springboot.entity.ReportHistory;
import com.fullstack.springboot.repository.ReportHistoryRepository;
import com.fullstack.springboot.repository.ReportRepository;
import com.fullstack.springboot.service.dayoff.DayOffService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;
	private final ReportHistoryRepository reportHistoryRepository;
	private final DayOffService dayOffService;
	
	
	@Override
	public PageResponseDTO<ReportDTO> getRecivedList(Long empNo, PageRequestDTO pageRequestDTO) {

		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage()-1,
				pageRequestDTO.getSize(),
				Sort.by("deadLine").ascending()
			);
		
		Employees employees = Employees.builder()
				.empNo(empNo)
				.build();
		
		Page<Object[]> result = reportRepository.selectReceivedList(employees, pageable);
		
		List<ReportDTO> dtoList = result.get().map(arr -> {
			
			Report report = (Report) arr[0];
			ReportHistory reportHistory = (ReportHistory)arr[1];
			ReportFiles reportFiles = (ReportFiles)arr[2];

			List<Long> receivers = new ArrayList<Long>();
			receivers.add(reportHistory.getReceiver().getEmpNo());
			
			ReportDTO reportDTO = ReportDTO.builder()
					.reportNo(report.getReportNo())
					.deadLine(report.getDeadLine())
					.title(report.getTitle())
					.contents(report.getContents())
					.reportingDate(report.getReportingDate())
					.reportStatus(report.getReportStatus())
					.sender(report.getSender().getEmpNo())
					.receivers(receivers)
					.isDayOff(report.getIsDayOff())
					.build();

			if(reportFiles == null) {
				return reportDTO;
			}
			
			
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
			ReportHistory reportHistory = (ReportHistory)arr[1];
			ReportFiles reportFiles = (ReportFiles)arr[2];

			List<Long> receivers = new ArrayList<Long>();
			receivers.add(reportHistory.getReceiver().getEmpNo());
			
			ReportDTO reportDTO = ReportDTO.builder()
					.reportNo(report.getReportNo())
					.deadLine(report.getDeadLine())
					.title(report.getTitle())
					.contents(report.getContents())
					.reportingDate(report.getReportingDate())
					.reportStatus(report.getReportStatus())
					.sender(report.getSender().getEmpNo())
					.receivers(receivers)
					.isDayOff(report.getIsDayOff())
					.build();
			
			if(reportFiles!=null) {
				String imageStr = reportFiles.getFileName();
				reportDTO.setUploadFileNames(List.of(imageStr));
			}
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
				.title(reportDTO.getTitle())
				.contents(reportDTO.getContents())
				.reportStatus(reportDTO.getReportStatus())
				.sender(Employees.builder().empNo(reportDTO.getSender()).build())
				.isDayOff(reportDTO.getIsDayOff())
				.build();
		
		//업로드 처리 끝난 파일들의 이름 리스트
		List<String> uploadFileNames = reportDTO.getUploadFileNames();
		if(uploadFileNames != null) {
			uploadFileNames.stream().forEach(uploadName -> {
				report.addFileString(uploadName);
			});
		}
		
		List<Long> receivers = reportDTO.getReceivers();
		if(receivers!=null) {
			receivers.stream().forEach(receiver->{
				ReportHistory reportHistory = ReportHistory.builder()
						.status("대기")
						.report(report)
						.receiver(Employees.builder().empNo(receiver).build())
						.build();
				
				reportHistoryRepository.save(reportHistory);
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
	public String modify(ReportDTO reportDTO) {
		Optional<Report> result = reportRepository.findById(reportDTO.getReportNo());
		
		Report report = result.orElseThrow();
		
		
		if(reportDTO.getReportStatus().equals("반려")) {

			report.changeStatus(reportDTO.getReportStatus());
			
			reportRepository.save(report);

			reportHistoryRepository.getRHList(report).forEach(receiver->{
				receiver.changeStatus(report.getReportStatus());
				
				reportHistoryRepository.save(receiver);
			});
			
			return "반려되었습니다.";
		}else {
			
			List<ReportHistory> list = reportHistoryRepository.getRHList(report);
			
			if(list.size()==1) {
				report.changeStatus("완료");
				reportRepository.save(report);
				list.forEach(receiver->{
					receiver.changeStatus("승인");
					reportHistoryRepository.save(receiver);
				});
				
				if(report.getIsDayOff()) {
					
					DayOffDTO dayOffDTO = DayOffDTO.builder()
							.offHours(Long.parseLong(report.getContents()))
							.dayOffDate(LocalDate.parse(report.getTitle()))
							.empNo(report.getSender().getEmpNo())
							.build();
					
					dayOffService.addDayOff(dayOffDTO);
				};
			}else {
				ReportHistory reportHistory = reportHistoryRepository.findById(reportHistoryRepository.getOneRH(report)).get();
				
				reportHistory.changeStatus("승인");
				reportHistoryRepository.save(reportHistory);
			}
			
			return "승인되었습니다.";
		}
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

		List<Long> receivers = new ArrayList<Long>();
		if(report.getReportStatus().equals("진행중")) {
			receivers.add(reportHistoryRepository.findById(reportHistoryRepository.getOneRH(report)).get().getReceiver().getEmpNo());
		}else {
			receivers.add(reportHistoryRepository.findById(reportHistoryRepository.getMaxRH(report)).get().getReceiver().getEmpNo());
		}
		
		
		ReportDTO reportDTO = ReportDTO.builder()
				.reportNo(report.getReportNo())
				.deadLine(report.getDeadLine())
				.title(report.getTitle())
				.contents(report.getContents())
				.reportingDate(report.getReportingDate())
				.reportStatus(report.getReportStatus())
				.sender(report.getSender().getEmpNo())
				.receivers(receivers)
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
