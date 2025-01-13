package com.fullstack.springboot.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.ChatMessageDTO;
import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatMemberDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.CompanyChat;

public interface CompanyChatService {
	
	//채팅방 생성
	public String createChatRoom(CompanyChatDTO companyChatDTO);
	
	//채팅방 나가기
	public List<CompanyChatMemberDTO> leaveChatRoom(String chatNo);

	//채팅 data 엑셀 파일에 기록
	public ByteArrayInputStream chatDataToExcel(String chatNo);
	
	//채팅 data 엑셀 파일에서 읽어오기
	public List<List<String>> chatDataReadToExcel(String filePath, String chatNo) throws Exception;
	
	
	//모든 employee list 가져오기
	public PageResponseDTO<EmployeesDTO> list(PageRequestDTO pageRequestDTO);
	
	//파일생성 (폴더 / 파일)
	public void createFileAndFolder(String chatNo, long senderEmpNo, long receiverEmpNo, String content, String sendTime) ;
	
	//채팅 보내기
	public void sendChat(String chatNo, long senderEmpNo,long receiverEmpNo, ChatMessageDTO messageObj, String sendTime) ;
	
	//채팅내용 엑셀파일에 저장하기
	public void saveChatToExcel(String chatNo, long senderEmpNo, String content, String sendTime); 


}
