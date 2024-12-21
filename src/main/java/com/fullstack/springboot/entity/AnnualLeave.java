package com.fullstack.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnnualLeave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long annualId;
	
	@OneToOne
	private Employees employees;
	
	private int antecedent;
	
	private String histroy;
	
	private long hours;
	
	/*
	 * 수정안
	 * 
	 * 1. 일단 이 테이블의 정체가 중요한데 employees 가 one to one 이고 history 가 동시에 들어가 있는 이유를 모르겠음.
	 * 1-1. 사원들의 연차 갯수를 저장해놓은 테이블인가?
	 * ㄴ 그렇다면 annualleave history table 을 따로 만들어서 이력을 관리하고 history column은 삭제 해야함.
	 * 1-2. 사원들의 연차 사용 이력을 저장해놓은 테이블인가?
	 * ㄴ 그렇다면 employees 는 Manytoone, hours 는 employees 테이블로 옮겨야한다.
	 * 
	 * 2. dayoff가 history를 관리하는 테이블인가?
	 * 2-1 맞다면 1-1 안대로 관리돼야함.
	 * 2-2 아니라면 dayoff 는 뭐하는 애지
	 */
}
