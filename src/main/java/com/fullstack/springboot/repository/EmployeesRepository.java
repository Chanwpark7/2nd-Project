package com.fullstack.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

	@Query("Select new com.fullstack.springboot.dto.EmployeesDTO(emp) from Employees emp")
	Page<EmployeesDTO> getEmployeesList(Pageable pageable);
}
