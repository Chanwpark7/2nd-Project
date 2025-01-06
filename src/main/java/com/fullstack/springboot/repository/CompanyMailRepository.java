package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.CompanyMail;

import jakarta.transaction.Transactional;

public interface CompanyMailRepository extends JpaRepository<CompanyMail, Long> {
	
	@Query("select cm from CompanyMail cm where cm.sender.empNo = :senderNo")
	public List<CompanyMail> getList(@Param("senderNo") Long senderNo);
//	
//	@Query("select cm from CompanyMail cm where cm.sender.mailAddress = :email and cm.mailCategory = :cat")
//	public Page<CompanyMail> getListPage(@Param("email") String email,@Param("cat") String cat ,Pageable pageable);

	@Query("select cm from CompanyMail cm join CompanyMailReceived cmr on cm.mailNo = cmr.mail.mailNo where cmr.employee.mailAddress = :email and cm.mailCategory = :cat")
	public Page<CompanyMail> getListPage(@Param("email") String email,@Param("cat") String cat ,Pageable pageable);
	
	@Query("select cm from CompanyMail cm where cm.sender.mailAddress = :email")
	public Page<CompanyMail> getMySendListPage(@Param("email") String email, Pageable pageable);

}
