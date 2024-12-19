package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.CompanyMail;

public interface CompanyMailRepository extends JpaRepository<CompanyMail, Long> {
	
}
