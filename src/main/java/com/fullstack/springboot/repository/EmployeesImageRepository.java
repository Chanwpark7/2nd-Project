package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.EmployeesImage;

public interface EmployeesImageRepository extends JpaRepository<EmployeesImage, Long> {

	@Query("select ei from EmployeesImage ei where ei.employees.empNo =:empNo")
	EmployeesImage getOneEmpImg(@Param("empNo")long empNo);
	
	
}
