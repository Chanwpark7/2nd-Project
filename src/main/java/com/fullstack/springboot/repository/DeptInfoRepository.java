package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.entity.DeptInfo;

public interface DeptInfoRepository extends JpaRepository<DeptInfo, Long> {
	
	@Query("Select new com.fullstack.springboot.dto.DeptInfoDTO(di) from DeptInfo di where di.deptName = :deptName")
	DeptInfoDTO getDeptInfoWithName(@Param("deptName") String deptName);

	@Query("Select new com.fullstack.springboot.dto.DeptInfoDTO(di) from DeptInfo di")
	List<DeptInfoDTO> getDeptList();
}
