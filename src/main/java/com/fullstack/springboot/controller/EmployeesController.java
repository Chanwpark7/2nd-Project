package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.EmployeesService;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;
import com.fullstack.springboot.service.commute.CommuteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/employees")
public class EmployeesController {

	private final EmployeesService employeesService;
	
	
	private final CommuteService commuteService;
	
	@GetMapping("/list")
	public PageResponseDTO<EmployeesDTO> list(PageRequestDTO pageRequestDTO) {
		
		return employeesService.getEmployeesListPage(pageRequestDTO);
	}
	
	@GetMapping("/read/{empNo}")
	public EmployeesDTO read(@PathVariable(name = "empNo") Long empNo) {
		return employeesService.getOne(empNo);
	}
	
	@PutMapping("/{empNo}")
	public void modify(@PathVariable(name = "empNo") Long empNo, @RequestBody EmployeesDTO employeesDTO) {
		
		employeesService.modifyEmployees(employeesDTO);
	}
	
	@DeleteMapping("/{empNo}")
	public void delete(@PathVariable(name = "empNo") Long empNo) {
		employeesService.deleteEmployees(empNo);
	}
	
	@PostMapping("/add")
	public EmployeesDTO add(@RequestBody EmployeesDTO employeesDTO) {

		return employeesService.addEmployees(employeesDTO);
	}
	
}
