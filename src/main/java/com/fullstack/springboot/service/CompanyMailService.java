package com.fullstack.springboot.service;

import java.util.List;

import com.fullstack.springboot.mail.dto.CompanyMailDTO;

public interface CompanyMailService {
	
	String register(CompanyMailDTO dto);
	
	CompanyMailDTO read(Long mailNo);
	
	List<CompanyMailDTO> getList(Long memberNo);
	
	String deleteMail(Long memberNo);
	
	String modifyMailCat(Long mailNo);
}
