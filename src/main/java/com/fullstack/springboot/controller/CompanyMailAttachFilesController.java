package com.fullstack.springboot.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.entity.CompanyMailAttachFiles;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.service.CompanyMailAttachFilesService;
import com.fullstack.springboot.service.CompanyMailReceivedService;
import com.fullstack.springboot.service.CompanyMailService;
import com.fullstack.springboot.util.FileUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2

@RequestMapping("${com.fullstack.springboot.company-url}")
public class CompanyMailAttachFilesController {
	
	@Autowired
	private CompanyMailAttachFilesService companyMailAttachFilesService;
	

	@Value("${com.fullstack.springboot.uploadPath}")
	private String uploadPath;
	
	@GetMapping("/mail/filelist/{mailNo}")
	public List<List<String>> getFileList(@PathVariable("mailNo") Long mailNo){
		System.out.println(mailNo);
		List<List<String>> fileList = new ArrayList<List<String>>();
		List<CompanyMailAttachFiles> enList = companyMailAttachFilesService.getEntityList(mailNo);
		System.out.println(enList);
		System.out.println(mailNo);
		enList.forEach((item)->{
			System.out.println(item);
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(item.getAttachOriginName());
			temp.add(item.getAttachUUID());
			fileList.add(temp);
		});
		
		
		return fileList;
	}
	@GetMapping("/mail/fileget/{fileName}")
	public void getMethodName(@PathVariable("fileName") String param,HttpServletResponse resp) {
		System.out.println(param);
		CompanyMailAttachFiles cmaf = companyMailAttachFilesService.getOne(param);
		File diskItem = new File(uploadPath + File.separator + cmaf.getAttachUUID());
		System.out.println(diskItem);
		try {
			byte[] fileByte = Files.readAllBytes(diskItem.toPath());
			resp.setContentType("application/octet-stream");
	        resp.setContentLength(fileByte.length);
	        resp.setHeader("Content-Disposition", 
	             "attachment; filename=" + cmaf.getAttachOriginName());
	        int BUFFER_SIZE = 4096;

	        byte[] buffer = new byte[BUFFER_SIZE];
	        
	        InputStream in = new ByteArrayInputStream(fileByte);
	        OutputStream out = resp.getOutputStream();
	        
	        while(in.read(buffer,0,BUFFER_SIZE) != -1) {
	        	out.write(buffer,0,BUFFER_SIZE);
	        }
	        out.flush();
	        
		}catch(Exception e) {
			
		}
		
		
//		System.out.println(diskItem);
//		try {
//			InputStream is = new FileInputStream(diskItem);
//			resp.setContentType("application/download");
//			resp.setHeader("Content-disposition", "attachment;filename=\"" + diskItem + "\"");
//			IOUtils.copy(is,resp.getOutputStream());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String conTy = null;
//		try {
//			conTy = Files.probeContentType(diskItem.toPath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HttpHeaders hthead = new HttpHeaders();
//		hthead.setContentDisposition(ContentDisposition.builder("attachment").filename(param).build());
//		hthead.add(hthead.CONTENT_TYPE, conTy);
//		
//		
//		try {
//			return new ResponseEntity<byte[]>(new InputStreamResource(new FileInputStream(diskItem)).getContentAsByteArray(),null,HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return null;
//		}
	}
	

}
