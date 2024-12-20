package com.fullstack.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.entity.Commute;

public interface CommuteRepository extends JpaRepository<Commute, Long> {

	@Query("Select new com.fullstack.springboot.dto.CommuteDTO(comm) "
			+ "from Commute comm "
			+ "where comm.checkOutTime is null "
			+ "and FUNCTION('DATE',comm.checkInTime) = CURRENT_DATE "
			+ "and comm.employees.empNo = :empNo")
	CommuteDTO whenCheckingOut(@Param("empNo") Long empNo);
	
	@Query("Select new com.fullstack.springboot.dto.CommuteDTO(comm) from Commute comm where comm.employees.empNo = :empNo")
	Page<CommuteDTO> getListComm(@Param("empNo") Long empNo, Pageable pageable);
}
