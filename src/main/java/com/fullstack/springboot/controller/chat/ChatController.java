package com.fullstack.springboot.controller.chat;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.ChatMessageDTO;
import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatFilesDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.EmployeesImageDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;

import com.fullstack.springboot.repository.CompanyChatFilesRepository;

import com.fullstack.springboot.service.CompanyChatFilesService;
import com.fullstack.springboot.service.CompanyChatService;
import com.fullstack.springboot.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RequestMapping("/chat")
public class ChatController {

	@Value("${com.fullstack.springboot.uploadPath}")
	private String uploadPath;
	
	private final SimpMessagingTemplate template;
	private final CompanyChatService companyChatService;
	private final CompanyChatFilesService companyChatFilesService;
	private final FileUtil fileUtil;

	private final CompanyChatFilesRepository chatFilesRepository;

	
	//클라이언트한테 채팅내용 가져오기
	@MessageMapping("/chat/{chatRoomId}")
	@SendTo("/sub/chat/{chatRoomId}")
	public ChatMessageDTO sendMessage(@DestinationVariable("chatRoomId") String chatRoomId, ChatMessageDTO message) {
	    message.setSendTime(LocalDateTime.now());
	    return message;
	}

	
	@MessageMapping("/messages")
	public ChatMessageDTO send(@RequestBody ChatMessageDTO chatMessageDTO) {
		log.error("chatMessageDTO " + chatMessageDTO);

	    if (chatMessageDTO.getSendTime() == null) {
	        chatMessageDTO.setSendTime(LocalDateTime.now());
	    }
	    
	    if (chatMessageDTO.getReceiver() == null) {
	        chatMessageDTO.setReceiver(" ");
	    }
	    
	    String chatRoomId = generateChatRoomId(chatMessageDTO.getSender(), chatMessageDTO.getReceiver());
	    log.error("chatRoomId" + chatRoomId);

	    template.convertAndSend("/sub/chat/" + chatRoomId, chatMessageDTO);
	    return chatMessageDTO;
	}

	
	
//	@MessageMapping("/messages")
//	public ChatMessageDTO send(@RequestBody ChatMessageDTO chatMessageDTO) {
//	    log.error("chatMessageDTO " + chatMessageDTO);
//
//	    if (chatMessageDTO.getFileUrl() == null && chatMessageDTO.getFile() != null) {
//	        String fileUrl = uploadFile(chatMessageDTO.getFile(), chatMessageDTO.getChatNo()).getBody();  
//	        chatMessageDTO.setFileUrl(fileUrl); 
//	    }
//
//	    if (chatMessageDTO.getSendTime() == null) {
//	        chatMessageDTO.setSendTime(LocalDateTime.now());
//	    }
//
//	    if (chatMessageDTO.getReceiver() == null) {
//	        chatMessageDTO.setReceiver(" ");
//	    }
//
//	    String chatRoomId = generateChatRoomId(chatMessageDTO.getSender(), chatMessageDTO.getReceiver());
//	    log.error("chatRoomId" + chatRoomId);
//
//	    template.convertAndSend("/sub/chat/" + chatRoomId, chatMessageDTO);
//	    return chatMessageDTO;
//	}
	
//	private ChatMessageDTO uploadFile(MultipartFile file, String chatNo) {
//		
//		
//		
//	}
	
	//채팅방 하나로 만들려고. "작은숫자empNo_ 큰숫자empNo"
	public String generateChatRoomId(String sender, String receiver) {
	    return sender.compareTo(receiver) < 0 ? sender + "_" + receiver : receiver + "_" + sender;
	}
	
	//employee list 불러오기
	@GetMapping("/empList/{empNo}") 
	public PageResponseDTO<EmployeesDTO> getChatEmpList(PageRequestDTO pageRequestDTO, @PathVariable("empNo")Long empNo){
		return companyChatService.list(pageRequestDTO);
	}
	
	//채팅방 생성
	@PostMapping("/createChatRoom")
	public ResponseEntity<String> createChatRoom(@RequestBody CompanyChatDTO chatDTO) { 
	    String chatRoomId = companyChatService.createChatRoom(chatDTO);
	    return ResponseEntity.ok(chatRoomId);
	}
	
