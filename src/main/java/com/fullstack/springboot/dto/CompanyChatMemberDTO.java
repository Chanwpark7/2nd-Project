package com.fullstack.springboot.dto;

import com.fullstack.springboot.entity.CompanyChatMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyChatMemberDTO {
	
	//private long CompanyChatMemberNo;
	private long empNo;
	private String chatNo;
	
	public CompanyChatMemberDTO(CompanyChatMember cm) {
		//this.CompanyChatMemberNo = cm.getCompanyChatMemberNo();
		this.empNo = cm.getEmployees().getEmpNo();
		this.chatNo = cm.getCompanyChat().getChatNo();
	}

}
