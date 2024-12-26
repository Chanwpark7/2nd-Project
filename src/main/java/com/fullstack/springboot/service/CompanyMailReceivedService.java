package com.fullstack.springboot.service;

import java.util.List;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.Employees;

public interface CompanyMailReceivedService {
	
	void register(CompanyMail mail,List<Employees> emps);

}
