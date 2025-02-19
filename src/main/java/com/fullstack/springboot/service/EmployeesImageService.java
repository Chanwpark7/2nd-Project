package com.fullstack.springboot.service;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.dto.EmployeesImageDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.EmployeesImage;

public interface EmployeesImageService  {

	Long register(EmployeesImageDTO imageDTO);
	
	
	default EmployeesImageDTO entityToDto(EmployeesImage employeesImage, Employees employees) {
		EmployeesImageDTO employeesImageDTO = EmployeesImageDTO.builder()
				.empImgNo(employeesImage.getEmpImgNo())
				.empNo(employees.getEmpNo())
				.url(employeesImage.getUrl())

				.uuid(employeesImage.getUuid())

				.build();
		return employeesImageDTO;
	}
	
	default EmployeesImage dtoToEntity(EmployeesImageDTO dto) {
		
		return EmployeesImage.builder()
				.empImgNo(dto.getEmpImgNo())
				.employees(Employees.builder().empNo(dto.getEmpNo()).build())
				.url(dto.getUrl())

				.uuid(dto.getUploadFileNames().get(0))

				.build();
	}
	
	
	EmployeesImageDTO getOne(long empNo);



	public void update(long empNo, List<String> newFileNames, EmployeesImageDTO employeesImageDTO);

}
