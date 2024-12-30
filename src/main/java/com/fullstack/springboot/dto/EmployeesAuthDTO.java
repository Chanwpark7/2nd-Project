package com.fullstack.springboot.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fullstack.springboot.entity.Employees;

import lombok.Data;

@Data
public class EmployeesAuthDTO extends User{
	
	private String email;
	private String password;
	
	Set<String> roleSet = new HashSet<String>();
	
	public EmployeesAuthDTO(Employees emp) {
		super(emp.getMailAddress(), emp.getPassword(),
				emp.getRoleSet().stream()
					.map(str -> new SimpleGrantedAuthority("ROLE-"+str))
					.collect(Collectors.toList()));
		email = emp.getMailAddress();
		password = emp.getPassword();
		roleSet = emp.getRoleSet().stream().map(str -> str.name()).collect(Collectors.toSet());
		
	}
	public Map<String, Object> getClaims(){
		Map<String, Object> claim = new HashMap<String , Object>();
		
		claim.put("email", email);
		claim.put("password", password);
		claim.put("roleSet", roleSet);
		
		return claim;
	}
}
