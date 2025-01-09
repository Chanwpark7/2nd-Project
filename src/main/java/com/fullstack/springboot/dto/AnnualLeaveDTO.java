package com.fullstack.springboot.dto;

import com.fullstack.springboot.entity.AnnualLeave;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnualLeaveDTO {

	private Long annualId;
	
	private Long empNo;
	
	private Integer antecedent;
	
	private Long hours;
	
	public AnnualLeaveDTO(AnnualLeave annualLeave) {
		this.annualId = annualLeave.getAnnualId();
		this.empNo = annualLeave.getEmployees().getEmpNo();
		this.antecedent = annualLeave.getAntecedent();
		this.hours = annualLeave.getHours();
	}
	
	public void changeHoursByUsing(Long hour) {
		this.hours = this.hours-hour;
	}
	
	public void changeAntecedent(int year) {
		
		this.antecedent = year;
	}

	public void changeHours(Long hour) {
		this.hours = hour;
	}
	
}
