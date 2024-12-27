package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	//삭제 기능 쿼리
	@Modifying//Select 가 아닌 DML 은 이 어노선언해야함.
	@Query("delete from Reply r where r.board.boardNo = :boardNo")
	void deleteByBno(@Param("boardNo") Long boardNo);
	
	//게시물의 댓글 리스트 리턴하도록 선언
	
	List<Reply> getRepliesByBoardOrderByRno(Board board);//쿼리 메서드 사용..
}
