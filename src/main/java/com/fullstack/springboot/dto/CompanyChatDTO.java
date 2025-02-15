package com.fullstack.springboot.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.entity.CompanyChat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyChatDTO {

	private String chatNo;
	private long empNo;
	private long senderEmpNo;
    private long receiverEmpNo;
    private List<MultipartFile> files;
	
	public CompanyChatDTO(CompanyChat c) {
		this.chatNo = c.getChatNo();
		
		 if (c.getCompanyChatMember() != null && !c.getCompanyChatMember().isEmpty()) {
		        this.empNo = c.getCompanyChatMember().get(0).getEmployees().getEmpNo();
		 } else {
		        this.empNo = -1; 
		    }
	}
	
}
