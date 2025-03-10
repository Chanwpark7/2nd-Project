package com.fullstack.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.fullstack.springboot.dto.EmployeeReceiverDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.mail.dto.CompanyMailDTO;
import com.fullstack.springboot.mail.dto.CompanyMailListRequestDTO;
import com.fullstack.springboot.mail.dto.CompanyMailReceivedDTO;
import com.fullstack.springboot.mail.dto.CompanyMailResponseDTO;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.service.CompanyMailAttachFilesService;
import com.fullstack.springboot.service.CompanyMailReceivedService;
import com.fullstack.springboot.service.CompanyMailService;
import com.fullstack.springboot.util.FileUtil;
import com.fullstack.springboot.util.URIVariableCrypto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("${com.fullstack.springboot.company-url}")
public class CompanyMailController {

	private final CompanyMailService companyMailService;
	private final CompanyMailAttachFilesService companyMailAttachFilesService;
	private final CompanyMailReceivedService companyMailReceivedService;
	private final EmployeesRepository employeesRepository;
	
	private final FileUtil fileUtil;

	@PostMapping(path = "/mail", consumes = { "multipart/form-data;charset=UTF-8"})
	public String regiMail(CompanyMailDTO dto,@RequestParam String sendEmpNo,@RequestParam long[] receiveEmpNo) {
		
		System.out.println(dto.getContents());
//		dto.setContents(dto.getContents().replace("\n", "<br>"));
		
		List<String> savedName = null;
		
		System.out.println("company-mail-sendMail");
		System.out.println(dto);
		System.out.println(sendEmpNo);
		for(long t : receiveEmpNo) {
			System.out.println(t);
		}
		//return null;
		for(long no : receiveEmpNo) {
			System.out.println(no);
		}
		
		Employees emp = employeesRepository.getByEmail(sendEmpNo);
		
		dto.setSender(EmployeesDTO.builder().empNo(emp.getEmpNo()).build());
		List<EmployeesDTO> empList = new ArrayList<EmployeesDTO>();
		for(long no : receiveEmpNo) {
			empList.add(EmployeesDTO.builder().empNo(no).build());
		}
		dto.setEmployees(empList);
		dto.setMailNo(companyMailService.register(dto));
		if(dto.getFiles() != null) {
		savedName = fileUtil.attachFiles(dto.getFiles());
		companyMailAttachFilesService.register(dto, savedName);
		}
		System.out.println(dto);
		
		companyMailReceivedService.register(CompanyMail.builder().mailNo(dto.getMailNo()).build(), empList);
		
		
		System.out.println("company-mail-sendMail");
		return "company-mail-sendMail";
	}
	@PostMapping("/mail/attach")
	public String attachMail(@RequestBody CompanyMailDTO dto) {
		System.out.println("company-mail-attachMail");
		
		List<MultipartFile> files = dto.getFiles();
		String mailTitle = files.size()>1 ? files.get(0).getOriginalFilename():(files.get(0).getOriginalFilename() + "and " + String.valueOf(files.size()-1) + "more...");
		CompanyMail.builder().contents(dto.getContents())
								.title(mailTitle)
								.mailCategory(dto.getMailCategory())
								
								.build();
		
		String tranMsg = null;//companyMailService.register(dto);
		
		return tranMsg;
	}
	
	@GetMapping("/mail/r/{mailNo}")
	public CompanyMailDTO readMail(@PathVariable String mailNo) {
		System.out.println("company-mail-readMail");
		
		//CompanyMailDTO dto = companyMailService.read(Long.valueOf(URIVariableCrypto.decodePathVariable(mailNo)));
		CompanyMailDTO dto = companyMailService.read(Long.valueOf(mailNo));
		
		return dto;
	}
	@GetMapping("/mail/{mailNo}/received")
	public CompanyMailReceivedDTO readMailReceived(@PathVariable("mailNo") String mailNo) {
		System.out.println("company-mail-received");
		
		return companyMailReceivedService.getReceivedByMailNo(Long.valueOf(mailNo));
		
	}
	@GetMapping("/mail/{mailNo}/attcfile")
	public List<String> getMailAttachFIle(@PathVariable("mailNo") String mailNo) {
		System.out.println("company-mail-attachedFile");
		
		return companyMailService.findAttached(Long.valueOf(mailNo));
	}
	
	@Transactional
	//@GetMapping("/mail/l/{memberNo}")
	public List<CompanyMailDTO> listMail(@PathVariable String memberNo){
		System.out.println("company-mail-listMail");

		//List<CompanyMailDTO> mailList = companyMailService.getList(Long.valueOf(URIVariableCrypto.decodePathVariable(memberNo)));
		List<CompanyMailDTO> mailList = companyMailService.getList(Long.valueOf(memberNo));
		System.out.println(mailList);
		return mailList;
	}
	@Transactional
	@GetMapping("/mail/l")
	public CompanyMailResponseDTO listMailPage(@RequestParam String email,CompanyMailListRequestDTO reqDTO){
		System.out.println("company-mail-lisMailPage");
		Pageable pageable = PageRequest.of(reqDTO.getPage()-1, reqDTO.getSize(),Sort.by("mailNo").descending());
		
		return companyMailService.getListPage(email, pageable, reqDTO);
	}
	@Transactional
	@GetMapping("/mail/l/{email}")
	public CompanyMailResponseDTO listSendMailPage(@PathVariable String email, CompanyMailListRequestDTO reqDTO) {
		System.out.println("company-mail-listSendMailPage");
		Pageable pageable = PageRequest.of(reqDTO.getPage()-1, reqDTO.getSize(), Sort.by("mailNo").descending());
		
		return companyMailService.getMySendListPage(email, pageable, reqDTO);
	}
	@GetMapping("/mail/receiver")
	public List<EmployeeReceiverDTO> findMailReceiver(@RequestParam String email) {
		return companyMailService.findMailReceiver(email);
	}
	
	
	@PutMapping("/mail/m")
	public String modCat(@RequestParam String category, @RequestParam Long mailNo) {
		System.out.println("company-mail-modCat");

		//String tranMsg = companyMailService.modifyMailCat(Long.valueOf(URIVariableCrypto.decodePathVariable(mailNo)),cat);
		String tranMsg = companyMailService.modifyMailCat(mailNo,category);
		
		return tranMsg;
	}
	
	@DeleteMapping("/mail/e/{mailNo}")
	public String deleteMail(@PathVariable String mailNo) {
		System.out.println("company-mail-deleteMail");
		
		//String tranMsg = companyMailService.deleteMail(Long.valueOf(URIVariableCrypto.decodePathVariable(mailNo)));
		String tranMsg = companyMailService.deleteMail(Long.valueOf(mailNo));
		
		return tranMsg;
	}
}
