package com.fullstack.springboot.mail.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.entity.Employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyMailDTO {
	
	private long mailNo;
	private LocalDateTime sendDate;
	private String contents;
	private String title;
	private String mailFileOriginName;
	private String mailFileUUID;
	private String mailCategory;
	
	private Employees sender;
	
	private List<Employees> employees;
	private List<MultipartFile> files;
	

}
