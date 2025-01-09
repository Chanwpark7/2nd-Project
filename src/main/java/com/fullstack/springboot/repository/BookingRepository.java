package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fullstack.springboot.entity.Booking;
import com.fullstack.springboot.entity.RoomList;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	
}
