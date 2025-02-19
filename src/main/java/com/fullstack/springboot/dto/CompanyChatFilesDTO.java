package com.fullstack.springboot.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.entity.CompanyChat;
import com.fullstack.springboot.entity.CompanyChatFiles;


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

	
	private List<MultipartFile> files = new ArrayList<>();
	
	private List<String> uploadFileNames = new ArrayList<>();
	
	public CompanyChatFilesDTO(CompanyChatFiles cf) {
		this.chatFileNo = cf.getChatFileNo();
		this.chatNo = cf.getCompanyChat().getChatNo();
		this.attachOriginName = cf.getAttachOriginName();
		this.attachUUID = cf.getAttachUUID();
	}
	

}
