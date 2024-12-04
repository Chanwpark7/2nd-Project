package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.EmpSchedule;

public interface EmpScheuleRepository extends JpaRepository<EmpSchedule, Long> {

}
