package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullstack.springboot.entity.Booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

	private Long bookNo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String bookDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private String start;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private String end;
	
	private Long roomNo;
	
	private Long empNo;
	
	public BookingDTO(Booking bk) {
		this.bookNo = bk.getBookNo();
		this.bookDate = bk.getBookDate().toString();
		this.start = bk.getStart().toString();
		this.end = bk.getEnd().toString();
		this.roomNo = bk.getRoomList().getRoomNo();
		this.empNo = bk.getEmployees().getEmpNo();
	}
}
