package com.fullstack.springboot.controller.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.service.EmpScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RequestMapping("/empSchedule")
public class EmpScheduleController {

	private final EmpScheduleService service;

	@PostMapping("/register/{empNo}")
	public Map<String, Long> register(@RequestBody EmpScheduleDTO empScheduleDTO, @PathVariable("empNo")Long empNo){
		Long empSchNo = service.register(empScheduleDTO);
		return Map.of("empSchNo", empSchNo);
	}
	
	@GetMapping("/read/{empNo}")
	public List<EmpScheduleDTO> getEmpSche(PageRequestDTO pageRequestDTO, @PathVariable("empNo")Long empNo){
		log.error("--------------");
		log.error(pageRequestDTO);
		return service.getEmpScheduleList(empNo);	
	}
	
	@PutMapping("/mod/{empNo}/{empSchNo}")
	public Map<String, String> modify(@PathVariable(name = "empSchNo") Long empSchNo, @PathVariable(name = "empNo") Long empNo, @RequestBody EmpScheduleDTO empScheduleDTO) {
	    empScheduleDTO.setEmpSchNo(empSchNo);
	    service.addOrMod(empScheduleDTO);
	    return Map.of("Result", "Success");
	}
	
	@DeleteMapping("/{empNo}/{empSchNo}")
	public Map<String, String> remove(@PathVariable(name = "empNo") Long empNo,  @PathVariable(name = "empSchNo") Long empSchNo) {
	    service.remove(empNo, empSchNo);
	    return Map.of("Result", "Success");
	}

	@GetMapping("/read/{empNo}/{empSchNo}")
	public EmpScheduleDTO getEmpSchedule(@PathVariable("empNo") Long empNo, @PathVariable("empSchNo") Long empSchNo) {
	    EmpScheduleDTO empScheduleDTO = service.getEmpScheduleById(empNo, empSchNo);
	    return empScheduleDTO;
	}
	
	
}
