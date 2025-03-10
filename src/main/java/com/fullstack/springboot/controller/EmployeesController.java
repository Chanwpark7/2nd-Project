package com.fullstack.springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.EmployeesWithDeptAndJobDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.EmployeesService;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;
import com.fullstack.springboot.service.commute.CommuteService;

import io.github.classgraph.Resource;
import jakarta.servlet.http.HttpServletResponse;
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
	
	@GetMapping("/findlist/{name}")
	public PageResponseDTO<EmployeesDTO> findList(PageRequestDTO pageRequestDTO, @PathVariable String name) {
	    log.error("컨트롤러 ");
	    return employeesService.getEmployeesFind(pageRequestDTO, name); 
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
	
	@PostMapping("/add/excel")
	public int addFiles(@RequestBody List<MultipartFile> files) {
		int added = employeesService.readExcelFile(files.get(0));
		
		return added;
	}
	
	@GetMapping("/dday/{empNo}")
	public long getDDay(@PathVariable("empNo")long empNo) {
		return employeesService.getDDay(empNo);
	}
	
	@GetMapping("/birth")
	public PageResponseDTO<EmployeesDTO> getBirth(PageRequestDTO pageRequestDTO){
		return employeesService.getBirthEmp(pageRequestDTO);
	}
	@GetMapping("/list/all")
	public List<EmployeesDTO> getAllList() {
		return employeesService.addAllList();
	}
	
	@GetMapping("/download/form")
	public void DownloadExcelForm(HttpServletResponse res){
		employeesService.downloadExcelForm(res);
	}
	
	@PostMapping("/check")
	public Long getMethodName(@RequestBody EmployeesDTO employeesDTO) {
		return employeesService.checkIfMailExist(employeesDTO.getMailAddress());
	}
	
	@GetMapping("/list/detail")
	public List<EmployeesWithDeptAndJobDTO> getDetailLists() {
		return employeesService.allListWithDeptAndJob();
	}
	
}
