package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
