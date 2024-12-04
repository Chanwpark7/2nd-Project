package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.DeptSchedule;

public interface DeptScheduleRepository extends JpaRepository<DeptSchedule, Long> {

}
