package com.fullstack.springboot.service.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Booking;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.RoomList;

public interface BookingService {

	public Long addBooking(BookingDTO bookingDTO);
	
	public List<BookingDTO> getBookingListAtDate(String bookDate);

	public List<BookingDTO> getBookingListAtDate(String bookDate, Long roomNo);
	
	public PageResponseDTO<BookingDTO> getCRBookingList(String bookDate, PageRequestDTO pageRequestDTO);
	
	public PageResponseDTO<BookingDTO> getWRBookingList(PageRequestDTO pageRequestDTO);
	
	public BookingDTO getOne(Long bookNo);
	
	public void modify(Long bookNo, BookingDTO bookingDTO);
	
	public void remove(Long bookNo);

	default Booking dtoToEntity(BookingDTO bookingDTO) {
		Employees employees = Employees.builder()
				.empNo(bookingDTO.getEmpNo()).build();
		
		RoomList roomList = RoomList.builder()
				.roomNo(bookingDTO.getRoomNo()).build();
		
		Booking booking = Booking.builder()
				.bookDate(LocalDate.parse(bookingDTO.getBookDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.start(LocalTime.parse(bookingDTO.getStart(), DateTimeFormatter.ofPattern("HH:mm")))
				.end(LocalTime.parse(bookingDTO.getEnd(), DateTimeFormatter.ofPattern("HH:mm")))
				.roomList(roomList)
				.employees(employees)
				.build();
		
		return booking;
	}

	default BookingDTO entityToDto(Booking booking) {
		
		BookingDTO bookingDTO = BookingDTO.builder()
				.bookNo(booking.getBookNo())
				.bookDate(booking.getBookDate().toString())
				.start(booking.getStart().toString())
				.end(booking.getEnd().toString())
				.roomNo(booking.getRoomList().getRoomNo())
				.empNo(booking.getEmployees().getEmpNo())
				.build();
		
		return bookingDTO;
	}
}
