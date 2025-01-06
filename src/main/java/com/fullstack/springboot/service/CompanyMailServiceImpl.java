package com.fullstack.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;
import com.fullstack.springboot.mail.dto.CompanyMailListRequestDTO;
import com.fullstack.springboot.mail.dto.CompanyMailResponseDTO;
import com.fullstack.springboot.repository.CompanyMailAttachFilesRepository;
import com.fullstack.springboot.repository.CompanyMailReceivedRepository;
import com.fullstack.springboot.repository.CompanyMailRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CompanyMailServiceImpl implements CompanyMailService {
	
	@Autowired
	CompanyMailRepository companyMailRepository;
	@Autowired
	CompanyMailReceivedRepository companyMailReceivedRepository;
	
	@Override
	public long register(CompanyMailDTO dto) {
		
		CompanyMail cm = mailDtoToEntity(dto);
		companyMailRepository.save(cm);
		
		return cm.getMailNo();
	}

	@Override
	public CompanyMailDTO read(Long mailNo) {
		// TODO Auto-generated method stub
		Optional<CompanyMail> obj = companyMailRepository.findById(mailNo);
		CompanyMailDTO dto = null;
		if(obj.isPresent()) {
			dto = this.mailEntityToDto(obj.get());
		}
		return dto;
		
	}

	@Override
	public List<CompanyMailDTO> getList(Long memberNo) {
		
		List<CompanyMail> list = companyMailRepository.getList(memberNo);
		List<CompanyMailDTO> dtoList = new ArrayList<CompanyMailDTO>();
		
		for(CompanyMail obj : list) {
			dtoList.add(this.mailEntityToDto(obj));
		}
		
		return dtoList;
	}
	
	@Override
	public CompanyMailResponseDTO getListPage(String email, Pageable pageable, CompanyMailListRequestDTO reqDTO){
		System.out.println("getListPage");
		Page<CompanyMail> result = companyMailRepository.getListPage(email, reqDTO.getCat() , pageable);
		
		List<CompanyMailDTO> dtoList = result.get()
				.map(obj -> this.mailEntityToDto(obj))
				.collect(Collectors.toList());
		
		long totalCnt = result.getTotalElements();
	
		return CompanyMailResponseDTO.builder()
					.dtoList(dtoList)
					.totalCnt(totalCnt)
					.requestDTO(reqDTO)
					.build();
	}
	
	@Override
	public CompanyMailResponseDTO getMySendListPage(String email, Pageable pageable, CompanyMailListRequestDTO reqDTO) {
		System.out.println("getMySendListPage");
		Page<CompanyMail> result = companyMailRepository.getMySendListPage(email, pageable);
		
		List<CompanyMailDTO> dtoList = result.get()
				.map(obj -> this.mailEntityToDto(obj))
				.collect(Collectors.toList());
		
		long totalCnt = result.getTotalElements();
	
		return CompanyMailResponseDTO.builder()
					.dtoList(dtoList)
					.totalCnt(totalCnt)
					.requestDTO(reqDTO)
					.build();
		
	}

	@Override
	public String deleteMail(Long mailNo) {
		Optional<CompanyMail> obj = companyMailRepository.findById(mailNo);
		if(obj.isPresent()) {
			obj.get().setMailCategory("isDeleted");
			return "delete_success";
		}
		return "delete_fail";
	}

	@Override
	public String modifyMailCat(Long sendEmpNo,String cat) {
		Optional<CompanyMail> obj =  companyMailRepository.findById(sendEmpNo);
		if(obj.isPresent()) {
			obj.get().setMailCategory(cat);
			return "modCat_success";
		}
		return "modCat_fail";
	}
	
	
	
}
