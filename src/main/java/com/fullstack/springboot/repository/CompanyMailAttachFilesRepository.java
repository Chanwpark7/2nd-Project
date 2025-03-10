package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.entity.CompanyMailAttachFiles;

public interface CompanyMailAttachFilesRepository extends JpaRepository<CompanyMailAttachFiles, Long> {
	
	@Transactional
	@Query("select cmaf from CompanyMailAttachFiles cmaf where cmaf.companyMail.mailNo = :eNo")
	public List<CompanyMailAttachFiles> getListByMailNo(@Param("eNo") long eNo);
	
	@Transactional
	@Query("select cmaf from CompanyMailAttachFiles cmaf where cmaf.attachUUID = :fileUuid")
	public CompanyMailAttachFiles getOne(@Param("fileUuid") String fileUuid);
}
