package com.fullstack.springboot.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
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
	
	private final ModelMapper modelMapper;
	
	private final EmployeesRepository employeesRepository;
	private final DeptInfoRepository deptInfoRepository;
	private final JobRepository jobRepository;
	private final PasswordEncoder pwEncoder;
	
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
				.password(pwEncoder.encode(employeesDTO.getPassword()))
				.build();
		
		employeesRepository.save(employees);
		
		Long empNo = Long.parseLong(employeesRepository.getMaxEmpNo().toString());

		
		Employees emp = employeesRepository.findById(empNo).get();
		
		EmployeesDTO dto = EmployeesDTO.builder()
				.empNo(emp.getEmpNo())
				.firstName(emp.getFirstName())
				.lastName(emp.getLastName())
				.hireDate(emp.getHireDate())
				.mailAddress(emp.getMailAddress())
				.salary(emp.getSalary())
				.deptNo(emp.getDeptInfo().getDeptNo())
				.jobNo(emp.getJob().getJobNo())
				.birthday(emp.getBirthday())
				.address(emp.getAddress())
				.phoneNum(emp.getPhoneNum())
				.gender(emp.getGender())
				.citizenId(emp.getCitizenId())
				.build();
		
		return dto;
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
				.password(pwEncoder.encode(employeesDTO.getPassword()))
				.build();
		
		employeesRepository.save(employees);
				
		return employeesDTO;
	}

	@Override
	public void deleteEmployees(Long empNo) {

		employeesRepository.delete(employeesRepository.findById(empNo).get());
		
	}

	@Override
	public PageResponseDTO<EmployeesDTO> getEmployeesListPage(PageRequestDTO pageRequestDTO) {
		
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("empNo").ascending());
		
		Page<EmployeesDTO> page = employeesRepository.getEmployeesList(pageable);
		
		List<EmployeesDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<EmployeesDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}

	@Override
	public EmployeesDTO getOne(Long empNo) {
		
		Optional<Employees> result = employeesRepository.findById(empNo);
		
		Employees emp = result.orElseThrow();

		EmployeesDTO dto = EmployeesDTO.builder()
				.empNo(emp.getEmpNo())
				.firstName(emp.getFirstName())
				.lastName(emp.getLastName())
				.hireDate(emp.getHireDate())
				.mailAddress(emp.getMailAddress())
				.salary(emp.getSalary())
				.deptNo(emp.getDeptInfo().getDeptNo())
				.jobNo(emp.getJob().getJobNo())
				.birthday(emp.getBirthday())
				.address(emp.getAddress())
				.phoneNum(emp.getPhoneNum())
				.gender(emp.getGender())
				.citizenId(emp.getCitizenId())
				.build();
		
		return dto;
	}

	@Override
	public long getDDay(long empNo) {
		Employees employees = employeesRepository.getEmpNo(empNo).orElseThrow();
		LocalDate hirDate = employees.getHireDate();
		LocalDate curDate = LocalDate.now();
		
		return ChronoUnit.DAYS.between(hirDate, curDate);
	}

	@Override
	public PageResponseDTO<EmployeesDTO> getBirthEmp(PageRequestDTO pageRequestDTO) {
	    Pageable pageable = pageRequestDTO.getPageable(Sort.by("empNo").ascending());

	    Page<EmployeesDTO> page = employeesRepository.getEmployeesList(pageable);

	    List<EmployeesDTO> dtoList = page.get().toList();
	    long totalCount = page.getTotalElements();

	    LocalDate nowDate = LocalDate.now();
	    int currentMonth = nowDate.getMonthValue();
	    int currentDay = nowDate.getDayOfMonth(); 

	    List<EmployeesDTO> birthdayEmployees = dtoList.stream()
	            .filter(emp -> {
	                LocalDate birthDate = emp.getBirthday(); 
	                return birthDate != null && birthDate.getMonthValue() == currentMonth && birthDate.getDayOfMonth() == currentDay;
	            })
	            .collect(Collectors.toList());

	    if (birthdayEmployees.isEmpty()) {
	        return null; 
	    }

	    return PageResponseDTO.<EmployeesDTO>withAll()
	            .dtoList(birthdayEmployees)
	            .totalCount((long) birthdayEmployees.size())
	            .pageRequestDTO(pageRequestDTO)
	            .build();
	}


	
	
}
