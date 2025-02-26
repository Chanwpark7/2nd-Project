package com.fullstack.springboot.dto.board;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.Board;

import lombok.Data;

@Data
public class BoardReadDTO {
	private long boardNo;
	private String title;
	private String contents;
	private EmployeesDTO empDto;
	private String category;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	
	public BoardReadDTO(Board board, long replyCnt) {
		this.boardNo = board.getBoardNo();
		this.title = board.getTitle();
		this.contents = board.getContents();
		this.empDto = EmployeesDTO.builder().mailAddress(board.getEmployees().getMailAddress()).build();
		this.category = board.getCategory();
	}
	public BoardReadDTO(long boardNo, String title, String contents,
			String empMail, String category, LocalDateTime regDate, LocalDateTime modDate) {
		this.boardNo = boardNo;
		this.title = title;
		this.contents = contents;
		this.empDto = EmployeesDTO.builder().mailAddress(empMail).build();
		this.category = category;
		this.regdate = regDate;
		this.moddate = modDate;
	}
	public BoardReadDTO(long boardNo, String title, String contents,
			String empMail, String category) {
		this.boardNo = boardNo;
		this.title = title;
		this.contents = contents;
		this.empDto = EmployeesDTO.builder().mailAddress(empMail).build();
		this.category = category;
	}
	
}
