package com.fullstack.springboot.controller.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.DeptScheduleDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.service.DeptScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/deptSchedule")
public class DeptScheduleController {

	private final DeptScheduleService service;
	
	@GetMapping("/read/{deptNo}/{empNo}")
	public List<DeptScheduleDTO> getDeptSche(PageRequestDTO pageRequestDTO, @PathVariable("deptNo")Long deptNo,@PathVariable("empNo")Long empNo){
		log.error("!!!!!!!!!!!!!");
		log.error(pageRequestDTO);
		return service.getDeptScheList(deptNo, empNo);
	}
	
	// 팀스케줄 등록 (팀장만 가능)
	@PostMapping("/register/{deptNo}/{empNo}")
	public Map<String, Long> register(@RequestBody DeptScheduleDTO deptScheduleDTO, @PathVariable("deptNo")Long deptNo, @PathVariable("empNo")Long empNo){
		log.error("deptNo" + deptNo); // 문제x
		log.error("empNo" + empNo); //문제x
		log.error(deptScheduleDTO); //startdate랑 enddate 잘 받아오는디 ??? ;;
		Long deptSchNo = service.register(deptScheduleDTO);
		return Map.of("deptSchNo",deptSchNo);
	}
	
	// 팀 스케줄 수정 (팀장만 가능)
	@PutMapping("/mod/{deptNo}/{empNo}/{deptSchNo}")
	public Map<String, String> modify(@PathVariable("deptNo")Long deptNo, @PathVariable("empNo")Long empNo, @PathVariable("deptSchNo")Long deptSchNo, @RequestBody DeptScheduleDTO deptScheduleDTO){
		log.error("~~~~~~~~~");
		deptScheduleDTO.setDeptSchNo(deptSchNo);
		service.addOrMod(deptScheduleDTO);
		return Map.of("Result","Success");
	}
	
	
	@DeleteMapping("/delete/{deptNo}/{deptSchNo}")
	public Map<String, String> remove(@PathVariable("deptNo")Long deptNo, @PathVariable("deptSchNo")Long deptSchNo){
		service.remove(deptNo, deptSchNo);
		return Map.of("Result","Success");
	}
	
	@GetMapping("/read/{deptNo}/{empNo}/{deptSchNo}")
	public DeptScheduleDTO getDeptSchedule(@PathVariable("deptNo")Long deptNo, @PathVariable("empNo")Long empNo, @PathVariable("deptSchNo")Long deptSchNo) {
		log.error("!!!!!!");		
		DeptScheduleDTO deptScheduleDTO = service.getDeptScheduleById(deptNo, deptSchNo);
		return deptScheduleDTO;
	}
	
}
