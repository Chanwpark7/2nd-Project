package com.fullstack.springboot.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.entity.EmpSchedule;

public interface EmpScheuleRepository extends JpaRepository<EmpSchedule, Long> {

	// 해당 직원에 그날 일정 리스트 가져오기
	@Query("select new com.fullstack.springboot.dto.EmpScheduleDTO(es.empSchNo, es.startDate, es.endDate, es.scheduleText, e.empNo) "
	        + "from EmpSchedule es inner join es.employees e where e.empNo = :empNo and es.startDate = :startDate and es.endDate =:endDate")
	public List<EmpScheduleDTO> getEmpScheduleDate(@Param("empNo") Long empNo, @Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);


	// 해당 직원에 일정 리스트 가져오기
	@Query("select new com.fullstack.springboot.dto.EmpScheduleDTO(es.empSchNo, es.startDate, es.endDate, es.scheduleText, e.empNo)  from EmpSchedule es left join Employees e on"
			+ " es.employees.empNo = e.empNo where es.employees.empNo = :empNo")
	public List<EmpScheduleDTO> getEmpScheList(@Param("empNo")Long empNo);


	// 해당 직원에 일정 한개만 select 해오기
	@Query("select new com.fullstack.springboot.dto.EmpScheduleDTO(es.empSchNo, es.startDate, es.endDate, es.scheduleText, e.empNo) from EmpSchedule es "
	        + "inner join es.employees e where es.employees.empNo = :empNo and es.empSchNo = :empSchNo")
	public List<EmpScheduleDTO> getEmpScheOne(@Param("empNo") Long empNo, @Param("empSchNo") Long empSchNo);


	// selectDate 로 가져오기
	@Query("select new com.fullstack.springboot.dto.EmpScheduleDTO(es.empSchNo, es.startDate, es.endDate, es.scheduleText, e.empNo) "
	        + "from EmpSchedule es inner join es.employees e where e.empNo = :empNo and es.startDate <= :endDate and es.endDate >= :startDate")
	public List<EmpScheduleDTO> getEmpScheduleByDate(@Param("empNo") Long empNo, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
