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
	private LocalDateTime bookDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalDateTime start;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalDateTime end;
	
	private Long RoomNo;
	
	private Long empNo;
	
	public BookingDTO(Booking bk) {
		this.bookNo = bk.getBookNo();
		this.bookDate = bk.getBookDate();
		this.start = bk.getStart();
		this.end = bk.getEnd();
		this.RoomNo = bk.getRoomList().getRoomNo();
		this.empNo = bk.getEmployees().getEmpNo();
	}
}
