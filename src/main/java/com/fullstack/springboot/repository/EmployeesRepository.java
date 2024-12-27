package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.EmployeesAuthDTO;
import com.fullstack.springboot.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
	
}
