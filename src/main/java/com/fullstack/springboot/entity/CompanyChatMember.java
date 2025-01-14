package com.fullstack.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyChatMember { //chat이랑 emp 중간 테이블.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long CompanyChatMemberNo;

	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_chat_chat_no") //이거 안주면 자꾸 company_chat_company_chat_member 쿼리가 이거랑 맵핑됨.company_chat_member 이거랑 되어야하는디;
    private CompanyChat companyChat;  
	
}
