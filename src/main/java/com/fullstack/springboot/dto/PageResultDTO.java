package com.fullstack.springboot.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;


@Data
public class PageResultDTO<DTO,En> {

	//리턴될 항목을 담는 List 선언..
	private List<DTO> dtoList;
	
	//총 페이지 번호..
	private int totalPage;
	
	//현재 페이지 번호
	private int page;
	//목록의 size
	private int size;
	
	//시작페이지, 끝 페이지 번호
	private int start, end;
	
	private boolean prev, next;//이건 Page 객체를 통해서 이전/이후가 있는지를 알수있음.
	
	//페이지 번호 목록을 담은 List 
	private List<Integer> pageList;
	
	
	
	public PageResultDTO(Page<En> result, Function<En, DTO> fn){
		
		//dto 리스트를 파람으로 받은 result 에서 스트림을 통해 생성합니다.
		//Page 객체에는 내부에 Entity 의 묶음이 있음..따라서 이걸 추출하려면 루프나 스트림을 통해 얻어내야함..
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		
		//totalPage 를 얻어냄..result(Page 객체) 를 통해 얻어낼수 잇음
		totalPage = result.getTotalPages();
		
		//페이징 처리수행하는 메서드 호출 해서 결과값 get
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		
		//가상의 마지막 페이값 산출...10,20,30,40 단위..즉 글 갯수와 상관없이 무조건 10 단위로 get 연산
		int tempEnd = (int)(Math.ceil(page / 10.0) * 10);
		start = tempEnd - 9;
		
		prev = start > 1;
		
		//실제 마지막 페이지 번호 get.
		end = totalPage > tempEnd ? tempEnd : totalPage;
		
		//다음은 페이지 블락 단위로 결정함..10 페이지씩 이동
		next = totalPage > tempEnd;
		
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		
	}
	
	
	
}
