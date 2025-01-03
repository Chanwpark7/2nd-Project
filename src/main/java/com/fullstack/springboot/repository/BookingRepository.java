package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) from Booking bk")
	List<BookingDTO> getBookList();

	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) "
			+ "from Booking bk "
			+ "left join RoomList rl on bk.roomList = rl "
			+ "where rl.roomNo = :roomNo")
	Page<BookingDTO> getBookingListByRoomNo(Pageable pageable, @Param("roomNo") Long roomNo);
}
