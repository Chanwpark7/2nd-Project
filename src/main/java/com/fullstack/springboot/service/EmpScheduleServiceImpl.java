package com.fullstack.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.entity.EmpSchedule;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.EmpScheuleRepository;
import com.fullstack.springboot.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class EmpScheduleServiceImpl implements EmpScheduleService {

	@Autowired
	private EmpScheuleRepository empScheuleRepository;
	
	@Autowired
	private EmployeesRepository employeesRepository;
	
	
	// 개인 스케줄 목록 가져오기
	@Override
	public List<EmpScheduleDTO> getEmpScheduleList(long empNo) {
		return empScheuleRepository.getEmpScheList(empNo);
	}
	
	// 일정 삭제
	@Override
	public List<EmpScheduleDTO> remove(long empNo, long empSchNo) {
		 Optional<EmpSchedule> empScheduleOne = empScheuleRepository.findById(empSchNo);
		 
		 
		 if(empScheduleOne.isPresent()) {
			 empScheuleRepository.deleteById(empSchNo);
			 log.warn("삭제완료");
		 }else {
			 log.warn("일정 없다");
		 }
		 return empScheuleRepository.getEmpScheList(empNo);		 
		}

	// 일정 추가 및 수정
	@Override
	public List<EmpScheduleDTO> addOrMod(EmpScheduleDTO empScheduleDTO) {
		
		long empSchNo = empScheduleDTO.getEmpSchNo();
		LocalDateTime startDate = empScheduleDTO.getStartDate();
		LocalDateTime endDate = empScheduleDTO.getEndDate();
		String scheduleText = empScheduleDTO.getScheduleText();
		long empNo = empScheduleDTO.getEmpNo();
		
		log.info("------------------------");
		log.info(empScheduleDTO.getEmpSchNo() == 0);
		
		if(empSchNo != 0) {	
			Optional<EmpSchedule> empScheRes = empScheuleRepository.findById(empSchNo);
			EmpSchedule empSchedule = empScheRes.orElseThrow();
			empSchedule.changeScheduleText(scheduleText);
			empSchedule.changeScheduleDate(startDate,endDate);
			empScheuleRepository.save(empSchedule);
			return getEmpScheduleList(empNo);
		}
		
		return getEmpScheduleList(empNo);
	}
	
	//개인일정 등록
	@Override
	public Long register(EmpScheduleDTO empScheduleDTO) {
		EmpSchedule empSchedule = DTOToEntity(empScheduleDTO);
		empScheuleRepository.save(empSchedule);
		return empSchedule.getEmpSchNo();
	}

	//개인스케줄 한 개 가져오기
	@Override
	public EmpScheduleDTO getEmpScheduleById(Long empNo, Long empSchNo) {
	   
	    EmpSchedule empSchedule = empScheuleRepository.findById(empSchNo).orElseThrow();
	    
	    if (empSchedule.getStartDate() == null || empSchedule.getEndDate() == null) {
	        log.error("empSchNO ---> " + empSchNo);
	    }

	    Employees employees = empSchedule.getEmployees();
	    return EntityToDTO(empSchedule, employees);
	}

	//해당 날짜에 해당하는 개인 일정 리스트 가져오기
	@Override
	public List<EmpScheduleDTO> getEmpScheduleList(Long empNo, LocalDateTime startOfDay, LocalDateTime endOfDay) {
		log.info("startOfDay" + startOfDay);
		log.info("endOfDay" + endOfDay);

		return empScheuleRepository.getEmpScheduleByDate(empNo, startOfDay, endOfDay);
	
	}

	

	


	




	
	
}
