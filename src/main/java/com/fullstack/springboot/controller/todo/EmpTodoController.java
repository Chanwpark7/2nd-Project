package com.fullstack.springboot.controller.todo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.controller.schedule.EmpDeptScheduleController;
import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.service.DeptScheduleService;
import com.fullstack.springboot.service.EmpScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RequestMapping("/empTodo")
public class EmpTodoController {
	
	private final EmpScheduleService empScheduleService;
	
	@GetMapping("/read/{empNo}/{selectDate}")
	public Map<String, Object> getEmpTodoList(@PathVariable("empNo") Long empNo, @PathVariable("selectDate") String selectDate) {
	    log.error("empNo" + empNo + " selectDate " + selectDate);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate date = LocalDate.parse(selectDate, formatter);

	    LocalDateTime startOfDay = date.atStartOfDay();
	    LocalDateTime endOfDay = date.atTime(23, 59, 59);

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String startFormatted = sdf.format(java.sql.Timestamp.valueOf(startOfDay));
	    String endFormatted = sdf.format(java.sql.Timestamp.valueOf(endOfDay));

	    List<EmpScheduleDTO> empSchedule = empScheduleService.getEmpScheduleList(empNo, startOfDay, endOfDay);
	    log.warn("empSchedule " + empSchedule);

	    return Map.of("empSchedule", empSchedule);
	}

}
