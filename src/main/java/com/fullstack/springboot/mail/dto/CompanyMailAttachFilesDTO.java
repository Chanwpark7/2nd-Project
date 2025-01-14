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
public class CompanyMailAttachFilesDTO {
	private long fileNo;
	private long mailNo;
	private String attachOriginName;
	private String attachUUID;
}
