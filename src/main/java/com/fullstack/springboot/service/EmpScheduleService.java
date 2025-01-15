package com.fullstack.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fullstack.springboot.dto.DeptScheduleDTO;
import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.DeptSchedule;
import com.fullstack.springboot.entity.EmpSchedule;
import com.fullstack.springboot.entity.Employees;

public interface EmpScheduleService {
	
	// 개인 스케줄 목록 가져오기
	public List<EmpScheduleDTO> getEmpScheduleList(long empNo);
	
	// 일정 삭제
	public List<EmpScheduleDTO> remove(long empNo, long empSchNo);
	
	// 일정 수정
	public List<EmpScheduleDTO> addOrMod(EmpScheduleDTO empScheduleDTO);
	
	// 일정 등록
	public Long register(EmpScheduleDTO empScheduleDTO);
	
	// 글 한개 가져오기
	public EmpScheduleDTO getEmpScheduleById(Long empNo, Long empSchNo);
	
	//해당 날짜에 해당하는 개인 일정 list 다 가져오기
	public List<EmpScheduleDTO> getEmpScheduleList(Long empNo, LocalDateTime startOfDay, LocalDateTime endOfDay);
	
	// dto ---> entity
		default EmpSchedule DTOToEntity(EmpScheduleDTO dto) {
			Employees employees = Employees.builder().empNo(dto.getEmpNo()).build();
			EmpSchedule entity = EmpSchedule.builder()
					.empSchNo(dto.getEmpSchNo())
					.startDate(dto.getStartDate())
					.endDate(dto.getEndDate())
					.scheduleText(dto.getScheduleText())
					.employees(employees)
					.build();
			return entity;
		}
		
	// entity ---> dto
		default EmpScheduleDTO EntityToDTO(EmpSchedule empSchedule, Employees employees) {
			EmpScheduleDTO empScheduleDTO = EmpScheduleDTO.builder()
					.empSchNo(empSchedule.getEmpSchNo())
					.startDate(empSchedule.getStartDate())
					.endDate(empSchedule.getEndDate())
					.scheduleText(empSchedule.getScheduleText())
					.empNo(employees.getEmpNo())
					.build();
			return empScheduleDTO;
		}
			
		

}
