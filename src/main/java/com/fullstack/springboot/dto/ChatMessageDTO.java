package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

	private String content;
	private String sender;
	private String receiver;
	private LocalDateTime sendTime;
	
	
	/*
	 * 여기서부터 추가됨
	 */
	private String fileUrl;
	private MultipartFile file;
	private String chatNo;
	
	/*
	 * 여기까지
	 */
}
