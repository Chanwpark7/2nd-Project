package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.CompanyMailReceived;
import com.fullstack.springboot.entity.Employees;
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
	public void register(CompanyMail mail, List<Employees> emps) {
		// TODO Auto-generated method stub
		
		for(Employees emp : emps) {
		CompanyMailReceived received =  CompanyMailReceived.builder().mail(mail).employee(emp).build();
		companyMailReceivedRepository.save(received);
		}
	}

}
