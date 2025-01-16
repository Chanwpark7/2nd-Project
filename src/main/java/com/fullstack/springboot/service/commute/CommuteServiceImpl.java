package com.fullstack.springboot.service.commute;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Commute;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.CommuteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommuteServiceImpl implements CommuteService {

	private final CommuteRepository commuteRepository;
	
	@Override
	public Long addCommute(Long empNo) {
		CommuteDTO commuteDTO = commuteRepository.whenCheckingOut(empNo);
		
		if(commuteDTO == null) {
			if(commuteRepository.checkingExistency(empNo)==null) {
				commuteRepository.save(dtoToEntity(CommuteDTO.builder().empNo(empNo).build()));
				
				return empNo;
			}
		};
		
		return null;
	}
	
	@Override
	public void checkOut(Long empNo) {

		CommuteDTO commuteDTO = commuteRepository.whenCheckingOut(empNo);
		
		if(commuteDTO == null) {
			return;
		};
		
		System.out.println(commuteDTO);
		
		Commute commute = dtoToEntity(commuteDTO);
		
		commute.changeCheckOut(LocalTime.now());
		
		commute.changeCommNo(commuteDTO.getCommNo());
		
		commuteRepository.save(commute);
	}
	
	@Override
	public PageResponseDTO<CommuteDTO> getListCommute(Long empNo, PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("commNo").descending());
		
		Page<CommuteDTO> page = commuteRepository.getListComm(empNo, pageable);
		
		List<CommuteDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<CommuteDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
	
	@Override
	public void modifyCommute(Long empNo, CommuteDTO commuteDTO) {
		Employees employees = Employees.builder()
				.empNo(commuteDTO.getEmpNo()).build();
		
		Commute commute = Commute.builder()
				.commNo(commuteDTO.getCommNo())
				.checkDate(commuteDTO.getCheckDate())
				.checkInTime(commuteDTO.getCheckInTime())
				.checkOutTime(commuteDTO.getCheckOutTime())
				.employees(employees)
				.build();
		commuteRepository.save(commute);
	}
	
	@Override
	public CommuteDTO getOne(Long commNo) {
		return entityToDto(commuteRepository.findById(commNo).get());
	}

	@Override
	public CommuteDTO todayCheckTime(Long empNo) {
		log.warn("!!!!!!!!!!!!!!!!");
		return commuteRepository.todayCheckTime(empNo);
	}
}
