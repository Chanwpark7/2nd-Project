package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
