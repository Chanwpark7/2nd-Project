package com.fullstack.springboot.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fullstack.springboot.entity.Employees;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class EmployeesAuthDTO extends User{
	
	private String email;
	private String password;
	private Long empNo;
	private Long deptNo;
	Set<String> roleSet = new HashSet<String>();
	
	public EmployeesAuthDTO(Employees emp) {
		super(emp.getMailAddress(), emp.getPassword(),
				emp.getRoleSet().stream()
					.map(str -> new SimpleGrantedAuthority("ROLE-"+str))
					.collect(Collectors.toList()));
		email = emp.getMailAddress();
		empNo = emp.getEmpNo();
		deptNo = emp.getDeptInfo().getDeptNo();
		password = emp.getPassword();
		roleSet = emp.getRoleSet().stream().map(str -> str.name()).collect(Collectors.toSet());
		
	}
	public EmployeesAuthDTO(String email, String password,Long empNo, Long deptNo, Set<String> roleSet) {
		super(email, password,
				roleSet.stream()
					.map(str -> new SimpleGrantedAuthority("ROLE-"+str))
					.collect(Collectors.toList()));
		this.email = email;
		this.password = password;
		this.roleSet = roleSet;
		this.empNo = empNo;
		this.deptNo = deptNo;
		
	}
	public Map<String, Object> getClaims(){
		Map<String, Object> claim = new HashMap<String , Object>();
		
		claim.put("email", email);
		claim.put("password", password);
		claim.put("roleSet", roleSet);
		claim.put("empNo", empNo);
		claim.put("deptNo", deptNo);
		
		return claim;
	}
}
