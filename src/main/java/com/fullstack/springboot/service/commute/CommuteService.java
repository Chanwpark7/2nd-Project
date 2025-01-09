package com.fullstack.springboot.service.commute;

import org.springframework.data.domain.Page;

import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.entity.Commute;
import com.fullstack.springboot.entity.Employees;

public interface CommuteService {

	public CommuteDTO addCommute(CommuteDTO commuteDTO);
	
	public void checkOut(Long empNo);
	
	public Page<CommuteDTO> getListCommute(Long empNo, PageRequestDTO pageRequestDTO);
	
	default Commute dtoToEntity(CommuteDTO commuteDTO) {
		Employees employees = Employees.builder()
				.empNo(commuteDTO.getEmpNo()).build();
		
		Commute commute = Commute.builder()
				.checkInTime(commuteDTO.getCheckInTime())
				.checkOutTime(commuteDTO.getCheckOutTime())
				.employees(employees)
				.build();
		
		return commute;
	}
	
	default CommuteDTO entityToDto(Commute commute) {
		CommuteDTO commuteDTO = CommuteDTO.builder()
				.commNo(commute.getCommNo())
				.checkInTime(commute.getCheckInTime())
				.checkOutTime(commute.getCheckOutTime())
				.empNo(commute.getEmployees().getEmpNo())
				.build();
		
		return commuteDTO;
	}
}
