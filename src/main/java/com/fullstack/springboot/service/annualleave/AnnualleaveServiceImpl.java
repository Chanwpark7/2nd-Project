package com.fullstack.springboot.service.annualleave;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.AnnualLeave;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.AnnualleaveRepository;
import com.fullstack.springboot.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnnualleaveServiceImpl implements AnnualleaveService {

	private final AnnualleaveRepository annualleaveRepository;
	
	private final EmployeesRepository employeesRepository;
	
	@Override
	public AnnualLeaveDTO getOne(Long empNo) {
		
		AnnualLeave result = annualleaveRepository.getOne(empNo);
		
		return entityToDto(result);
	}

	@Override
	public void setAnnualleave(Long empNo) {
		AnnualLeave annualLeave = AnnualLeave.builder()
				.antecedent(0)
				.employees(Employees.builder().empNo(empNo).build())
				.hours(0)
				.build();
		
		annualleaveRepository.save(annualLeave);
	}

	@Override
	public void deleteAnnualleave(Long empNo) {
		annualleaveRepository.delete(annualleaveRepository.getOne(empNo));
	}

	@Override
	public void modifyAnnualleave(AnnualLeaveDTO annualleaveDTO, Long empNo) {
		AnnualLeave annualLeave = annualleaveRepository.getOne(empNo);
		
		AnnualLeaveDTO dto = entityToDto(annualLeave);
		if(annualleaveDTO.getAntecedent()!=null) {
			dto.changeAntecedent(annualleaveDTO.getAntecedent());
		}
		if(annualleaveDTO.getHours()!=null) {
			dto.changeHours(annualleaveDTO.getHours());
		}
		annualleaveRepository.save(dtoToEntity(dto));
	}

	@Override
	public void changeHours(Long empNo, Long hours) {
		
		AnnualLeave annualLeave = annualleaveRepository.getOne(empNo);
		AnnualLeaveDTO dto = entityToDto(annualLeave);
		
		dto.changeHoursByUsing(hours);
		
		annualleaveRepository.save(dtoToEntity(dto));
	}
	
	@Override
	public PageResponseDTO<AnnualLeaveDTO> getALList(Long empNo, PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("annualId").descending());
		
		Page<AnnualLeaveDTO> page = annualleaveRepository.getALList(empNo, pageable);
		
		List<AnnualLeaveDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<AnnualLeaveDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
}
