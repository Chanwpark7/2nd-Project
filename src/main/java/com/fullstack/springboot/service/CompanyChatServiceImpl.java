package com.fullstack.springboot.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.ChatMessageDTO;
import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatMemberDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.CompanyChat;
import com.fullstack.springboot.entity.CompanyChatMember;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.CompanyChatMemberRepository;
import com.fullstack.springboot.repository.CompanyChatRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CompanyChatServiceImpl implements CompanyChatService {

	private final CompanyChatRepository companyChatRepository;
	private final EmployeesRepository employeesRepository;
	private final CompanyChatMemberRepository companyChatMemberRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	//채팅방 생성
	@Override
	public String createChatRoom(CompanyChatDTO companyChatDTO) {
		String chatNo = generateChatRoomId(companyChatDTO.getSenderEmpNo(), companyChatDTO.getReceiverEmpNo());
		
		CompanyChat companyChat = CompanyChat.builder().chatNo(chatNo).build();
	    companyChatRepository.save(companyChat);

	    Optional<Employees> senderOpt = employeesRepository.findById(companyChatDTO.getSenderEmpNo());
	    Optional<Employees> receiverOpt = employeesRepository.findById(companyChatDTO.getReceiverEmpNo());
	    
	    Employees sender = senderOpt.get();
	    Employees receiver = receiverOpt.get();
	    
	    CompanyChatMember senderMember = CompanyChatMember.builder()
	    		.companyChat(companyChat)
	            .employees(sender)
	            .build();
	    CompanyChatMember receiverMember = CompanyChatMember.builder()
	            .companyChat(companyChat)
	            .employees(receiver)
	            .build();

	    Optional<CompanyChatMemberDTO> companyChatOpt = companyChatMemberRepository.getSameList(companyChat.getChatNo(), sender.getEmpNo());
	   
	    if(companyChatOpt.isPresent()) { // 중복으로 저장되길래, 막음
	    	return companyChatDTO.getChatNo();
	    }    
	    
	    companyChatMemberRepository.save(senderMember);
	    companyChatMemberRepository.save(receiverMember);
	      
	    ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
	      
	    long senderEmpNo = sender.getEmpNo();
	    long receiverEmpNo = receiver.getEmpNo(); 
	    String message = chatMessageDTO.getContent();
	    String sendTime = "";
	      
	    createFileAndFolder(chatNo, senderEmpNo, receiverEmpNo, message, sendTime);
	    
	    companyChatDTO.setChatNo(companyChat.getChatNo()); 
	    return companyChat.getChatNo();
	}


	//채팅방 나가기
//	@Override
//	public List<CompanyChatMemberDTO> leaveChatRoom(long chatNo, long empNo) {
//	    List<CompanyChatMemberDTO> mem = companyChatMemberRepository.getChatOneMember(chatNo, empNo);
//	   
//	    for (CompanyChatMemberDTO dto : mem) {
//	        companyChatMemberRepository.deleteById(dto.getCompanyChatMemberNo());
//	    }
//	    
//	    List<CompanyChatMemberDTO> remainMem = companyChatMemberRepository.getChatAllMember(chatNo);
//
//	    return remainMem;
//	}

	//채팅방 인원 추가
//	@Override
//	public Long addChatMember(CompanyChatDTO companyChatDTO) {
//		CompanyChat companyChat = companyChatRepository.findById(companyChatDTO.getChatNo()).get();
//		Employees employees = employeesRepository.findById(companyChatDTO.getEmpNo()).get();
//		
//		CompanyChatMember companyChatMember = CompanyChatMember.builder()
//				.companyChat(companyChat)
//				.employees(employees)
//				.build();
//		companyChatMemberRepository.save(companyChatMember);
//		
//		return companyChatDTO.getChatNo();
//	}

	//사진 파일 보내기
//	@Override
//	public void sendImageFile(long chatNo, long empNo, MultipartFile file) {
//	    String originalFileName = file.getOriginalFilename();
//	    String uuid = UUID.randomUUID().toString();
//	    String savedFileName = uuid + "-" + originalFileName;
//	    String uploadPath = fileUtil.getUploadPath();
//	    
//	    Path savePath = Paths.get(uploadPath, savedFileName);
//	    
//	    try {
//	        if (!Files.exists(savePath.getParent())) {
//	            Files.createDirectories(savePath.getParent());
//	        }
//	        Files.copy(file.getInputStream(), savePath);
//	    } catch (IOException e) {
//	        log.error(e.getMessage());
//	    }
//	}

	//파일 보내기 (---> 이메일 연동)
//	@Override
//	public void sendFile(long chatNo, long empNo, MultipartFile file) {
//		
//	}

	
	//채팅 알람 띄우기
//	@Override
//	public void chatAlarm(long chatNo, long empNo, String msgId) {
//		
//	}

	
	//채팅 data Excel 파일에 저장하기
	@Override
	public ByteArrayInputStream chatDataToExcel(String chatNo) {
	    String currMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
	    List<String> headers = getHeaderData();
	    List<List<String>> rows = getRowDatas(chatNo);  
	    
	    String yearDirPath = "C:" + File.separator + "chatting" + File.separator + LocalDate.now().getYear();
	    File file = new File(yearDirPath, chatNo + ".xlsx");
	    
	    Workbook workbook = null;
	    
	    if (file.exists()) { //파일 존재시, 불러오기
	        try (FileInputStream fis = new FileInputStream(file)) {
	            workbook = new XSSFWorkbook(fis);
	        } catch (IOException e) {
	            log.error(e.getMessage());
	        }
	    } else { //파일x , 새로 생성
	        workbook = new XSSFWorkbook();
	    }

	    Sheet sheet = getOrCreateSheet(workbook, currMonth);
	    int rowNum = sheet.getLastRowNum() + 1;

	    if (rowNum == 1) {
	        Row headerRow = sheet.createRow(0);
	        for (int i = 0; i < headers.size(); i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(headers.get(i));
	        }
	    }

	    for (int i = 0; i < rows.size(); i++) {
	        Row row = sheet.createRow(rowNum + i);
	        List<String> data = rows.get(i);
	        for (int j = 0; j < data.size(); j++) {
	            Cell cell = row.createCell(j);
	            cell.setCellValue(data.get(j));
	        }
	    }

	    try (FileOutputStream fileOut = new FileOutputStream(file)) {
	        workbook.write(fileOut);
	        workbook.close();
	    } catch (IOException e) {
	        log.error(e.getMessage());
	    }

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    try {
	        workbook.write(outputStream);
	        workbook.close();
	    } catch (IOException e) {
	        log.error(e.getMessage());
	    }
	    
	    return new ByteArrayInputStream(outputStream.toByteArray());
	}

	
	private Sheet getOrCreateSheet(Workbook workbook, String month) {
	    Sheet sheet = workbook.getSheet(month);
	    if (sheet == null) {
	        sheet = workbook.createSheet(month);
	    }
	    return sheet;
	}

	private List<String> getHeaderData() {
	    List<String> headers = new ArrayList<>();
	    headers.add("날짜/시간");
	    headers.add("사용자");
	    headers.add("메시지 내용");
	    return headers;
	}

	private List<List<String>> getRowDatas(String chatNo) {
	    List<List<String>> rows = new ArrayList<>();
	    return rows;
	}
	
	
	// 채팅 data excel 읽기
	public List<List<String>> chatDataReadToExcel(String filePath, String chatNo) throws Exception { //test 로 뿌려지는 거 확인함.
		int year = LocalDate.now().getYear();
		String DIRECTORY_PATH = "C:" + File.separator + "chatting" + File.separator + year;
		String fileName = chatNo + ".xlsx";  
		File file = new File(DIRECTORY_PATH, fileName);

	    List<List<String>> rowDataList = new ArrayList<>();

	    if (!file.exists()) {
	        throw new FileNotFoundException(filePath);
	    }

	    try (FileInputStream fis = new FileInputStream(file);
	         Workbook workbook = new XSSFWorkbook(fis)) {
	        
	        Sheet sheet = workbook.getSheetAt(0);
	        Iterator<Row> rowIterator = sheet.iterator();

	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            List<String> rowData = new ArrayList<>();
	            Iterator<Cell> cellIterator = row.iterator();

	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                rowData.add(cell.toString());
	            }
	            rowDataList.add(rowData);
	        }
	    }
	    
	    for (List<String> rowData : rowDataList) {
	        String sender = rowData.get(0);  
	        String message = rowData.get(1);
	        String sentTime = rowData.get(2); 
	       System.out.println(sender +  " " + message + " ( " +  sentTime + " ) ");
	    }
	    
	    
	    return rowDataList;
	}
	
	
	
	
	//직원 리스트 가져오기
	@Override
	public PageResponseDTO<EmployeesDTO> list(PageRequestDTO pageRequestDTO) { 

		Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("empNo").descending());
		
		Page<EmployeesDTO> res = employeesRepository.empAllList(pageable); 
		
		List<EmployeesDTO> dtoList = res.get()
				.collect(Collectors.toList());
		
		long totalCount = res.getTotalElements();
		
		return PageResponseDTO.<EmployeesDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}

	//파일 및 폴더 생성
	@Override
	public void createFileAndFolder(String chatNo, long senderEmpNo, long receiverEmpNo, String content, String sendTime) {
	    String chatRoomId = generateChatRoomId(senderEmpNo, receiverEmpNo);

	    int year = LocalDate.now().getYear();
	    String DIRECTORY_PATH = "C:" + File.separator + "chatting";
	    File firDir = new File(DIRECTORY_PATH);

	    if (!firDir.exists()) {
	        firDir.mkdir();  
	    }

	    String YEAR_DIRECTORY_PATH = "C:" + File.separator + "chatting" + File.separator + year;
	    File secDir = new File(YEAR_DIRECTORY_PATH);
	    if (!secDir.exists()) {
	        secDir.mkdir();  
	    }

	    String fileName = chatRoomId + ".xlsx";  
	    File file = new File(secDir, fileName);

	    if (content == null || content.trim().isEmpty()) {
	        return; 
	    }

	    Workbook workbook = null;
	    Sheet sheet = null;

	    try {
	        if (file.exists()) {
	            try (FileInputStream fileIn = new FileInputStream(file)) {
	                workbook = new XSSFWorkbook(fileIn);
	            }
	        } else {
	            workbook = new XSSFWorkbook();
	        }

	        String mon = LocalDate.now().getMonth().toString();
	        sheet = workbook.getSheet(mon);

	        if (sheet == null) {
	            sheet = workbook.createSheet(mon);  
	            Row headerRow = sheet.createRow(0);  
	            headerRow.createCell(0).setCellValue("Sender");
	            headerRow.createCell(1).setCellValue("SendMessage");
	            headerRow.createCell(2).setCellValue("SendTime");
	        }

	        int lastRowNum = sheet.getLastRowNum();
	        Row dataRow = sheet.createRow(lastRowNum + 1);
	        
	        dataRow.createCell(0).setCellValue(senderEmpNo);
	        dataRow.createCell(1).setCellValue(content);
	        dataRow.createCell(2).setCellValue(sendTime);

	        try (FileOutputStream fileOut = new FileOutputStream(file)) {
	            workbook.write(fileOut);  
	        }

	    } catch (IOException e) {
	        log.error(e.getMessage());
	    } finally {
	        if (workbook != null) {
	            try {
	                workbook.close();
	            } catch (IOException e) {
	                log.error(e.getMessage());
	            }
	        }
	    }
	}

	//채팅 보내기
	@Override
	public void sendChat(String chatNo, long senderEmpNo, long receiverEmpNo, ChatMessageDTO messageObj, String sendTime) {
		List<CompanyChat> companyChats = companyChatRepository.findBySenderEmpNoAndReceiverEmpNo(senderEmpNo, receiverEmpNo);

	    CompanyChat companyChat = companyChats.isEmpty() ? null : companyChats.get(0);
	    

	    // 채팅방X ->생성
	    if (companyChat == null) {
	        companyChat = new CompanyChat();
	        companyChatRepository.save(companyChat); 
	       
	        CompanyChatMember senderMember = createChatMember(senderEmpNo, companyChat);
	        CompanyChatMember receiverMember = createChatMember(receiverEmpNo, companyChat);
	        
	        companyChatMemberRepository.save(senderMember);
	        companyChatMemberRepository.save(receiverMember);
	    } else {
	    	
	        saveChatToExcel(companyChat.getChatNo(), senderEmpNo, messageObj.getContent(), sendTime);
	    }
	}


	private CompanyChatMember createChatMember(long empNo, CompanyChat companyChat) {
	    Employees employee = employeesRepository.findById(empNo)
	            .orElseThrow(() -> new RuntimeException("Employee not found with empNo: " + empNo));
	    
	    CompanyChatMember member = new CompanyChatMember();
	    member.setEmployees(employee);
	    member.setCompanyChat(companyChat);
	    return member;
	}

	//채팅 내용 저장하기
	public void saveChatToExcel(String chatNo, long senderEmpNo, String content, String sendTime) {
	    String currMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
	    String yearDirPath = "C:" + File.separator + "chatting" + File.separator + LocalDate.now().getYear();
	
	    File file = new File(yearDirPath, chatNo + ".xlsx");  

	    Workbook workbook = null;
	    if (file.exists()) { // 파일이 존재하면 읽기
	        try (FileInputStream fis = new FileInputStream(file)) {
	            workbook = new XSSFWorkbook(fis);
	        } catch (IOException e) {
	            log.error(e.getMessage());
	        }
	    } else { // 파일이 없으면 새로 생성
	        workbook = new XSSFWorkbook();
	    }

	    Sheet sheet = getOrCreateSheet(workbook, currMonth);
	    
	    int rowNum = sheet.getLastRowNum() + 1;  
	    if (rowNum == 1) {
	        Row headerRow = sheet.createRow(0);
	        headerRow.createCell(0).setCellValue("보낸사람");
	        headerRow.createCell(1).setCellValue("보낸메시지");
	        headerRow.createCell(2).setCellValue("보낸시간");
	    }


	    Row dataRow = sheet.createRow(rowNum);
	    dataRow.createCell(0).setCellValue(senderEmpNo);
	    dataRow.createCell(1).setCellValue(content);  
	    dataRow.createCell(2).setCellValue(sendTime); 

	    
	    try (FileOutputStream fileOut = new FileOutputStream(file)) {
	        workbook.write(fileOut);
	    } catch (IOException e) {
	        log.error(e.getMessage());
	    } finally {
	        try {
	            if (workbook != null) {
	                workbook.close();
	            }
	        } catch (IOException e) {
	            log.error(e.getMessage());
	        }
	    }
	}

	private String generateChatRoomId(long senderEmpNo, long receiverEmpNo) {
	    long[] empNos = {senderEmpNo, receiverEmpNo};
	    Arrays.sort(empNos); 
	    return empNos[0] + "_" + empNos[1];  
	}
	
	@Override
	public EmployeesDTO getEmpFind(long empNo) {
		log.warn("eeeee");
		return employeesRepository.empFind(empNo);
	}
	
	
}