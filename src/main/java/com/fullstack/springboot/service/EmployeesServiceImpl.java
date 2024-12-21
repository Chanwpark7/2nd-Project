package com.fullstack.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.DeptInfoRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {
	
	private final EmployeesRepository employeesRepository;
	private final DeptInfoRepository deptInfoRepository;
	private final JobRepository jobRepository;
	
	@Override
	public EmployeesDTO addEmployees(EmployeesDTO employeesDTO) {
		
		Employees employees = Employees.builder()
				.firstName(employeesDTO.getFirstName())
				.lastName(employeesDTO.getLastName())
				.hireDate(employeesDTO.getHireDate())
				.mailAddress(employeesDTO.getMailAddress())
				.salary(employeesDTO.getSalary())
				.deptInfo(deptInfoRepository.findById(employeesDTO.getDeptNo()).get())
				.job(jobRepository.findById(employeesDTO.getJobNo()).get())
				.birthday(employeesDTO.getBirthday())
				.address(employeesDTO.getAddress())
				.phoneNum(employeesDTO.getPhoneNum())
				.gender(employeesDTO.getGender())
				.citizenId(employeesDTO.getCitizenId())
				.build();
		
		employeesRepository.save(employees);
				
		return employeesDTO;
	}
	
	@Override
	public EmployeesDTO modifyEmployees(EmployeesDTO employeesDTO) {
		//hiredate, birthday 수정 안됨
		Employees employees = Employees.builder()
				.empNo(employeesDTO.getEmpNo())
				.firstName(employeesDTO.getFirstName())
				.lastName(employeesDTO.getLastName())
				.hireDate(employeesDTO.getHireDate())
				.mailAddress(employeesDTO.getMailAddress())
				.salary(employeesDTO.getSalary())
				.deptInfo(deptInfoRepository.findById(employeesDTO.getDeptNo()).get())
				.job(jobRepository.findById(employeesDTO.getJobNo()).get())
				.birthday(employeesDTO.getBirthday())
				.address(employeesDTO.getAddress())
				.phoneNum(employeesDTO.getPhoneNum())
				.gender(employeesDTO.getGender())
				.citizenId(employeesDTO.getCitizenId())
				.build();
		
		employeesRepository.save(employees);
				
		return employeesDTO;
	}

	@Override
	public void deleteEmployees(Long empNo) {

		employeesRepository.delete(employeesRepository.findById(empNo).get());
		
	}

	@Override
	public Page<EmployeesDTO> getEmployeesListPage(PageRequestDTO pageRequestDTO) {
		
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("empNo").ascending());

		Page<EmployeesDTO> page = employeesRepository.getEmployeesList(pageable);
		return page;
	}

	@Override
	public List<?> getOne(EmployeesDTO employeesDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
