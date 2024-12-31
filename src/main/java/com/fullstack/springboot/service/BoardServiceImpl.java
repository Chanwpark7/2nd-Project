package com.fullstack.springboot.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.BoardRepository;
import com.fullstack.springboot.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(BoardDTO dto) {
//		System.out.println("게시글 등록 메서드...");
//		Board board = dtotoEntity(dto);
//		
//		boardRepository.save(board);
//		return board.getBoardNo();
		return null;
	}
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		
//		
//		Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Employees)en[1], (Long)en[2]));
//		
//		//Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("boardNo").descending()));
//		
//		//검색기능을 추가한 Page 객체 얻기
//		Page<Object[]> result = boardRepository.searchPage(pageRequestDTO.getType(), 
//				pageRequestDTO.getKeyword(), pageRequestDTO.getPageable(Sort.by("boardNo").descending()));
//		
//		return new PageResultDTO<>(result, fn);
		return null;
	}
	@Override
	public BoardDTO getRead(Long boardNo) {
//		Object obj = boardRepository.getBoardByBoardNo(boardNo);
//		Object[] arr = (Object[])obj;
//		
//		
//		//Entity 를 DTO 로 변화.
//		return entityToDTO((Board)arr[0], (Employees)arr[1], (Long)arr[2]);
		return null;
		
	}
	//댓글과 함께 게실글 삭제 처리 구현
	
	
	@Transactional
	@Override
	public void removeWithRelpies(Long boardNo) {
		replyRepository.deleteByBoardNo(boardNo);
		boardRepository.deleteById(boardNo);
		
	}
	@Transactional
	@Override
	public void modify(BoardDTO boardDTO) {
		//아래 getRef...() 는 지연 로딩 처리메서드임..따라서, 이 메서드에는 Connection 이 유지되야 하므로, Transa...선언
		Board board = boardRepository.getReferenceById(boardDTO.getBoardNo());
		
		if(board != null) {
			board.changeContent(boardDTO.getContents());
			board.changeTitle(boardDTO.getTitle());
			
			boardRepository.save(board);
		}
	}

}
