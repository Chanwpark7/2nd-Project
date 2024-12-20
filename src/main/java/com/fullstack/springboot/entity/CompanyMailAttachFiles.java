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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyMailAttachFiles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fileNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CompanyMail companyMail;
	
	private String attachOriginName;
	private String attachUUID;
	
}
