package com.fullstack.springboot.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Password;

import lombok.Data;

@Data
public class EmployeesAuthDTO extends User{
	
	private String email;
	private String password;
	
	Set<String> roleSet = new HashSet<String>();
	
	public EmployeesAuthDTO(Employees emp, Password pwd) {
		super(emp.getMailAddress(), pwd.getPassword(),
				emp.getRoleSet().stream()
					.map(str -> new SimpleGrantedAuthority("ROLE-"+str))
					.collect(Collectors.toList()));
		
	}
}
