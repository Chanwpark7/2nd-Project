package com.fullstack.springboot.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.DeptScheduleDTO;
import com.fullstack.springboot.entity.DeptSchedule;

public interface DeptScheduleRepository extends JpaRepository<DeptSchedule, Long> {
	
	// deptSche 한개만 가져오기
	@Query("select new com.fullstack.springboot.dto.DeptScheduleDTO(ds) from DeptSchedule ds left join "
	        + " ds.deptInfo di where ds.deptSchNo = :deptSchNo and ds.deptInfo.deptNo = :deptNo")
	public List<DeptScheduleDTO> getDeptScheOne(@Param("deptSchNo") Long deptSchNo, @Param("deptNo") Long deptNo);

	
	//해당 날짜에 해당하는 부서 리스트 가져오기
	@Query("select new com.fullstack.springboot.dto.DeptScheduleDTO(ds) from DeptSchedule ds left join ds.deptInfo di left join ds.employees e "
			+ "where ds.deptInfo.deptNo = :deptNo and ds.startDate = :startDate and ds.endDate =:endDate")
	public List<DeptScheduleDTO> getDeptSchedulDay(@Param("deptNo") long deptNo, @Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

	
	//부서 리스트 가져오기
	@Query("select new com.fullstack.springboot.dto.DeptScheduleDTO(ds) from DeptSchedule ds join ds.deptInfo di join ds.employees e "
			+ "where ds.deptInfo.deptNo = :deptNo and ds.employees.empNo = :empNo")
	public List<DeptScheduleDTO> getDeptScheduleList(@Param("deptNo") long deptNo, @Param("empNo") long empNo);

	
	// selectDate 로 가져오기
	@Query("select new com.fullstack.springboot.dto.DeptScheduleDTO(ds) from DeptSchedule ds where ds.deptInfo.deptNo = :deptNo "
	        + "and ds.startDate <= :endDate and ds.endDate >= :startDate")
	List<DeptScheduleDTO> findSchedulesByDeptAndDate(@Param("deptNo") Long deptNo, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}