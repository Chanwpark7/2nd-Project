package com.fullstack.springboot.service;

import java.util.List;

import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatFilesDTO;
import com.fullstack.springboot.entity.CompanyChat;
import com.fullstack.springboot.entity.CompanyChatFiles;

public interface CompanyChatFilesService {

	String register(CompanyChatDTO chatDTO, List<String> saveNames);
	
	default CompanyChatFiles dtoToEntity(CompanyChatFilesDTO dto) {
		return CompanyChatFiles.builder()
				.chatFileNo(dto.getChatFileNo())
				.companyChat(CompanyChat.builder().chatNo(dto.getChatNo()).build())
				.attachOriginName(dto.getAttachOriginName())
				.attachUUID(dto.getAttachUUID())
				.build();
	}
	
	default CompanyChatFilesDTO entityToDto(CompanyChatFiles entity) {
		return CompanyChatFilesDTO.builder()
				.chatFileNo(entity.getChatFileNo())
				.chatNo(entity.getCompanyChat().getChatNo())
				.attachOriginName(entity.getAttachOriginName())
				.attachUUID(entity.getAttachUUID())
				.build();
	}
}
