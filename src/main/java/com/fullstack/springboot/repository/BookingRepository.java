package com.fullstack.springboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.entity.Booking;
import com.fullstack.springboot.entity.RoomList;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) from Booking bk where bk.bookDate >= curdate()")
	List<BookingDTO> getBookList();
	
	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) from Booking bk where bk.bookDate = :bookDate order by bk.start")
	List<BookingDTO> getBookListAtDate(@Param("bookDate") LocalDate bookDate);
	
	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) from Booking bk where bk.bookDate = :bookDate and bk.roomList = :roomNo")
	List<BookingDTO> getBookListAtDateWithRoomNo(@Param("bookDate") LocalDate bookDate, @Param("roomNo") RoomList roomNo);

	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) "
			+ "from Booking bk "
			+ "left join RoomList rl on bk.roomList = rl "
			+ "where rl.roomNo = :roomNo")
	Page<BookingDTO> getBookingListByRoomNo(Pageable pageable, @Param("roomNo") Long roomNo);
	
	@Query("Select new com.fullstack.springboot.dto.BookingDTO(bk) "
			+ "from Booking bk "
			+ "left join RoomList rl on bk.roomList = rl "
			+ "where rl.roomNo = :roomNo")
	List<BookingDTO> getBookingListWithRoomNo(@Param("roomNo") Long roomNo);
}
