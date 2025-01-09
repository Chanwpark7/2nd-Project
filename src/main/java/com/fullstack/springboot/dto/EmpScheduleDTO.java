package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import com.fullstack.springboot.entity.Employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class EmpScheduleDTO {
	
	private long empSchNo;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String scheduleText;
	private long empNo;
	
	public EmpScheduleDTO(long empSchNo, LocalDateTime startDate, LocalDateTime endDate, String scheduleText, long empNo) {
        this.empSchNo = empSchNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scheduleText = scheduleText;
        this.empNo = empNo;
    }
	
	 public EmpScheduleDTO(Long empNo) {
	        this.empNo = empNo;
	  }
	
}
