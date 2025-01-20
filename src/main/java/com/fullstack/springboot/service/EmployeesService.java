package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;

public interface EmployeesService {

	public EmployeesDTO addEmployees(EmployeesDTO employeesDTO);

	public EmployeesDTO modifyEmployees(EmployeesDTO employeesDTO);
	
	public void deleteEmployees(Long empNo);
	
	public PageResponseDTO<EmployeesDTO> getEmployeesListPage(PageRequestDTO pageRequestDTO);
	
	public EmployeesDTO getOne(Long empNo);
	
	public long getDDay(long empNo);
	
	public PageResponseDTO<EmployeesDTO> getBirthEmp(PageRequestDTO pageRequestDTO);
}
