package com.fullstack.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.DeptScheduleDTO;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.DeptSchedule;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.DeptScheduleRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DeptScheduleServiceImpl implements DeptScheduleService {

	@Autowired
	private DeptScheduleRepository deptScheduleRepository;
	
	//팀 스케줄 목록 가져오긴
	@Override
	public List<DeptScheduleDTO> getDeptScheList(long deptNo, long empNo) {
		return deptScheduleRepository.getDeptScheduleList(deptNo, empNo);
	}

	//팀 일정 삭제
	@Override
	public List<DeptScheduleDTO> remove(long deptNo, long deptSchNo) {
		Optional<DeptSchedule> deptScheduleOne = deptScheduleRepository.findById(deptSchNo);
		
		if(deptScheduleOne.isPresent()) {
			deptScheduleRepository.deleteById(deptSchNo);
			log.warn("삭제완료");
		}else {
			log.warn("일정없다아아");
		}
		return null;
	}

	//팀 일정 추가 및 수정
	@Override
	public List<DeptScheduleDTO> addOrMod(DeptScheduleDTO deptScheduleDTO) {
		long deptSchNo = deptScheduleDTO.getDeptSchNo();
	    LocalDateTime startDate = deptScheduleDTO.getStartDate();
	    LocalDateTime endDate = deptScheduleDTO.getEndDate();
	    String scheduleText = deptScheduleDTO.getScheduleText();
	    long deptNo = deptScheduleDTO.getDeptNo();
	    String deptName = deptScheduleDTO.getDeptName();
	    long empNo = deptScheduleDTO.getEmpNo();
	    
	    log.info("-----------------");
	    log.info(deptScheduleDTO.getDeptSchNo() == 0);
	    
	    if(deptSchNo != 0) {
	    	Optional<DeptSchedule> deptSchRes = deptScheduleRepository.findById(deptSchNo);
	    	DeptSchedule deptSchedule = deptSchRes.orElseThrow();
	    	deptSchedule.changeScheduleDate(startDate,endDate);
	    	deptSchedule.changeScheduleText(scheduleText);
	    	deptScheduleRepository.save(deptSchedule);
	    	return getDeptScheList(deptNo, empNo);
	    }
	    
	    return getDeptScheList(deptNo, empNo);
	}

	//팀 스케줄 등록
	@Override
	public Long register(DeptScheduleDTO deptScheduleDTO){
			DeptSchedule deptSchedule = DTOToEntity(deptScheduleDTO);
			deptScheduleRepository.save(deptSchedule);
			return deptSchedule.getDeptSchNo();
	}

	//팀 스케줄 하나만 가져오기
	@Override
	public DeptScheduleDTO getDeptScheduleById(Long deptNo, Long deptSchNo) {
		DeptSchedule deptSchedule = deptScheduleRepository.findById(deptSchNo).orElseThrow();
		if(deptSchedule.getStartDate() == null || deptSchedule.getEndDate() == null) {
			log.error("deptsche--->" + deptSchNo);
		}
		DeptInfo deptInfo = deptSchedule.getDeptInfo();
		Employees employees = deptSchedule.getEmployees();
		return entityToDTO(deptSchedule, employees, deptInfo);
	}
	
	//해당날짜 팀 스케줄 리스트 가져오기
	@Override
	public List<DeptScheduleDTO> getDeptScheduleList(Long deptNo, LocalDateTime startOfDay, LocalDateTime endOfDay) {
		log.info("start" +  startOfDay);
		log.info("end" + endOfDay);
		return deptScheduleRepository.findSchedulesByDeptAndDate(deptNo, startOfDay, endOfDay);
	}





}
