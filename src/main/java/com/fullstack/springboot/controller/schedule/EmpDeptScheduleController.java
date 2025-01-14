package com.fullstack.springboot.controller.schedule;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.DeptScheduleDTO;
import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.service.DeptScheduleService;
import com.fullstack.springboot.service.EmpScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RequestMapping("/empDeptSchedule")
public class EmpDeptScheduleController {
	
	private final EmpScheduleService empScheduleService;
	private final DeptScheduleService deptScheduleService;
	
	//달력에 일정 다 뿌리기
	@GetMapping("/read/{deptNo}/{empNo}")
	public Map<String, Object> getAllSchedule(@PathVariable("deptNo")String deptNo){
		log.error(deptNo);
		
		String[] empDeptArr = deptNo.split(",");
		
		log.warn("dddd" + deptNo);
		log.warn("Eeee" + empDeptArr);
		
		Long deptNum = Long.parseLong(empDeptArr[0]);
		Long empNum = Long.parseLong(empDeptArr[1]);
		
		List<DeptScheduleDTO> deptSchedule = deptScheduleService.getDeptScheList(deptNum, empNum);
		List<EmpScheduleDTO> empSchedule = empScheduleService.getEmpScheduleList(empNum);
		
		return Map.of("deptSchedule", deptSchedule, "empSchedule", empSchedule);
	}
	
	//스케줄 삭제
	@DeleteMapping("/{empNo}/{empSchNo}")
	public Map<String, String> remove(@PathVariable(name = "empNo")Long empNo, @PathVariable(name = "empSchNo") Long empSchNo){
		log.error("------");
		empScheduleService.remove(empNo, empSchNo);
		return Map.of("Result","Success");
	}
	
	//해당 날짜에 일정 가져오기
	@GetMapping("/list/{deptNo}/{empNo}/{selectDate}")
	public Map<String, Object> getTodaySchedule(@PathVariable("deptNo") Long deptNo, @PathVariable("empNo") Long empNo, @PathVariable("selectDate") String selectDate) {
	    log.error("deptNo" + deptNo + "empNo" +  empNo + "selectDate" + selectDate); 
	    try {
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 	    LocalDate date = LocalDate.parse(selectDate, formatter);

	 	    LocalDateTime startOfDay = date.atStartOfDay();
	 	    LocalDateTime endOfDay = date.atTime(23, 59, 59);

	 	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	    String startFormatted = sdf.format(java.sql.Timestamp.valueOf(startOfDay));
	 	    String endFormatted = sdf.format(java.sql.Timestamp.valueOf(endOfDay));

	        List<DeptScheduleDTO> deptSchedule = deptScheduleService.getDeptScheduleList(deptNo, startOfDay, endOfDay);
	        log.warn("deptSchedule" + deptSchedule);  // 잘 받음ㅇㅇ
	        List<EmpScheduleDTO> empSchedule = empScheduleService.getEmpScheduleList(empNo, startOfDay, endOfDay);
	        log.warn("empSchedule" + empSchedule);   // 잘 받았음ㅇㅇ

	        return Map.of("deptSchedule", deptSchedule, "empSchedule", empSchedule);
	    } catch (Exception e) {
	        log.error("Error occurred: {}", e.getMessage());
	        return Map.of("deptSchedule", new ArrayList<>(), "empSchedule", new ArrayList<>());
	    }
	}

	
	
	
	
	
	
}
