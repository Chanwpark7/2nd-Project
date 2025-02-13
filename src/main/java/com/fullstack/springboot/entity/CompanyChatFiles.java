package com.fullstack.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyChatFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long chatFileNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CompanyChat companyChat; 
	
	private String attachOriginName;
	
	private String attachUUID;
}
