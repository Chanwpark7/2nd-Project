package com.fullstack.springboot.service.dayoff;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.entity.DayOff;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.DayOffRepository;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class DayOffServiceImpl implements DayOffService {
	
	private final DayOffRepository dayOffRepository;
	private final AnnualleaveService annualleaveService;
	
	@Override
	public void addDayOff(DayOffDTO dayOffDTO) {
		dayOffRepository.save(dtoToEntity(dayOffDTO));
		
		annualleaveService.changeHours(dayOffDTO.getEmpNo(), dayOffDTO.getOffHours());
	}

	@Override
	public void removeDayOff(DayOffDTO dayOffDTO) {		
		
		DayOff dayOff = dayOffRepository.getOneDayOff(dayOffDTO.getEmpNo(), dayOffDTO.getDayOffDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		annualleaveService.changeHours(dayOff.getEmployees().getEmpNo(), dayOff.getOffHours()*-1L);
		
		dayOffRepository.delete(dayOff);
	}

	@Override
	public void modifyDayOff(DayOffDTO dayOffDTO) {
		
		DayOff origin = dayOffRepository.getOneDayOff(dayOffDTO.getEmpNo(),dayOffDTO.getDayOffDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		DayOffDTO dto = entityToDto(origin);
		
		if(dayOffDTO.getOffHours()!=null) {
			dto.changeOffHours(dayOffDTO.getOffHours());
			annualleaveService.changeHours(dayOffDTO.getEmpNo(), dayOffDTO.getOffHours()-origin.getOffHours());
		}
		
		if(dayOffDTO.getDayOffDate()!=null) {
			dto.changeOffDate(dayOffDTO.getDayOffDate());
		}
		
		DayOff dayOff = DayOff.builder()
				.dayOffNo(origin.getDayOffNo())
				.dayOffDate(dto.getDayOffDate())
				.offHours(dto.getOffHours())
				.employees(Employees.builder().empNo(dto.getEmpNo()).build())
				.build();
		
		dayOffRepository.save(dayOff);
	}

}
