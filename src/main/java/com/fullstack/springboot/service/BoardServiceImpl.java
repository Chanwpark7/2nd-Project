package com.fullstack.springboot.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.dto.board.BoardReadDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.BoardRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.ReplyRepository;
import com.mysql.cj.log.Log;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	private final EmployeesRepository employeesRepository;
	
	@Override
	public Long register(BoardDTO dto) {
		Employees employees = Employees.builder()
				.empNo(employeesRepository.getEmpWithMail(dto.getMailAddress()).getEmpNo()).build();
		
		Board board = Board.builder()
				.title(dto.getTitle())
				.contents(dto.getContents())
				.employees(employees)
				.category(dto.getCatecory())
				.build();
		
		boardRepository.save(board);
		return board.getBoardNo();
	}
	@Override
	public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {
		
		
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("boardNo").descending());
		
		//검색기능을 추가한 Page 객체 얻기
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		Page<BoardDTO> page = result.map(t -> { 
			return entityToDTO((Board)t[0], (Employees)t[1], (Long)t[2]);});
		
		List<BoardDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<BoardDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
	@Override
	public BoardDTO getRead(Long boardNo) {
		BoardReadDTO obj = boardRepository.getBoardWithEmployees(boardNo);
		
		BoardDTO boardDTO = BoardDTO.builder()
				.boardNo(obj.getBoardNo())
				.title(obj.getTitle())
				.contents(obj.getContents())
				.mailAddress(obj.getEmpDto().getMailAddress())
				.catecory(obj.getCategory())
				.regdate(obj.getRegdate())
				.moddate(obj.getModdate())
				.replyCount((int)obj.getReplyCnt())
				.build();
		return boardDTO;
	}

	
	@Transactional
	@Override
	public void removeWithRelpies(Long boardNo) {
		replyRepository.deleteByBoardNo(boardNo);
		boardRepository.deleteById(boardNo);
		
	}
	@Transactional
	@Override
	public void modify(Long boardNo ,BoardDTO boardDTO) {
		
		Board board = boardRepository.findById(boardNo).get();
		
		if(board != null) {
			board.changeContent(boardDTO.getContents());
			board.changeTitle(boardDTO.getTitle());
			board.changeCategory(boardDTO.getCatecory());
			
			boardRepository.save(board);
		}
	}

	

}
