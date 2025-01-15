package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.CompanyMailAttachFiles;

public interface CompanyMailAttachFilesRepository extends JpaRepository<CompanyMailAttachFiles, Long> {

}