	//채팅보내기
	@PostMapping("/{senderEmpNo}/{receiverEmpNo}")
	public ResponseEntity<Map<String, Object>> sendChatRoom(@PathVariable("senderEmpNo") long senderEmpNo, @PathVariable("receiverEmpNo") long receiverEmpNo,@RequestBody ChatMessageDTO chatMessageDTO) {
		log.error("----------------"); 
		int year = LocalDate.now().getYear();
		String DIRECTORY_PATH = "C:" + File.separator + "chatting" + File.separator + year;
		
		
	    CompanyChatDTO chatDTO = new CompanyChatDTO();
	    chatDTO.setSenderEmpNo(senderEmpNo);
	    chatDTO.setReceiverEmpNo(receiverEmpNo);
	    String chatNo =generateChatRoomId(senderEmpNo, receiverEmpNo);
	    String filePath = DIRECTORY_PATH + File.separator + chatNo + ".xlsx";
	    
	    Long empNo = chatDTO.getEmpNo();
	    String content = chatMessageDTO.getContent();
	    String sendTime = chatMessageDTO.getSendTime() != null ? chatMessageDTO.getSendTime().toString() : LocalDateTime.now().toString();
	    
	    EmployeesDTO empInfo = companyChatService.getEmpFind(receiverEmpNo);
	    
	    log.error("senderEmpNo"+senderEmpNo);
	    log.error("receiverEmpNo"+receiverEmpNo);
	    log.error("getChatNo"+chatDTO.getChatNo());  
	        
	    companyChatService.createChatRoom(chatDTO); //채팅방 생성
	    	    
	    companyChatService.createFileAndFolder(chatNo,senderEmpNo,receiverEmpNo,content,sendTime, chatMessageDTO.getFileUrl()); //파일 및 폴더 생성
	    

	    companyChatService.sendChat(chatNo, senderEmpNo, receiverEmpNo, chatMessageDTO, sendTime, chatMessageDTO.getFileUrl()); //채팅보내기

	    
	    
	    log.warn("senderEmpNo"+senderEmpNo);  
	    log.warn("receiverEmpNo"+receiverEmpNo); 
	    
	    Map<String, Object> res = new HashMap<>();
	    	res.put("message", content);
	    	res.put("resInfo", empInfo);
	    
	    return ResponseEntity.ok(res);
	}
//	
//	@PostMapping("/file/{chatNo}")
//	public ResponseEntity<String> sendFile(@PathVariable("chatNo") String chatNo, 
//            @RequestParam("files") List<MultipartFile> files,
//            CompanyChatFilesDTO companyChatFilesDTO) {
//		log.info("reg "+ companyChatFilesDTO );
//		List<String> uploadFileNames = fileUtil.attachFiles(files);
//		
//		companyChatFilesDTO.setUploadFileNames(uploadFileNames);
//		companyChatFilesDTO.setChatNo(chatNo);
//		
//		companyChatFilesService.sendFile(companyChatFilesDTO);
////		saveChatToExcel(companyChatFilesDTO.getChatNo(), senderEmpNo, messageObj.getContent(), sendTime);
//		return ResponseEntity.ok("성공");
//	}
	
	@PostMapping("/file/{chatNo}/{empNo}")
	public ResponseEntity<String> sendFile(@PathVariable("chatNo") String chatNo, @PathVariable("empNo")long empNo,
			ChatMessageDTO messageObj,
	        @RequestParam("files") List<MultipartFile> files, 
	        CompanyChatFilesDTO companyChatFilesDTO) {
	    log.info("reg "+ companyChatFilesDTO);

	    List<String> uploadFileNames = fileUtil.attachFiles(files);

	    List<String> fileUrls = new ArrayList<>();
	    for (String fileName : uploadFileNames) {
	        String fileUrl = "/uploads/" + fileName;
	        fileUrls.add(fileUrl);  
	    }
	    
	    CompanyChatDTO chatDTO = new CompanyChatDTO();
	    
	    String content = messageObj.getContent();
	    String sendTime = messageObj.getSendTime() != null ? messageObj.getSendTime().toString() : LocalDateTime.now().toString();
	    
	    companyChatFilesDTO.setUploadFileNames(uploadFileNames);
	    companyChatFilesDTO.setChatNo(chatNo);

	    companyChatFilesService.sendFile(companyChatFilesDTO);
	    System.out.println("gggggggggggggg" + companyChatFilesDTO.getUploadFileNames());
	    String fileNames = String.join(",", uploadFileNames);
//	    return ResponseEntity.ok(String.join(",", fileUrls));
	    
	    companyChatService.saveChatToExcel(companyChatFilesDTO.getChatNo(), empNo, messageObj.getContent(), sendTime, fileNames);
	    
	    return ResponseEntity.ok(fileNames);
	}


