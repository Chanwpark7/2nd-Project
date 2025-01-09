package com.fullstack.springboot.service.dayoff;

import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.AnnualLeave;
import com.fullstack.springboot.entity.DayOff;
import com.fullstack.springboot.entity.Employees;

public interface DayOffService {

	public void addDayOff(DayOffDTO dayOffDTO);
	
	//public void removeDayOff(DayOffDTO dayOffDTO);
	
	public void removeDayOff(Long dayOffNo);
	
	public void modifyDayOff(DayOffDTO dayOffDTO);
	
	public PageResponseDTO<DayOffDTO> getList(PageRequestDTO pageRequestDTO);
	
	public DayOffDTO getOne(Long dayOffNo);
	
	default DayOffDTO entityToDto(DayOff dayOff) {
		DayOffDTO dto = DayOffDTO.builder()
				.dayOffNo(dayOff.getDayOffNo())
				.dayOffDate(dayOff.getDayOffDate())
				.offHours(dayOff.getOffHours())
				.empNo(dayOff.getEmployees().getEmpNo())
				.build();
		
		return dto;
	}
	
	default DayOff dtoToEntity(DayOffDTO dayOffDTO) {
		DayOff dayOff = DayOff.builder()
				.dayOffDate(dayOffDTO.getDayOffDate())
				.offHours(dayOffDTO.getOffHours())
				.employees(Employees.builder().empNo(dayOffDTO.getEmpNo()).build())
				.build();
		
		return dayOff;
	}
}
