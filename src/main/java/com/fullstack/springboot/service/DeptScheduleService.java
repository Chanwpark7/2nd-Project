package com.fullstack.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.DeptScheduleDTO;
import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.DeptSchedule;
import com.fullstack.springboot.entity.Employees;


public interface DeptScheduleService {
	
	//팀 스케줄 목록 가져오기
	public List<DeptScheduleDTO> getDeptScheList(long deptNo, long empNo);
	
	//팀 스케줄 삭제
	public List<DeptScheduleDTO> remove(long deptNo, long deptSchNo);
	
	//팀 스케줄 수정
	public List<DeptScheduleDTO> addOrMod(DeptScheduleDTO deptScheduleDTO);
	
	//팀 스케줄 등록
	public Long register(DeptScheduleDTO deptScheduleDTO);
	
	//팀 스케줄 한 개 가져오기
	public DeptScheduleDTO getDeptScheduleById(Long deptNo, Long deptSchNo);
	
	// 해당 날짜에 팀 스케줄 리스트 가져오기
	public List<DeptScheduleDTO> getDeptScheduleList(Long deptNo, LocalDateTime startOfDay, LocalDateTime endOfDay);
	
	// dto ---> entity
	default DeptSchedule DTOToEntity(DeptScheduleDTO dto) {
		DeptInfo deptInfo = DeptInfo.builder().deptNo(dto.getDeptNo()).build();
		Employees employees = Employees.builder().empNo(dto.getEmpNo()).build();
		
		DeptSchedule entity = DeptSchedule.builder()
				.deptSchNo(dto.getDeptSchNo())
				.scheduleText(dto.getScheduleText())
				.startDate(dto.getStartDate())
				.endDate(dto.getEndDate())
				.deptInfo(deptInfo)
				.employees(employees)
				.build();
		return entity;
	}
	
	
	// entity ---> dto
	default DeptScheduleDTO entityToDTO(DeptSchedule deptSchedule, Employees employees, DeptInfo deptInfo) {
		DeptScheduleDTO deptScheduleDTO = DeptScheduleDTO.builder()
				.startDate(deptSchedule.getStartDate())
				.endDate(deptSchedule.getEndDate())
				.scheduleText(deptSchedule.getScheduleText())
				.deptSchNo(deptSchedule.getDeptSchNo())
				.deptNo(deptInfo.getDeptNo())
				.empNo(employees.getEmpNo())
				.build();
		return deptScheduleDTO;
	}
}
