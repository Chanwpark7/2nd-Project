package com.fullstack.springboot.service.booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Booking;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.RoomList;
import com.fullstack.springboot.repository.BookingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	
	@Override
	public Long addBooking(BookingDTO bookingDTO) {
		
		Booking booking = dtoToEntity(bookingDTO);
		bookingRepository.save(booking);
		
		return booking.getBookNo();
	}
	
	@Override
	public PageResponseDTO<BookingDTO> getCRBookingList(PageRequestDTO pageRequestDTO) {
		
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("bookNo").descending());
		
		List<BookingDTO> result = bookingRepository.getBookList();
		
		List<BookingDTO> res = new ArrayList<BookingDTO>();
		
		for(BookingDTO ares : result) {
			if(ares.getRoomNo()<200L) {
				res.add(ares);
			}
		}
		
		int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), res.size());
		
		Page<BookingDTO> page = new PageImpl<BookingDTO>(res.subList(start, end),pageable,res.size());
		
		List<BookingDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<BookingDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
	
	@Override
	public PageResponseDTO<BookingDTO> getWRBookingList(PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("bookNo").descending());
		
		List<BookingDTO> result = bookingRepository.getBookList();
		
		List<BookingDTO> res = new ArrayList<BookingDTO>();
		
		for(BookingDTO ares : result) {
			if(ares.getRoomNo()>200L) {
				res.add(ares);
			}
		}
		
		int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), res.size());
		
		Page<BookingDTO> page = new PageImpl<BookingDTO>(res.subList(start, end),pageable,res.size());
		
		List<BookingDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<BookingDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
	
	@Override
	public void remove(Long bookNo) {
		Booking booking = Booking.builder()
				.bookNo(bookNo)
				.build();
		
		bookingRepository.delete(booking);
	}
	
	@Override
	public BookingDTO getOne(Long bookNo) {

		return entityToDto(bookingRepository.findById(bookNo).get());
	}

	@Override
	public void modify(Long bookNo, BookingDTO bookingDTO) {

		Booking booking = dtoToEntity(bookingDTO);
		booking.setBookNo(bookNo);
		
		bookingRepository.save(booking);
	}
}
