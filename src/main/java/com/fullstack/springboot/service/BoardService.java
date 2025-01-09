package com.fullstack.springboot.service;


import org.springframework.data.domain.PageRequest;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Employees;



public interface BoardService {

	
	Long register(BoardDTO dto);
	
	//list 페이지 뿌려질 명세 정의..PageResult 객체를 이용함.
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	//글상세에서 필요한 기능 선언
	BoardDTO getRead(Long boardNo);
	
	//수정 기능 선언
	void modify(BoardDTO boardDTO);
	
	//글삭제 기능 선언..댓글과 같이 삭제 시킨다.
	void removeWithRelpies(Long boardNo);
	
	//default 메서드로 매퍼 정의..아래는 dto --> entity
	default Board dtotoEntity(BoardDTO dto) {
			Employees employees = Employees.builder()
					.mailAddress(dto.getMailAddress()).build();
			
			Board entity = Board.builder()
					.boardNo(dto.getBoardNo())
					.title(dto.getTitle())
					.contents(dto.getContents())
					.employees(employees)
					.build();
			return entity;
		}
		
	default BoardDTO entityToDTO(Board board, Employees employees, Long replyCount) {
		BoardDTO boardDTO = BoardDTO.builder()
				.boardNo(board.getBoardNo())
				.title(board.getTitle())
				.contents(board.getContents())
				.regdate(board.getRegDate())
				.moddate(board.getModDate())
				.mailAddress(employees.getMailAddress())
				.replyCount(replyCount.intValue())
				.build();
		return boardDTO;
	}
	
	
}
