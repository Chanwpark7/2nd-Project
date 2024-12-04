package com.fullstack.springboot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyCloud extends BaseEntity{

	@Id
	private String fileUUID;
	
	private String fileOriginName;
	
	private String lastModEmployee;
	
	private long viewCount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
	
	@OneToMany(fetch = FetchType.LAZY)
	@Builder.Default
	private List<Employees> employeeAuth = new ArrayList<Employees>();
}
