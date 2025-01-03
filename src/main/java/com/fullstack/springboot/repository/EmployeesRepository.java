package com.fullstack.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

	@Query("Select new com.fullstack.springboot.dto.EmployeesDTO(emp) from Employees emp")
	Page<EmployeesDTO> getEmployeesList(Pageable pageable);

	@Query("Select distinct new com.fullstack.springboot.dto.EmployeesDTO(emp) "
			+ "from Employees emp "
			+ "left join Job j on emp.job = j "
			+ "where j.jobNo = :jobNo")
	Page<EmployeesDTO> getEmployeesListByJobNo(Pageable pageable, @Param("jobNo") Long jobNo);
	
	@Query("Select distinct new com.fullstack.springboot.dto.EmployeesDTO(emp) "
			+ "from Employees emp "
			+ "left join DeptInfo di on emp.deptInfo = di "
			+ "where di.deptNo = :deptNo")
	Page<EmployeesDTO> getEmployeesListByDeptNo(Pageable pageable, @Param("deptNo") Long deptNo);
}
