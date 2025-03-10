package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.CompanyMailAttachFiles;
import com.fullstack.springboot.mail.dto.CompanyMailAttachFilesDTO;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;

public interface CompanyMailAttachFilesService {
	
	public String register(CompanyMailDTO targetMail, List<String> savedNames);
	public List<CompanyMailAttachFiles> getEntityList(Long mailNo);
	public CompanyMailAttachFiles getOne(String fileUuid);
	
	default CompanyMailAttachFiles attachDtoToEntity(CompanyMailAttachFilesDTO dto) {
		return CompanyMailAttachFiles.builder()
										.fileNo(dto.getFileNo())
										.companyMail(CompanyMail.builder().mailNo(dto.getMailNo()).build())
										.attachOriginName(dto.getAttachOriginName())
										.attachUUID(dto.getAttachUUID())
										.build();
	}
	default CompanyMailAttachFilesDTO attachEntityToDto(CompanyMailAttachFiles entity) {
		return CompanyMailAttachFilesDTO.builder()
											.fileNo(entity.getFileNo())
											.mailNo(entity.getCompanyMail().getMailNo())
											.attachOriginName(entity.getAttachOriginName())
											.attachUUID(entity.getAttachUUID())
											.build();
	}
}
