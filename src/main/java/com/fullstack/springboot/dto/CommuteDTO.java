package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullstack.springboot.entity.Commute;
import com.fullstack.springboot.entity.Employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommuteDTO {

	private Long commNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime checkInTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime checkOutTime;
	
	private Long empNo;
	
	public CommuteDTO(Commute comm) {
		this.commNo = comm.getCommNo();
		this.checkInTime = comm.getCheckInTime();
		this.checkOutTime = comm.getCheckOutTime();
		this.empNo = comm.getEmployees().getEmpNo();
	}
}
