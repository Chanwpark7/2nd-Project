package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
