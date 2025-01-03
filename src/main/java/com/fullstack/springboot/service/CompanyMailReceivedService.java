package com.fullstack.springboot.service;

import java.util.List;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.CompanyMail;

public interface CompanyMailReceivedService {
	
	void register(CompanyMail mail,List<EmployeesDTO> emps);

}
