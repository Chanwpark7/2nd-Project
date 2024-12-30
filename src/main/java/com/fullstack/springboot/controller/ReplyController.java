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

	
	/*
	 * REST 요청 매핑을 할때는 다음 몇가지를 생각 해야 합니다.
	 * 
	 *  1.요청을 어떻게 받을것인지..기본적으로는 페이지를 Viewer 로 지정하지만, 지금은 그렇지 않기 때문에
	 *  요청 자체를 path 로 받아서 처리함(PathVaraible 처럼)
	 *  
	 *  2.서버에서 보낼 데이터 타입을 지정해야함..반드시!!(MediaType.Application.JSON_VALUE 라는 상수로 되어있음)
	 *  
	 *  3.보낼때는 데이터만 보내주는 Restful 응답 객체인 ResponseEntity 객체를 리턴하도록 해야함. 이때, 파라미터로 넘겨질 데이터와, 서버의 상태코드 값을 
	 *  같이 보내야함)
	 */
	
	//상세화면에서 댓글 확인 버튼을 클릭하면, 글 번호에 따른 댓글 리스트를 JSON 으로 보내도록 메서드 정의
	@GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBno(@PathVariable("boardNo") Long boardNo){
		 
		List<ReplyDTO> result = replyService.getList(boardNo);
		return ResponseEntity.ok().body(result);
		//return new ResponseEntity<ReplyDTO>(replyService.getList(boardNo), HttpStatus.OK);
	}
	
	//서버에서는 JSON 으로 파라미터를 받을땐, 메서드 파라미터로 @RequesetBody 를 넣고, 매핑되는 DTO 를 선언하면 됩니다.
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
