package com.fullstack.springboot.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.BoardService;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@PostMapping("/add")
	public void registerPost(@RequestBody BoardDTO boardDTO) {

		boardService.register(boardDTO);
		
	}
	
	
	//게시글 목록 요청 및 UI 에 모델 연결
	@GetMapping("/list")
	public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
		
		return boardService.getList(pageRequestDTO);
		
	}
	
	@GetMapping("/read/{boardNo}")
	public BoardDTO getOne(@PathVariable(name = "boardNo") Long boardNo) {
		
		
		return boardService.getRead(boardNo);
	}
	
	@PutMapping("/{boardNo}")
	public void putMethodName(@PathVariable(name = "boardNo") Long boardNo, @RequestBody BoardDTO boardDTO) {
		boardService.modify(boardNo, boardDTO);
	}
}
