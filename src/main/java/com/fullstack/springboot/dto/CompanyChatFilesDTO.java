package com.fullstack.springboot.dto;

import com.fullstack.springboot.entity.CompanyChat;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyChatFilesDTO {

	private long chatFileNo;
	
	private String chatNo; 
	
	private String attachOriginName;
	
	private String attachUUID;
}
