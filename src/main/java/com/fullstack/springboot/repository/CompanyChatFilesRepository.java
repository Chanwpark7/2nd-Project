package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.CompanyChatFilesDTO;
import com.fullstack.springboot.entity.CompanyChatFiles;
import java.util.List;


public interface CompanyChatFilesRepository extends JpaRepository<CompanyChatFiles, Long> {

	@Query("select new com.fullstack.springboot.dto.CompanyChatFilesDTO(cf) from CompanyChatFiles cf where cf.attachOriginName =:attachOriginName")
	List<CompanyChatFilesDTO> getImg(@Param("attachOriginName")String attachOriginName);

	
	
}
