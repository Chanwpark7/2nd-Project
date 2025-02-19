package com.fullstack.springboot.service;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.EmployeesImageDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.EmployeesImage;
import com.fullstack.springboot.repository.EmployeesImageRepository;
import com.fullstack.springboot.repository.EmployeesRepository;

import com.fullstack.springboot.util.FileUtil;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployeesImageServiceImpl implements EmployeesImageService {
	
	private final EmployeesImageRepository employeesImageRepository;
	private final EmployeesRepository employeesRepository;

	private final FileUtil fileUtil;
	
	@Override
	public Long register(EmployeesImageDTO imageDTO) {
		EmployeesImage image = dtoToEntity(imageDTO);
		image.setEmployees(employeesRepository.findById(imageDTO.getEmpNo()).get());
		EmployeesImage res =  employeesImageRepository.save(image);
		return res.getEmpImgNo();
	}
	
	



	@Override
	public EmployeesImageDTO getOne(long empNo) {
		EmployeesImage employeesImage = employeesImageRepository.getOneEmpImg(empNo);

		
		if(employeesImage == null) {
			System.out.println("이미지 null");
		}
		

		Employees employees = employeesImage.getEmployees();
		return entityToDto(employeesImage, employees);
	
	}

	
	
	
	@Override
	public void update(long empNo, List<String> newFileNames, EmployeesImageDTO employeesImageDTO) {
	    EmployeesImage existingImage = employeesImageRepository.getOneEmpImg(empNo);

	    if (existingImage != null) {
	        log.info("기존 사진 삭제 " + existingImage.getUuid());
	        fileUtil.deleteFile(existingImage.getUuid());  
	        employeesImageRepository.delete(existingImage); 
	    }

	    EmployeesImage newImage = new EmployeesImage();
	    newImage.setUuid(newFileNames.get(0));  
	    newImage.setUrl(employeesImageDTO.getUrl());

	    Employees employee = employeesRepository.findById(empNo).get();
	    newImage.setEmployees(employee);

	    employeesImageRepository.save(newImage);  
	    log.info("이미지 수정 완 service");
	}




}
