package com.fullstack.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.ReplyDTO;
import com.fullstack.springboot.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*
 * RESTFul server 는 선언 해야함
 */
@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;


	
	
	@GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBno(@PathVariable("boardNo") Long boardNo){
		 
		List<ReplyDTO> result = replyService.getList(boardNo);
		return ResponseEntity.ok().body(result);
		//return new ResponseEntity<ReplyDTO>(replyService.getList(boardNo), HttpStatus.OK);
	}
	
	
	@PostMapping("")
	public ResponseEntity<Long> insertReply(@RequestBody ReplyDTO replyDTO){
		Long rno = replyService.register(replyDTO);
		
		return ResponseEntity.ok(rno);
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		replyService.remove(rno);
		return ResponseEntity.ok("success");
	}	
	
	//수정 처리
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO) {
		replyService.modify(replyDTO);
		return ResponseEntity.ok("success");
	}
	
	
	
	
}
