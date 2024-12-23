package com.fullstack.springboot.service.annualleave;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.entity.AnnualLeave;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.AnnualleaveRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnnualleaveServiceImpl implements AnnualleaveService {

	private final AnnualleaveRepository annualleaveRepository;
	
	@Override
	public AnnualLeaveDTO getOne(AnnualLeaveDTO annualleaveDTO) {
		
		AnnualLeave result = annualleaveRepository.getOne(annualleaveDTO.getEmpNo());
		
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
}
