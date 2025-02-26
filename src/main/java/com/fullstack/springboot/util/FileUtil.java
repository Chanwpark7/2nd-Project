package com.fullstack.springboot.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class FileUtil {

	  @Value("${com.fullstack.springboot.uploadPath}")
	  private String uploadPath;

	  @PostConstruct
	  public void init() {
	    File tempFolder = new File(uploadPath);
	    if(tempFolder.exists() == false) {
	      tempFolder.mkdirs();
	    }
	    uploadPath = tempFolder.getAbsolutePath();
	  }
	  
	  public List<String> attachFiles(List<MultipartFile> oriFiles){
		log.info("attachFiles start");
		if(oriFiles == null || oriFiles.size() < 1) return null;
		
		List<String> saveNames = new ArrayList<String>();
		  
		for(MultipartFile file : oriFiles) {
			String genUuid = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
			Path path = Path.of(uploadPath, genUuid);
				
			try {
				Files.copy(file.getInputStream(), path);
				saveNames.add(genUuid);
			} catch (Exception e) {
				e.getMessage();
			}
			
		}
		
		return saveNames;
	  }
	  
	  public ResponseEntity<Resource> getFile(String fileName) {
		    
		    Resource resource = new FileSystemResource(uploadPath+ File.separator + fileName);

		    if(!resource.exists()) {

		      resource = new FileSystemResource(uploadPath+ File.separator + "default.jpeg");
		    
		    }

		    HttpHeaders headers = new HttpHeaders();

		    try{
		        headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
		    } catch(Exception e){
		        return ResponseEntity.internalServerError().build();
		    }
		    return ResponseEntity.ok().headers(headers).body(resource);
		  }

	  public ResponseEntity<Resource> getReportFile(String fileName) {
		    
		    Resource resource = new FileSystemResource(uploadPath+ File.separator + fileName);

		    if(!resource.exists()) {

		      resource = new FileSystemResource(uploadPath+ File.separator + "default.jpeg");
		    
		    }

		    HttpHeaders headers = new HttpHeaders();

		    try{
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
		                  .filename(fileName, Charset.forName("UTF-8"))
		                  .build();
		        headers.setContentDisposition(contentDisposition);
		    } catch(Exception e){
		        return ResponseEntity.internalServerError().build();
		    }
		    return ResponseEntity.ok().headers(headers).body(resource);
		  }

	  
	  public String getUploadPath() { //uploadPath 경로 반환
		  return uploadPath;
	  }
	  
	  public void deleteFile(String uuid) {
		  Path path = Path.of(uploadPath, uuid);
		  try {
			Files.delete(path);
			System.out.println("삭제성공~");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	  }
}
