package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import com.fullstack.springboot.entity.DayOff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOffDTO {

	private Long dayOffNo;
	
	private Long offHours;
	
	private LocalDateTime dayOffDate;
	
	private Long empNo;
	
	public DayOffDTO(DayOff dayOff) {
		this.dayOffNo = dayOff.getDayOffNo();
		this.offHours = dayOff.getOffHours();
		this.dayOffDate = dayOff.getDayOffDate();
		this.empNo = dayOff.getEmployees().getEmpNo();
	}
	
	public void changeOffHours(Long hours) {
		this.offHours = hours;
	}
	
	public void changeOffDate(LocalDateTime date) {
		this.dayOffDate = date;
	}
}
