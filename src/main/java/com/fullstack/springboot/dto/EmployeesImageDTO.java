package com.fullstack.springboot.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.entity.Employees;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesImageDTO {

	private long empImgNo;
	
	private long empNo;
	
	private String url;
	
	private List<MultipartFile> files = new ArrayList<>();
	
	private List<String> uploadFileNames = new ArrayList<>();
}
