package com.fullstack.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.entity.DayOff;

public interface DayOffRepository extends JpaRepository<DayOff, Long> {

	@Query("Select do from DayOff do where do.employees.empNo = :empNo and FUNCTION('DATE',do.dayOffDate) = :dayOffDate")
	public DayOff getOneDayOff(@Param("empNo") Long empNo, @Param("dayOffDate") String dayOffDate);
	
	@Query("Select new com.fullstack.springboot.dto.DayOffDTO(do) from DayOff do where do.employees.empNo = :empNo")
	public List<DayOffDTO> getDayOffList(@Param("empNo") Long empNo);
	
	@Query("Select new com.fullstack.springboot.dto.DayOffDTO(do) from DayOff do")
	public Page<DayOffDTO> getList(Pageable pageable);
}
