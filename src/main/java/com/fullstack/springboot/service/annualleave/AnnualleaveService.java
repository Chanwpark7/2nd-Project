package com.fullstack.springboot.service.annualleave;

import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.AnnualLeave;
import com.fullstack.springboot.entity.Employees;

public interface AnnualleaveService {

	public AnnualLeaveDTO getOne(AnnualLeaveDTO annualleaveDTO);
	
	public void setAnnualleave(Long empNo);
	
	public void deleteAnnualleave(Long empNo);
	
	public void changeHours(Long empNo, Long hours);
	
	public void modifyAnnualleave(AnnualLeaveDTO annualleaveDTO, Long hours);
	
	
	default AnnualLeave dtoToEntity(AnnualLeaveDTO annualLeaveDTO) {
		AnnualLeave annualLeave = AnnualLeave.builder()
				.annualId(annualLeaveDTO.getAnnualId())
				.antecedent(annualLeaveDTO.getAntecedent())
				.employees(Employees.builder().empNo(annualLeaveDTO.getEmpNo()).build())
				.hours(annualLeaveDTO.getHours())
				.build();
		
		return annualLeave;
	}
	
	default AnnualLeaveDTO entityToDto(AnnualLeave annualLeave) {
		AnnualLeaveDTO annualLeaveDTO = AnnualLeaveDTO.builder()
				.annualId(annualLeave.getAnnualId())
				.antecedent(annualLeave.getAntecedent())
				.empNo(annualLeave.getEmployees().getEmpNo())
				.hours(annualLeave.getHours())
				.build();
		
		return annualLeaveDTO;
	}
}
