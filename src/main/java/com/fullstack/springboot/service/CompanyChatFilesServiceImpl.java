package com.fullstack.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatFilesDTO;
import com.fullstack.springboot.repository.CompanyChatFilesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CompanyChatFilesServiceImpl implements CompanyChatFilesService {
	
	private final CompanyChatFilesRepository companyChatFilesRepository;
	
	@Override
	public String register(CompanyChatDTO chatDTO, List<String> saveNames) {
		List<MultipartFile> oriFiles = chatDTO.getFiles();
		List<CompanyChatFilesDTO> dtos = new ArrayList<CompanyChatFilesDTO>();
		Iterator<String> it = saveNames.iterator();
		
		for(MultipartFile ori : oriFiles) {
			dtos.add(CompanyChatFilesDTO.builder()
					.chatNo(chatDTO.getChatNo())
					.attachOriginName(ori.getOriginalFilename())
					.attachUUID(it.next())
					.build());
		}
		
		for(CompanyChatFilesDTO dto : dtos) {
			companyChatFilesRepository.save(dtoToEntity(dto));
		}
		
		return null;
	}
}
