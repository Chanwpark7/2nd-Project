package com.fullstack.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.CompanyChatMemberDTO;
import com.fullstack.springboot.entity.CompanyChatMember;

public interface CompanyChatMemberRepository extends JpaRepository<CompanyChatMember, Long> {


	//해당 채팅방에 들어가있는 모든 직원 list
	@Query("select distinct new com.fullstack.springboot.dto.CompanyChatMemberDTO(cm) from CompanyChatMember cm "
			+ "inner join CompanyChat c on cm.companyChat.chatNo =:chatNo")
	List<CompanyChatMemberDTO> getChatAllMember(@Param("chatNo")String chatNo);
	
	
	//만약에 해당 직원 채팅방 강퇴시킬수도 있으니깐
	@Query("select distinct new com.fullstack.springboot.dto.CompanyChatMemberDTO(cm) from CompanyChatMember cm "
			+ "inner join CompanyChat c on cm.companyChat.chatNo =:chatNo and cm.employees.empNo =:empNo")
	List<CompanyChatMemberDTO> getChatOneMember(@Param("chatNo")String chatNo, @Param("empNo")Long empNo);
	
	
	@Query("select new com.fullstack.springboot.dto.CompanyChatMemberDTO(cm) from CompanyChatMember cm "
			+ "where cm.companyChat.chatNo =:chatNo and cm.employees.empNo =:empNo ")
	Optional<CompanyChatMemberDTO> getSameList(@Param("chatNo")String chatNo, @Param("empNo")Long empNo);
	
	
	@Query("select new com.fullstack.springboot.dto.CompanyChatMemberDTO(cm) from CompanyChatMember cm where cm.employees.empNo =:empNo")
	Long getEmp(@Param("empNo")Long empNo);
	
	@Query("delete from CompanyChatMember cm where cm.employees.empNo =:empNo")
	Long delEmp(@Param("empNo")Long empNo);
	
	@Query("select ccm from CompanyChatMember ccm where ccm.employees.empNo =:empNo and ccm.companyChat.chatNo=:chatNo")
	CompanyChatMember getByMemberEmpNo(@Param("empNo")Long empNo, @Param("chatNo")String chatNo);
}
