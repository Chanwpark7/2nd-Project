package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.repository.search.SearchBoardRepository;




public interface BoardRepository extends JpaRepository<Board, Long>,SearchBoardRepository {
	
	@Query("select b.boardNo, b.title,e.mail_address,e.employees_emp_no from Board b Join b.Employees e where b.boardNo= :boardNo")
	Object getBoardWithEmployees(@Param("boardNo") Long boardNo);//Object getBoardWithMember(@Param("boardNo") Long boardNo);//Object[] 로 리턴됨
	
	
	@Query("Select count(u), sum(u.boardNo) from Board u")
	Object getUsefunc();
	
	
	
	@Query("select b,r from Board b Right Join Reply r On r.board = b where b.boardNo = :boardNo")
	Object[] getBoardWithReply(); //Object[] getBoardWithReple();
	
	
	

	@Query(value = "select b, e, count(r)from Board b left join b.Employees e left join Reply r On r.board = b group by b"
			,countQuery = "select count(b) from Board b")
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
	
	
		
	
	@Query("select b, e, count(r) from Board b left join b.Emplyoees e left join Reply r On r.board = b where b.boardNo = :boardNo")
	Object getBoardByBoardNo(@Param("boardNo") Long boardNo);//Object getBoardByBno(@Param("boardNo") Long boardNo);
	

}
