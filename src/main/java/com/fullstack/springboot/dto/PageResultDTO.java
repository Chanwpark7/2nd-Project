package com.fullstack.springboot.dto;

import java.util.List;
import java.util.function.Function;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO, En> {

	//리턴될 항목을 담는 List 선언.
	private List<DTO> dtoList;
	
	//총 페이지 번호.
	private int totalPage;
	
	//현재 페이지 번호
	private int page;
	//목록의 사이즈
	private int size;
	
	//시작페이지, 끝 페이지 번호
	private int start, end;
	
	private boolean prev, next;
	
	//페이지 번호 목록을 담은 List
	private List<Integer> pageList;
	
	public PageResultDTO(Page<En> result, Function<En, DTO> fn){
		
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		
		totalPage = result.getTotalPages();
		
		//페이징 처리수행하는 메소드 호출해서 결과값 get
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {

		this.page = pageable.getPageNumber()+1;
		this.size = pageable.getPageSize();
		
		//가상의 마지막 페이지 값 산출 10의 배수. 글 갯수와 상관없이 10 단위로 get
		int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;
		start = tempEnd-9;
		
		prev = start > 1;
		
		//실제 마지막 페이지 번호 get
		end = totalPage > tempEnd ? tempEnd : totalPage;
		
		//다음은 페이지 블락단위로 결정. 10 페이지씩 이동
		next = totalPage > tempEnd;
		
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
}
