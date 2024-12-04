package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.JobHistory;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {

}
