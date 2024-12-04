package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.SalaryChart;

public interface SalaryChartRepository extends JpaRepository<SalaryChart, Long> {

}
