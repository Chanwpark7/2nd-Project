package com.fullstack.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.io.monitor.FileEntry;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatFilesDTO;

import com.fullstack.springboot.entity.CompanyChat;
import com.fullstack.springboot.entity.CompanyChatFiles;
import com.fullstack.springboot.repository.CompanyChatFilesRepository;
import com.fullstack.springboot.repository.CompanyChatRepository;
import com.fullstack.springboot.util.FileUtil;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CompanyChatFilesServiceImpl implements CompanyChatFilesService {


    private final CompanyChatFilesRepository companyChatFilesRepository;
    private final FileUtil fileUtil;
    private final CompanyChatRepository companyChatRepository;
	@Override
	public void sendFile(CompanyChatFilesDTO companyChatFilesDTO) {
		CompanyChatFiles chatFiles = dtoToEntity(companyChatFilesDTO);
		chatFiles.setCompanyChat(companyChatRepository.findByChatNo(companyChatFilesDTO.getChatNo()));
		CompanyChatFiles res = companyChatFilesRepository.save(chatFiles);

	} 

//    @Override
//    public void sendFile(String chatNo, long empNo, MultipartFile file) {
//        if (file == null || file.isEmpty()) {
//            System.out.println("파일없음");
//            return;
//        }
//
//        
//        List<String> savedFileNames = fileUtil.attachFiles(List.of(file));
//        String savedFileName = savedFileNames.get(0);
//
//        CompanyChat companyChat = companyChatRepository.findByChatNo(chatNo);
//        
//        if (companyChat == null) {
//            System.out.println("해당 채팅방을 찾을 수 없습니다.");
//            return;
//        }
//
//        CompanyChatFiles companyChatFiles = CompanyChatFiles.builder()
//            .companyChat(companyChat) 
//            .attachUUID(savedFileName) 
//            .build();
//
//        companyChatFilesRepository.save(companyChatFiles);
//        System.out.println("업로드된 파일: " + savedFileName);
//    }
}

	