	//채팅방 하나로 만들려고. "작은숫자empNo_ 큰숫자empNo"
	private String generateChatRoomId(long senderEmpNo, long receiverEmpNo) {
	    return Math.min(senderEmpNo, receiverEmpNo) + "_" + Math.max(senderEmpNo, receiverEmpNo);
	}
	
	//과거 내역 보기
	@GetMapping("/chat-history/{chatRoomId}")
	public ResponseEntity<List<List<String>>> getChatHistory(@PathVariable("chatRoomId") String chatRoomId) {
	    String[] empNos = chatRoomId.split("_");
	    long senderEmpNo = Long.parseLong(empNos[0]);
	    long receiverEmpNo = Long.parseLong(empNos[1]);

	    if (senderEmpNo > receiverEmpNo) {
	        long temp = senderEmpNo;
	        senderEmpNo = receiverEmpNo;
	        receiverEmpNo = temp;
	    }

	    String filePath = "C:" + File.separator + "chatting" + File.separator + LocalDate.now().getYear() + File.separator + generateChatRoomId(senderEmpNo, receiverEmpNo) + ".xlsx";
	    File file = new File(filePath);

	    if (!file.exists()) { //파일 존재 x
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }

	    try (FileInputStream fis = new FileInputStream(file); 
	         Workbook workbook = new XSSFWorkbook(fis)) {
	        
	        Sheet sheet = workbook.getSheetAt(0);
	        
	        if (sheet == null || sheet.getPhysicalNumberOfRows() == 0) {
	            return ResponseEntity.noContent().build();
	        }

	        List<List<String>> chatHistory = new ArrayList<>();
	        for (Row row : sheet) {
	            List<String> rowData = new ArrayList<>();
	            for (Cell cell : row) {
	                rowData.add(cell.toString());
	            }
	            chatHistory.add(rowData);
	        }

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
	                .header(HttpHeaders.CONTENT_TYPE, "application/json")
	                .body(chatHistory);
	        
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}



	@GetMapping("/chatList/{senderEmpNo}")
	 public ResponseEntity<List<CompanyChatDTO>> getChatList(@PathVariable("senderEmpNo") long senderEmpNo) {
        System.out.println("Aaaaa");
        System.out.println(senderEmpNo);
		List<CompanyChatDTO> chatList = companyChatService.getChatList(senderEmpNo);
        
        return ResponseEntity.ok(chatList);  
    }

	@DeleteMapping("/{senderEmpNo}/{receiverEmpNo}")
	public Map<String, String> remove(@PathVariable("senderEmpNo")Long senderEmpNo, @PathVariable("receiverEmpNo")Long receiverEmpNo){
		String chatNo =generateChatRoomId(senderEmpNo, receiverEmpNo);
		log.warn(chatNo); //맞음
		companyChatService.leaveChatRoom(chatNo, senderEmpNo,receiverEmpNo);
		
		return Map.of("Result","Success");
	}

	
	@GetMapping("/view/{attachUUID}")
	public ResponseEntity<Resource> viewImg(@PathVariable(name="attachUUID") String attachUUID) {
	    String filePath = uploadPath + File.separator + attachUUID;
	    System.out.println("uuu =>   " + attachUUID);
	    
	    if (!new File(filePath).exists()) {
	        return ResponseEntity.notFound().build();
	    }
	    
	     System.out.println("~~~~~~~~~~~` " + fileUtil.getFile(filePath));
	   
	    log.error("이걸 실행중~~~~~");
	    log.error(fileUtil.getFile(filePath));
	    log.error(fileUtil.getFile(attachUUID));
	    return fileUtil.getFile(attachUUID);
	}
	
	
	@GetMapping("/fileDetail/{attachOriginName}")
	public List<CompanyChatFilesDTO> getFileDetail(@PathVariable("attachOriginName")String attachOriginName) {
		System.out.println("attach => " + attachOriginName);

		return chatFilesRepository.getImg(attachOriginName);
	}
	
	@GetMapping("/viewFile/{attachUUID}")
	public ResponseEntity<Resource> viewFile(@PathVariable(name="attachUUID") String attachUUID) {
	 
	    String filePath = uploadPath + File.separator + attachUUID;
	    System.out.println("uuu =>   " + attachUUID);

	    File file = new File(filePath);
	    if (!file.exists()) {
	        return ResponseEntity.notFound().build(); 
	    }

	    Resource resource = new FileSystemResource(file);

	    if (resource.exists()) {
	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM) 
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"") 
	                .body(resource); 
	    } else {
	        return ResponseEntity.notFound().build(); 
	    }
	}


}
