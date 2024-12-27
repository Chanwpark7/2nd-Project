package com.fullstack.springboot.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.EmployeesAuthDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	
	
	private final EmployeesRepository employeesRepository;
	
	@Override
	public UserDetails loadUserByUsername(String empNo) throws UsernameNotFoundException {
		log.info("loadUserByUsername start");
		
		//EmployeesAuthDTO authDto = new EmployeesAuthDTO(employeesRepository.findById(null), null)
		
		return null;
	}

}
