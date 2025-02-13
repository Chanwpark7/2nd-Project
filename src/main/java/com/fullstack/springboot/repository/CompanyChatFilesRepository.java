package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.CompanyChatFiles;

public interface CompanyChatFilesRepository extends JpaRepository<CompanyChatFiles, Long> {

}
