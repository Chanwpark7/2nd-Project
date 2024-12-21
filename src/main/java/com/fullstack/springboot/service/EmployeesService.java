package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;

public interface EmployeesService {

	public EmployeesDTO addEmployees(EmployeesDTO employeesDTO);

	public EmployeesDTO modifyEmployees(EmployeesDTO employeesDTO);
	
	public void deleteEmployees(Long empNo);
	
	public Page<EmployeesDTO> getEmployeesListPage(PageRequestDTO pageRequestDTO);
	
	public List<?> getOne(EmployeesDTO employeesDTO);
}
