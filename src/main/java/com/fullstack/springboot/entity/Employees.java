package com.fullstack.springboot.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Employees implements Serializable{
	
	//직원 엔티티
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empNo;
	
	private String firstName;
	
	private String lastName;
	
	@CreationTimestamp
	private LocalDate hireDate;
	
	private String mailAddress;
	
	private long salary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private DeptInfo deptInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Job job;
	
	private LocalDate birthday;
	
	private String address;
	
	private String phoneNum;
	
	private String gender;
	
	private String citizenId;
	
	private String password;
	
	public void changePw(String password) {
		this.password = password;
	}
//dev_mail
	
	@Builder.Default
	@ElementCollection(fetch = FetchType.LAZY)
	private Set<CompanyAuth> roleSet = new HashSet<CompanyAuth>();
	
	public void addRole(CompanyAuth role) {
		roleSet.add(role);
	}
	public void resetRole() {
		roleSet.clear();
	}
}
