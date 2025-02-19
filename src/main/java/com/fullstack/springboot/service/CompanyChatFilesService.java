package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatFilesDTO;
import com.fullstack.springboot.entity.CompanyChat;
import com.fullstack.springboot.entity.CompanyChatFiles;

public interface CompanyChatFilesService {

	//public void sendFile(String chatNo, long empNo, MultipartFile file);
	public void sendFile(CompanyChatFilesDTO companyChatFilesDTO);
	
	default CompanyChatFiles dtoToEntity(CompanyChatFilesDTO dto) {
		return CompanyChatFiles.builder()
				.chatFileNo(dto.getChatFileNo())
				.companyChat(CompanyChat.builder().chatNo(dto.getChatNo()).build())
				.attachOriginName(dto.getAttachOriginName())
				.attachUUID(dto.getUploadFileNames().get(0))
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
