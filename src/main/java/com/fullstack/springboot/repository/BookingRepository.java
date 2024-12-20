package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) from Booking bk")
	List<BookingDTO> getBookList();
}
