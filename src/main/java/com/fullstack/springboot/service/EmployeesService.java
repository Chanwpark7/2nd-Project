package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.EmployeesWithDeptAndJobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;

import jakarta.servlet.http.HttpServletResponse;

public interface EmployeesService {

	public EmployeesDTO addEmployees(EmployeesDTO employeesDTO);

	public EmployeesDTO modifyEmployees(EmployeesDTO employeesDTO);
	
	public void deleteEmployees(Long empNo);
	
	public PageResponseDTO<EmployeesDTO> getEmployeesListPage(PageRequestDTO pageRequestDTO);
	
	public PageResponseDTO<EmployeesDTO> getEmployeesFind(PageRequestDTO pageRequestDTO, String name);
	
	public EmployeesDTO getOne(Long empNo);
	
	public long getDDay(long empNo);
	
	public PageResponseDTO<EmployeesDTO> getBirthEmp(PageRequestDTO pageRequestDTO);
  
	public List<EmployeesDTO> addAllList();

	public List<EmployeesWithDeptAndJobDTO> allListWithDeptAndJob();
	
	public void downloadExcelForm(HttpServletResponse res);
	
	public int readExcelFile(MultipartFile file);
	
	public Long checkIfMailExist(String mailAdress);
	
}
