package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;
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
	
	@Override
	public String register(CompanyMailDTO dto) {
		
		
		
		
		
		return null;
	}

	@Override
	public CompanyMailDTO read(Long mailNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyMailDTO> getList(Long memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMail(Long memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modifyMailCat(Long mailNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
