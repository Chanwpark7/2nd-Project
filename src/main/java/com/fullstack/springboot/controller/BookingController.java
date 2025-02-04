package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.booking.BookingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/booking")
public class BookingController {

	private final BookingService bookingService;
	
	@GetMapping("/list/cr/{bookDate}")
	public PageResponseDTO<BookingDTO> crList(@PathVariable(name="bookDate") String bookDate, PageRequestDTO pageRequestDTO) {
		
		return bookingService.getCRBookingList(bookDate,pageRequestDTO);
	}
	
	@GetMapping("/list/date/{bookDate}")
	public List<BookingDTO> listAtDate(@PathVariable(name="bookDate") String bookDate) {
		
		return bookingService.getBookingListAtDate(bookDate);
	}
	
	@GetMapping("/list/date/{bookDate}/{roomNo}")
	public List<BookingDTO> listAtDateWithRoomNo(@PathVariable(name="bookDate") String bookDate, @PathVariable(name="roomNo") Long roomNo) {
		
		return bookingService.getBookingListAtDate(bookDate,roomNo);
	}
	
	@GetMapping("/read/{bookNo}")
	public BookingDTO read(@PathVariable(name = "bookNo") Long bookNo) {
		return bookingService.getOne(bookNo);
	}
	
	@PutMapping("/{bookNo}")
	public void modify(@PathVariable(name = "bookNo") Long bookNo, @RequestBody BookingDTO bookingDTO) {
		
		bookingService.modify(bookNo, bookingDTO);
	}
	
	@DeleteMapping("/{bookNo}")
	public void delete(@PathVariable(name = "bookNo") Long bookNo) {

		bookingService.remove(bookNo);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody BookingDTO	bookingDTO) {

		bookingService.addBooking(bookingDTO);
	}
	
	
}
