package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.deptinfo.DeptInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/deptinfo")
public class DeptInfoController {

	private final DeptInfoService deptInfoService;
	
	@GetMapping("/list")
	public List<DeptInfoDTO> list() {
		return deptInfoService.getDeptList();
	}
	
	@GetMapping("/read/{deptNo}")
	public DeptInfoDTO getOne(@PathVariable(name = "deptNo") Long deptNo) {
		return deptInfoService.getOne(deptNo);
	}
	
	@GetMapping("/list/{deptNo}")
	public PageResponseDTO<EmployeesDTO> getEmpList(PageRequestDTO pageRequestDTO, @PathVariable(name = "deptNo") Long deptNo) {
		
		return deptInfoService.getEmployeesListPageByDeptNo(pageRequestDTO, deptNo);
	}
	
	@PutMapping("/{deptNo}")
	public void modify(@PathVariable(name = "deptNo") Long deptNo , @RequestBody DeptInfoDTO deptInfoDTO) {
		
		deptInfoService.createOrModifyDept(deptInfoDTO);
	}
	
	@PostMapping("/add")
	public void postMethodName(@RequestBody DeptInfoDTO deptInfoDTO) {
		deptInfoService.createOrModifyDept(deptInfoDTO);
	}
	
	@DeleteMapping("/{deptNo}")
	public void delete(@PathVariable(name = "deptNo") Long deptNo) {
		deptInfoService.deleteDept(deptNo);
	}
}
