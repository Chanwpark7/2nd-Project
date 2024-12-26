package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.CompanyMail;

public interface CompanyMailRepository extends JpaRepository<CompanyMail, Long> {
	
	@Query("select cm from CompanyMail cm where cm.sender.empNo = :senderNo")
	public List<CompanyMail> getList(@Param("senderNo") Long senderNo);


}
