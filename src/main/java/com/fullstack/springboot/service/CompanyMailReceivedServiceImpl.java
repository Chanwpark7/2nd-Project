package com.fullstack.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.CompanyMailReceived;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.mail.dto.CompanyMailReceivedDTO;
import com.fullstack.springboot.repository.CompanyMailReceivedRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CompanyMailReceivedServiceImpl implements CompanyMailReceivedService {
	
	@Autowired
	CompanyMailReceivedRepository companyMailReceivedRepository;
	
	@Override
	public void register(CompanyMail mail, List<EmployeesDTO> emps) {
		// TODO Auto-generated method stub
		
		for(EmployeesDTO emp : emps) {
		CompanyMailReceived received =  CompanyMailReceived.builder().mail(mail).employee(Employees.builder().empNo(emp.getEmpNo()).build()).build();
		companyMailReceivedRepository.save(received);
		}
	}

	@Override
	public CompanyMailReceivedDTO getReceivedByMailNo(Long mailNo) {
		List<CompanyMailReceived> entity = companyMailReceivedRepository.getReceiveds(mailNo);
		List<EmployeesDTO> empList = new ArrayList<EmployeesDTO>();
		for(CompanyMailReceived obj:entity) {
			Employees emp = obj.getEmployee();
			empList.add(EmployeesDTO.builder()
							.empNo(emp.getEmpNo())
							.jobNo(emp.getJob().getJobNo())
							.deptNo(emp.getDeptInfo().getDeptNo())
							.firstName(emp.getFirstName())
							.lastName(emp.getLastName())
							.build());
		}
		
		CompanyMailReceivedDTO dto = CompanyMailReceivedDTO.builder()
										.mailNo(mailNo)
										.empDTO(empList)
										.build();
		
		return dto;
		}

}
