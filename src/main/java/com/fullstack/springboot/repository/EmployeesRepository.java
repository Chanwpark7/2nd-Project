package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

}
