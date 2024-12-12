package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.AnnualLeave;

public interface AnnulLeaveRepository extends JpaRepository<AnnualLeave, Long> {
	
}
