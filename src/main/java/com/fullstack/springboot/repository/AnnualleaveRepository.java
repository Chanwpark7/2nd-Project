package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.entity.AnnualLeave;

public interface AnnualleaveRepository extends JpaRepository<AnnualLeave, Long> {

	@Query("Select al from AnnualLeave al where al.employees.empNo = :empNo")
	public AnnualLeave getOne(@Param("empNo") Long empNo);
}
