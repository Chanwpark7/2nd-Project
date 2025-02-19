package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.entity.CompanyChat;

public interface CompanyChatRepository extends JpaRepository<CompanyChat, String> {

	//해당 직원이 들어가있는 모든 채팅방 번호 list
	@Query("select new com.fullstack.springboot.dto.CompanyChatDTO(c) from CompanyChat c join c.companyChatMember cm where "
			+ " cm.employees.empNo =:empNo")
	public List<CompanyChatDTO> getCompanyChatNoAll(@Param("empNo")Long empNo);
	

	//현재 해당 직원이 속해있는 채팅방번호 
	@Query("select new com.fullstack.springboot.dto.CompanyChatDTO(c) from CompanyChat c inner join c.companyChatMember cm where "
			+ "cm.employees.empNo =:empNo and c.chatNo =:chatNo")
	public List<CompanyChatDTO> getCompanyChatNoOne(@Param("empNo")Long empNo, @Param("chatNo")String chatNo);
	
	//senderEmpNo 와 receiverEmpNo 가 속해있는 채팅방 번호 리턴
	@Query("select c from CompanyChat c join c.companyChatMember cm where cm.employees.empNo = :senderEmpNo and cm.companyChat.chatNo in "
	        + "(select c2.chatNo from CompanyChat c2 join c2.companyChatMember cm2 where cm2.employees.empNo = :receiverEmpNo)")
	public List<CompanyChat> findBySenderEmpNoAndReceiverEmpNo(@Param("senderEmpNo") long senderEmpNo, @Param("receiverEmpNo") long receiverEmpNo);


	public CompanyChat findByChatNo(String chatNo);
}
