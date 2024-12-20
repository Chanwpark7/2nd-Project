package com.fullstack.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;
import com.fullstack.springboot.service.CompanyMailAttachFilesService;
import com.fullstack.springboot.service.CompanyMailService;
import com.fullstack.springboot.util.FileUtil;
import com.fullstack.springboot.util.URIVariableCrypto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("${com.fullstack.springboot.company-url}")
public class CompanyMailController {

	private final CompanyMailService companyMailService;
	private final CompanyMailAttachFilesService companyMailAttachFilesService;
	private final FileUtil fileUtil;

	@PostMapping(path = "/mail", consumes = { "multipart/form-data;charset=UTF-8"})
	public String regiMail(CompanyMailDTO dto,@RequestParam("sendEmpNo") long sendEmpNo,@RequestParam("receiveEmpNo") long[] receiveEmpNo) {
		System.out.println("company-mail-sendMail");
		
		List<String> savedNames = null;
		long getMailNo = companyMailService.register(dto);
		dto.setMailNo(getMailNo);
		savedNames = fileUtil.attachFiles(dto.getFiles());
		String tranMsg = companyMailAttachFilesService.register(dto,savedNames);
		
		return tranMsg;
	}
	@PostMapping("/mail/attach")
	public String attachMail(@RequestBody CompanyMailDTO dto) {
		System.out.println("company-mail-attachMail");
		
		List<MultipartFile> files = dto.getFiles();
		String mailTitle = files.size()>1 ? files.get(0).getOriginalFilename():(files.get(0).getOriginalFilename() + "and " + String.valueOf(files.size()-1) + "more...");
		CompanyMail.builder().contents(dto.getContents())
								.title(mailTitle)
								.employees(dto.getEmployees())
								.mailCategory(dto.getMailCategory())
								
								.build();
		
		String tranMsg = null;//companyMailService.register(dto);
		
		return tranMsg;
	}
	
	@GetMapping("/mail/r/{mailNo}")
	public CompanyMailDTO readMail(@PathVariable("mailNo") String mailNo) {
		System.out.println("company-mail-readMail");
		
		CompanyMailDTO dto = companyMailService.read(Long.valueOf(URIVariableCrypto.decodePathVariable(mailNo)));
		
		return dto;
	}
	@GetMapping("/mail/l/{memberNo}")
	public List<CompanyMailDTO> listMail(@PathVariable("memberNo") String memberNo){
		System.out.println("company-mail-listMail");
		
		List<CompanyMailDTO> mailList = companyMailService.getList(Long.valueOf(URIVariableCrypto.decodePathVariable(memberNo)));
		
		return mailList;
	}
	@PutMapping("/mail/m/{mailNo}")
	public String modCat(@PathVariable("mailNo") String mailNo) {
		System.out.println("company-mail-modCat");
		
		String tranMsg = companyMailService.modifyMailCat(Long.valueOf(URIVariableCrypto.decodePathVariable(mailNo)));
		
		return tranMsg;
	}
	
	@DeleteMapping("/mail/e/{mailNo}")
	public String deleteMail(@PathVariable("mailNo")String mailNo) {
		System.out.println("company-mail-deleteMail");
		
		String tranMsg = companyMailService.deleteMail(Long.valueOf(URIVariableCrypto.decodePathVariable(mailNo)));
		
		return tranMsg;
	}
}
