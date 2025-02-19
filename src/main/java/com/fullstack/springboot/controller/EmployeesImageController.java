package com.fullstack.springboot.controller;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.EmployeesImageDTO;
import com.fullstack.springboot.entity.EmployeesImage;
import com.fullstack.springboot.repository.EmployeesImageRepository;

import com.fullstack.springboot.service.EmployeesImageService;
import com.fullstack.springboot.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;



@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/empImage")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeesImageController {
	

	@Value("${com.fullstack.springboot.uploadPath}")
	private String uploadPath;
	
	private final FileUtil fileUtil;
	private final EmployeesImageService employeesImageService;
	public final EmployeesImageRepository employeesImageRepository;
	
	@PostMapping(path = "/{empNo}", consumes = { "multipart/form-data;charset=UTF-8"})
	public long register(EmployeesImageDTO dto){
		log.info("reg " + dto);
		List<MultipartFile> files = dto.getFiles();
		List<String> uploadFileNames = fileUtil.attachFiles(files);
		
		dto.setUploadFileNames(uploadFileNames);
		return employeesImageService.register(dto);
	}
	
	
	

	@GetMapping("/read/{empNo}")
	public EmployeesImageDTO read(@PathVariable("empNo")long empNo) {
		log.warn("여기 실행됨"); //나옴
		return employeesImageService.getOne(empNo);
	}
	


	@GetMapping("/view/{uuid}")
	public ResponseEntity<Resource> viewImg(@PathVariable(name="uuid") String uuid) {
	    String filePath = uploadPath + File.separator + uuid;
	    System.out.println("uuu =>   " + uuid);
	    
	    if (!new File(filePath).exists()) {
	        return ResponseEntity.notFound().build();
	    }
	    
	     System.out.println("~~~~~~~~~~~` " + fileUtil.getFile(filePath));
	   
	    return fileUtil.getFile(uuid);
	}
	
	@PutMapping("/mod/{empNo}")
	public ResponseEntity<String> modImg(@PathVariable("empNo") long empNo, 
	                                     @RequestParam("files") List<MultipartFile> files,
	                                     EmployeesImageDTO employeesImageDTO) {

	    if (files.isEmpty()) {
	        return ResponseEntity.badRequest().body("파일없음");
	    }

	    try {
	        List<String> uploadFileNames = fileUtil.attachFiles(files);

	        employeesImageService.update(empNo, uploadFileNames,employeesImageDTO);

	        return ResponseEntity.ok("수정 성공");
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return null;
	    }
	}

	
	@DeleteMapping("/{empNo}")
	public ResponseEntity<String> deleteEmployeeImage(@PathVariable("empNo") long empNo) {
	    EmployeesImage existingImage = employeesImageRepository.getOneEmpImg(empNo);

	    if (existingImage != null) {
	        fileUtil.deleteFile(existingImage.getUuid());

	        employeesImageRepository.delete(existingImage);

	        return ResponseEntity.ok("이미지 삭제 완료");
	    } else {
	        System.out.println("삭제 실패");
	        return null;
	    }
	}




}
