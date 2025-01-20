package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReplyDTO {

	private Long rno;
	private String text;
	private String replyer;
	private Long boardNo; //게시글 참조 필드
	private LocalDateTime regdate,moddate;
}
