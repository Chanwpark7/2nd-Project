package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.EmployeesImageDTO;
import com.fullstack.springboot.service.EmployeesImageService;
import com.fullstack.springboot.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/empImage")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeesImageController {
	
	private final FileUtil fileUtil;
	private final EmployeesImageService employeesImageService;
	
	@PostMapping(path = "", consumes = { "multipart/form-data;charset=UTF-8"})
	public long register(@RequestBody MultipartFile files){
		log.info("reg " + files);
		List<String> savedName = null;
		
//		List<MultipartFile> files = dto.getFiles();
//		savedName = fileUtil.attachFiles(dto.getFiles());
//		
//		employeesImageService.register(dto);
//		
//		return dto.getEmpImgNo();
		
		return 1L;
	}
	
	@GetMapping("/read/{empNo}")
	public EmployeesImageDTO read(@PathVariable("empNo")long empNo) {
		log.warn("여기 실행됨"); //나옴
		return employeesImageService.getOne(empNo);
	}
	

}
