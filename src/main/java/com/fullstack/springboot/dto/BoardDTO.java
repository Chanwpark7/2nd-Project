package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import com.fullstack.springboot.entity.Employees;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	private Long boardNo;
	private String title;
	private String contents;
	private Employees employees;
	private String writerEmail;
	private String writerName;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	private int replyCount;
}
