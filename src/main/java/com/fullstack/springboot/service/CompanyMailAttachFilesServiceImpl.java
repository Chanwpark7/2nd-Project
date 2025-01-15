package com.fullstack.springboot.service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.mail.dto.CompanyMailAttachFilesDTO;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;
import com.fullstack.springboot.repository.CompanyMailAttachFilesRepository;
import com.fullstack.springboot.util.FileUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CompanyMailAttachFilesServiceImpl implements CompanyMailAttachFilesService {
	
	@Autowired
	CompanyMailAttachFilesRepository companyMailAttachFilesRepository;
	
	@Override
	public String register(CompanyMailDTO targetMail, List<String> savedNames) {
		
		List<MultipartFile> oriFiles = targetMail.getFiles();
		List<CompanyMailAttachFilesDTO> dtos = new ArrayList<CompanyMailAttachFilesDTO>();
		Iterator<String> it = savedNames.iterator();
		
		for(MultipartFile ori: oriFiles) {
			dtos.add(CompanyMailAttachFilesDTO.builder()
												.mailNo(targetMail.getMailNo())
												.attachOriginName(ori.getOriginalFilename())
												.attachUUID(it.next())
												.build());
		}
		for(CompanyMailAttachFilesDTO dto: dtos) {
			companyMailAttachFilesRepository.save(attachDtoToEntity(dto));
		}
		
		return null;
	}
}
