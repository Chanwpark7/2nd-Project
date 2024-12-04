package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.DayOff;

public interface DayOffRepository extends JpaRepository<DayOff, Long> {

}
