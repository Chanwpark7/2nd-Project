package com.fullstack.springboot.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.service.BoardService;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	//게시물 수정 처리
	@PostMapping("/modify")
	public String modifyProc(BoardDTO dto, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, 
			RedirectAttributes redirectAttributes) {
	
		boardService.modify(dto);
		
		redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
		redirectAttributes.addAttribute("type", pageRequestDTO.getType());
		redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
		

		redirectAttributes.addAttribute("boardNo", dto.getBoardNo());
		return "redirect:/board/list";
	}
	
	
	//게시물조회 및 수정 연결
	@GetMapping({"/read","/modify"})
	public BoardDTO read(@ModelAttribute("pageRequestDTO") PageRequestDTO requestDTO, @RequestParam(value = "boardNo", required = false) Long boardNo, Model model) {
		log.error("상세 페이지 요청됨 " + boardNo);
		
		
		
		BoardDTO boardDTO = boardService.getRead(boardNo);
		model.addAttribute("dto", boardDTO);
		
		System.out.println(boardDTO);
		return boardDTO;
	}
	
	
	//등록폼연결
	@GetMapping("/register")
	public void register() {
		log.error("등록폼 요청됨..");
	}
	
	@PostMapping("/registerPost")//등록 처리후 list 로 리다에렉트
	public String registerPost(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		
		log.error("등록 처리 수행..");
		Long boardNo = boardService.register(boardDTO);
		log.error("신규 등록 글번호 : " + boardNo);
		
		redirectAttributes.addFlashAttribute("msg","글 번호 " + boardNo + " 가 잘 등록됨");
		return"redirect:/board/list";
	}
	
	
	
	//게시글 목록 요청 및 UI 에 모델 연결
//	@GetMapping("/list")
//	public String list(PageRequestDTO pageRequestDTO, Model model) {
//		log.error("리스트 요청됨..." + pageRequestDTO);
//		
//		
//		model.addAttribute("result", boardService.getList(pageRequestDTO));
//		return "test";
//		
//	}
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.error("리스트 요청됨..." + pageRequestDTO);
		
		
		model.addAttribute("result", boardService.getList(pageRequestDTO));
		
	}
}
