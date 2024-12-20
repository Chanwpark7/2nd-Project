package com.fullstack.springboot.service.booking;

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
	
	public Page<BookingDTO> getCRBookingList(PageRequestDTO pageRequestDTO);
	
	public Page<BookingDTO> getWRBookingList(PageRequestDTO pageRequestDTO);
	
	public void remove(Long bookNo);

	default Booking dtoToEntity(BookingDTO bookingDTO) {
		Employees employees = Employees.builder()
				.empNo(bookingDTO.getEmpNo()).build();
		
		RoomList roomList = RoomList.builder()
				.roomNo(bookingDTO.getRoomNo()).build();
		
		Booking booking = Booking.builder()
				.bookDate(bookingDTO.getBookDate())
				.start(bookingDTO.getStart())
				.end(bookingDTO.getEnd())
				.roomList(roomList)
				.employees(employees)
				.build();
		
		return booking;
	}

	default BookingDTO entityToDto(Booking booking) {
		
		BookingDTO bookingDTO = BookingDTO.builder()
				.bookNo(booking.getBookNo())
				.bookDate(booking.getBookDate())
				.start(booking.getStart())
				.end(booking.getEnd())
				.RoomNo(booking.getRoomList().getRoomNo())
				.empNo(booking.getEmployees().getEmpNo())
				.build();
		
		return bookingDTO;
	}
}
