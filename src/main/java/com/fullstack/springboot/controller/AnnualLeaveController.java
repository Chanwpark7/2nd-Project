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
@RequestMapping("/api/annualleave")
public class AnnualLeaveController {

	private final AnnualleaveService annualleaveService;
	
	@GetMapping("/list/{empNo}")
	public PageResponseDTO<AnnualLeaveDTO> list(@PathVariable(name = "empNo") Long empNo, PageRequestDTO pageRequestDTO) {
		
		return annualleaveService.getALList(empNo, pageRequestDTO);
	}
	
	@GetMapping("/read/{empNo}")
	public AnnualLeaveDTO read(@PathVariable(name = "empNo") Long empNo) {
		return annualleaveService.getOne(empNo);
	}
	
	@PutMapping("/{empNo}")
	public void modify(@PathVariable(name = "empNo") Long empNo, @RequestBody AnnualLeaveDTO annualLeaveDTO) {
		
		annualleaveService.modifyAnnualleave(annualLeaveDTO, empNo);
	}
	
	@DeleteMapping("/{empNo}")
	public void delete(@PathVariable(name = "empNo") Long empNo) {
		annualleaveService.deleteAnnualleave(empNo);
	}
	
	@PostMapping("/set")
	public void set(@RequestBody EmployeesDTO employeesDTO) {

		annualleaveService.setAnnualleave(employeesDTO.getEmpNo());
	}
	
	@GetMapping("/count/{empNo}")
	public AnnualLeaveDTO countRead(@PathVariable("empNo")Long empNo) {
		return annualleaveService.getOne(empNo);
	}
}
