
package com.fullstack.springboot.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//리스트에서 요청되는 페이지의 정보를 담고 있는 DTO
@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

	private int page;
	private int size;//글 목록 수
	private String type;//조회분류변수 (writer or content, title)
	private String keyword;//검색 내용.
	
	public PageRequestDTO() {
		//기본값 초기화
		this.page = 1;
		this.size = 10;
		this.keyword="";
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, sort);
	}
}