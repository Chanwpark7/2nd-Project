package com.fullstack.springboot.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;
import com.fullstack.springboot.mail.dto.CompanyMailListRequestDTO;
import com.fullstack.springboot.mail.dto.CompanyMailResponseDTO;

public interface CompanyMailService {
	
	long register(CompanyMailDTO dto);
	
	CompanyMailDTO read(Long mailNo);
	
	List<CompanyMailDTO> getList(Long memberNo);
	
	CompanyMailResponseDTO getListPage(String email, Pageable pageable, CompanyMailListRequestDTO reqDTO);
	
	String deleteMail(Long memberNo);
	
	String modifyMailCat(Long sendEmpNo,String cat);
	
	
	
	default CompanyMail mailDtoToEntity(CompanyMailDTO dto) {
		return CompanyMail.builder()
							.mailNo(dto.getMailNo())
							.sendDate(dto.getSendDate())
							.contents(dto.getContents())
							.title(dto.getTitle())
							.mailCategory(dto.getMailCategory())
							.mailFileOriginName(dto.getMailFileOriginName())
							.mailFileUUID(dto.getMailFileUUID())
							.sender(Employees.builder()
									.empNo(dto.getSender().getEmpNo())
									.build())
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
								.sender(EmployeesDTO.builder()
											.empNo(entity.getSender().getEmpNo())
											.firstName(entity.getSender().getFirstName())
											.lastName(entity.getSender().getLastName())
											.mailAddress(entity.getSender().getMailAddress())
											.deptNo(entity.getSender().getDeptInfo().getDeptNo())
											.build())
								.build();
	}
}
