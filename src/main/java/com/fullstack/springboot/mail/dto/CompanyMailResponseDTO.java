package com.fullstack.springboot.mail.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class CompanyMailResponseDTO {
	
	private List<CompanyMailDTO> dtoList;
	private List<Integer> pageNumList;
	
	private CompanyMailListRequestDTO requestDTO;
	
	private boolean prev, next;
	
	private int totalCnt, totalPage, prevPage, nextPage, current;
	
	@Builder
	public CompanyMailResponseDTO(List<CompanyMailDTO> dtoList, CompanyMailListRequestDTO requestDTO, long totalCnt) {
		this.dtoList = dtoList;
		this.requestDTO = requestDTO;
		this.totalCnt = (int)totalCnt;

	    int end =   (int)(Math.ceil( requestDTO.getPage() / 10.0 )) *  10;

	    int start = end - 9;

	    int last =  (int)(Math.ceil((totalCnt/(double)requestDTO.getSize())));

	    end =  end > last ? last: end;

	    this.prev = start > 1;


	    this.next =  totalCnt > end * requestDTO.getSize();

	    this.pageNumList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

	    if(prev) {
	        this.prevPage = start -1;
	    }

	    if(next) {
	        this.nextPage = end + 1;
	    }

	    this.totalPage = this.pageNumList.size();

	    this.current = requestDTO.getPage();
		
		
	}
	

}
