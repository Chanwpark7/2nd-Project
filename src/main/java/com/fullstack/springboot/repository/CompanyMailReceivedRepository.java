package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.CompanyMail;
import com.fullstack.springboot.entity.CompanyMailReceived;

public interface CompanyMailReceivedRepository extends JpaRepository<CompanyMailReceived, Long> {
	
	@Query("select cmr from CompanyMailReceived cmr where cmr.mail.mailNo = :mailNo")
	List<CompanyMailReceived> getReceiveds(@Param("mailNo")Long mailNo);

}
