package com.fullstack.springboot.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.EmployeesService;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;
import com.fullstack.springboot.service.commute.CommuteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/commute")
public class CommuteController {

	private final CommuteService commuteService;
	
	@GetMapping("/list/{empNo}")
	public PageResponseDTO<CommuteDTO> list(@PathVariable(name = "empNo") Long empNo, PageRequestDTO pageRequestDTO) {
		
		return commuteService.getListCommute(empNo, pageRequestDTO);
	}
	
	@PutMapping("/checkout/{empNo}")
	public void checkOut(@PathVariable(name = "empNo") Long empNo) {
		
		commuteService.checkOut(empNo);
	}
	
//	@DeleteMapping("/{empNo}")
//	public void delete(@PathVariable(name = "empNo") Long empNo) {
//		annualleaveService.deleteAnnualleave(empNo);
//	}
	
	@PostMapping("/set/{empNo}")
	public Long checkIn(@PathVariable(name = "empNo") Long empNo) {

		return commuteService.addCommute(empNo);
	}
	
	@PutMapping("/modify/{empNo}")
	public void putMethodName(@PathVariable(name = "empNo") Long empNo, @RequestBody CommuteDTO commuteDTO) {
		commuteService.modifyCommute(empNo,commuteDTO);
	}
}
