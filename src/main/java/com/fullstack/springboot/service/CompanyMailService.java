package com.fullstack.springboot.service;

import java.util.List;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;

public interface CompanyMailService {
	
	long register(CompanyMailDTO dto);
	
	CompanyMailDTO read(Long mailNo);
	
	List<CompanyMailDTO> getList(Long memberNo);
	
	String deleteMail(Long memberNo);
	
	String modifyMailCat(Long mailNo);
	
	default CompanyMail mailDtoToEntity(CompanyMailDTO dto) {
		return CompanyMail.builder()
							.mailNo(dto.getMailNo())
							.sendDate(dto.getSendDate())
							.contents(dto.getContents())
							.title(dto.getTitle())
							.mailCategory(dto.getMailCategory())
							.mailFileOriginName(dto.getMailFileOriginName())
							.mailFileUUID(dto.getMailFileUUID())
							.employees(dto.getEmployees())
							.sender(dto.getSender())
							.build();
	}
	default CompanyMailDTO mailEntityToDto(CompanyMail entity) {
		return CompanyMailDTO.builder()
								.mailNo(entity.getMailNo())
								.sendDate(entity.getSendDate())
								.contents(entity.getContents())
								.title(entity.getTitle())
								.mailFileOriginName(entity.getMailFileOriginName())
								.mailFileUUID(entity.getMailFileUUID())
								.mailCategory(entity.getMailCategory())
								.employees(entity.getEmployees())
								.sender(entity.getSender())
								.build();
	}
}
