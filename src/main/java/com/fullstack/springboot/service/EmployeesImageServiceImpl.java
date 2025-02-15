package com.fullstack.springboot.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.EmployeesImageDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.EmployeesImage;
import com.fullstack.springboot.repository.EmployeesImageRepository;
import com.fullstack.springboot.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployeesImageServiceImpl implements EmployeesImageService {
	
	private final EmployeesImageRepository employeesImageRepository;
	private final EmployeesRepository employeesRepository;
	
	@Override
	public Long register(EmployeesImageDTO imageDTO) {
		System.out.println("service =-------------------------------");
		log.error(imageDTO); //여기까지 잘 나옴
		EmployeesImage image = dtoToEntity(imageDTO);
		System.out.println(image);
		image.setEmployees(employeesRepository.findById(imageDTO.getEmpNo()).get());
		EmployeesImage res = employeesImageRepository.save(image); 
		System.out.println("성공");
		System.out.println("222222service =-------------------------------");
		return res.getEmpImgNo();
	}

	@Override
	public EmployeesImageDTO getOne(long empNo) {
		EmployeesImage employeesImage = employeesImageRepository.getOneEmpImg(empNo);
	
		Employees employees = employeesImage.getEmployees();
		return entityToDto(employeesImage, employees);
	
	}

}
